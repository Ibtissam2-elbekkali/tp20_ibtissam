package com.ibtissam.numberbook;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IbtissamContactApi {

    @POST("ibtissamInsert.php")
    Call<IbtissamApiResponse> insertIbtissamContact(@Body IbtissamContact contact);

    @GET("ibtissamGetAll.php")
    Call<List<IbtissamContact>> getAllIbtissamContacts();

    @GET("ibtissamSearch.php")
    Call<List<IbtissamContact>> searchIbtissamContacts(@Query("ibtissam_keyword") String keyword);
}
