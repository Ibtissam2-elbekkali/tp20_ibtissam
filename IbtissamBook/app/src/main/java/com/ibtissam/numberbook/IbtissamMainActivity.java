package com.ibtissam.numberbook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IbtissamMainActivity extends AppCompatActivity {

    private MaterialButton btnLoadIbtissamContacts, btnSyncIbtissam, btnSearchIbtissam;
    private EditText etKeywordIbtissam;
    private RecyclerView rvContactsIbtissam;
    private IbtissamContactAdapter ibtissamAdapter;
    private List<IbtissamContact> ibtissamContactList = new ArrayList<>();
    private IbtissamContactApi ibtissamApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibtissam_main);

        btnLoadIbtissamContacts = findViewById(R.id.btnLoadIbtissamContacts);
        btnSyncIbtissam = findViewById(R.id.btnSyncIbtissam);
        btnSearchIbtissam = findViewById(R.id.btnSearchIbtissam);
        etKeywordIbtissam = findViewById(R.id.etKeywordIbtissam);
        rvContactsIbtissam = findViewById(R.id.rvContactsIbtissam);

        rvContactsIbtissam.setLayoutManager(new LinearLayoutManager(this));
        ibtissamAdapter = new IbtissamContactAdapter(ibtissamContactList);
        rvContactsIbtissam.setAdapter(ibtissamAdapter);

        ibtissamApi = IbtissamRetrofitClient.getClient().create(IbtissamContactApi.class);

        btnLoadIbtissamContacts.setOnClickListener(v -> checkIbtissamPermissionAndLoad());
        btnSyncIbtissam.setOnClickListener(v -> syncIbtissamContacts());
        btnSearchIbtissam.setOnClickListener(v -> searchIbtissamContacts());
    }

    private void checkIbtissamPermissionAndLoad() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            loadIbtissamContacts();
        } else {
            requestIbtissamPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }

    private final ActivityResultLauncher<String> requestIbtissamPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    loadIbtissamContacts();
                } else {
                    Toast.makeText(this, "Permission refusée pour Ibtissam", Toast.LENGTH_SHORT).show();
                }
            });

    private void loadIbtissamContacts() {
        ibtissamContactList.clear();

        Cursor ibtissamCursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        );

        if (ibtissamCursor != null) {
            while (ibtissamCursor.moveToNext()) {
                String ibtissamName = ibtissamCursor.getString(
                        ibtissamCursor.getColumnIndexOrThrow(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                        )
                );

                String ibtissamPhone = ibtissamCursor.getString(
                        ibtissamCursor.getColumnIndexOrThrow(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                );

                ibtissamContactList.add(new IbtissamContact(ibtissamName, ibtissamPhone));
            }
            ibtissamCursor.close();
        }

        ibtissamAdapter.updateIbtissamData(ibtissamContactList);
        Toast.makeText(this, "Contacts d'Ibtissam chargés : " + ibtissamContactList.size(), Toast.LENGTH_SHORT).show();
    }

    private void syncIbtissamContacts() {
        if (ibtissamContactList.isEmpty()) {
            Toast.makeText(this, "Aucun contact à synchroniser", Toast.LENGTH_SHORT).show();
            return;
        }

        for (IbtissamContact ibtissamContact : ibtissamContactList) {
            ibtissamApi.insertIbtissamContact(ibtissamContact).enqueue(new Callback<IbtissamApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<IbtissamApiResponse> call, @NonNull Response<IbtissamApiResponse> response) {
                }

                @Override
                public void onFailure(@NonNull Call<IbtissamApiResponse> call, @NonNull Throwable t) {
                    Toast.makeText(IbtissamMainActivity.this, "Erreur réseau Ibtissam", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Toast.makeText(this, "Synchronisation d'Ibtissam lancée ✨", Toast.LENGTH_SHORT).show();
    }

    private void searchIbtissamContacts() {
        String ibtissamKeyword = etKeywordIbtissam.getText().toString().trim();

        if (ibtissamKeyword.isEmpty()) {
            Toast.makeText(this, "Saisir un nom ou un numéro Ibtissam", Toast.LENGTH_SHORT).show();
            return;
        }

        ibtissamApi.searchIbtissamContacts(ibtissamKeyword).enqueue(new Callback<List<IbtissamContact>>() {
            @Override
            public void onResponse(@NonNull Call<List<IbtissamContact>> call, @NonNull Response<List<IbtissamContact>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ibtissamAdapter.updateIbtissamData(response.body());
                    if (response.body().isEmpty()) {
                        Toast.makeText(IbtissamMainActivity.this, "Aucun résultat trouvé", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<IbtissamContact>> call, @NonNull Throwable t) {
                Toast.makeText(IbtissamMainActivity.this, "Erreur de recherche Ibtissam", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
