package com.itgate.restaurant.service;


import com.itgate.restaurant.model.Cassier;
import com.itgate.restaurant.model.Utilisateur;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RestServeur {



    @POST("/authentification")
    void authentification(@Query("login") String login, @Query("password") String password,
                Callback<Utilisateur> callback);
}
