package com.ibtissam.numberbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IbtissamContactAdapter extends RecyclerView.Adapter<IbtissamContactAdapter.IbtissamViewHolder> {

    private List<IbtissamContact> ibtissamContacts;

    public IbtissamContactAdapter(List<IbtissamContact> contacts) {
        this.ibtissamContacts = contacts;
    }

    @NonNull
    @Override
    public IbtissamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ibtissam_contact, parent, false);
        return new IbtissamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IbtissamViewHolder holder, int position) {
        IbtissamContact contact = ibtissamContacts.get(position);
        holder.tvName.setText(contact.getIbtissam_name());
        holder.tvPhone.setText(contact.getIbtissam_phone());
    }

    @Override
    public int getItemCount() {
        return ibtissamContacts.size();
    }

    public void updateIbtissamData(List<IbtissamContact> newContacts) {
        this.ibtissamContacts = newContacts;
        notifyDataSetChanged();
    }

    static class IbtissamViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;

        public IbtissamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvContactNameIbtissam);
            tvPhone = itemView.findViewById(R.id.tvContactPhoneIbtissam);
        }
    }
}
