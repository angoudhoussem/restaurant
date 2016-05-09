package com.itgate.restaurant.service;


import com.itgate.restaurant.model.Categorie;
import com.itgate.restaurant.model.Commande;
import com.itgate.restaurant.model.LigneCommande;
import com.itgate.restaurant.model.Plat;
import com.itgate.restaurant.model.Utilisateur;

import java.util.List;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RestServeur {



    @POST("/authentification")
    void authentification(@Query("login") String login, @Query("password") String password,
                Callback<Utilisateur> callback);




    @POST("/ListesCommandes")
    void getAllCommande(@Query("idServeur") Long id,
                          Callback<List<Commande>> callback);




    @POST("/getPlatByIDPlat")
    void getPlatByIDPlat(@Query("idPlat") Long id,
                          Callback<Plat> callback);

    @POST("/getLigneComandeByIDCommande")
    void getLigneComandeByIDCommande(@Query("idcommande") Long id,
                          Callback<List<LigneCommande>> callback);

    @POST("/getAllCategorie")
    void getAllCategorie(@Query("limit") int limit, @Query("offset") int offset,
                          Callback<List<Categorie>> callback);

    @POST("/getAllPlatByCat")
    void getAllPlatByCat(@Query("idCategorie") Long id,
                         Callback<List<Plat>> callback);
    @POST("/PasserCommande")
    void PasserCommande(@Query("numtable") String numtable,@Query("idserveur") Long idserveur,@Query("idPlat") Long idPlat,@Query("quantite") int quantite,
                         Callback<Commande> callback);
}
