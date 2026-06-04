package com.ibtissam.numberbook;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IbtissamRetrofitClient {
    // Utilisation de 10.0.2.2 pour localhost sur l'émulateur Android
    private static final String BASE_URL = "http://10.0.2.2/ibtissam-api/api/";
    private static Retrofit retrofitIbtissam;

    public static Retrofit getClient() {
        if (retrofitIbtissam == null) {
            retrofitIbtissam = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitIbtissam;
    }
}
