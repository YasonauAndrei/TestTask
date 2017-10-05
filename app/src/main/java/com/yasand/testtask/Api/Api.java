package com.yasand.testtask.Api;

import com.yasand.testtask.Models.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/xim/api.php")
    Call<Model> getList(@Query("query") final String category);

}
