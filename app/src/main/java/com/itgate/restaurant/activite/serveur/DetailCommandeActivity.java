package com.itgate.restaurant.activite.serveur;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.itgate.restaurant.R;
import com.itgate.restaurant.adapter.LigneCommandeAdapter;
import com.itgate.restaurant.adapter.ServeurAdapter;
import com.itgate.restaurant.model.Commande;
import com.itgate.restaurant.model.LigneCommande;
import com.itgate.restaurant.model.Utilisateur;
import com.itgate.restaurant.service.RestServeur;
import com.itgate.restaurant.util.Config;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailCommandeActivity extends AppCompatActivity {
    ArrayList<LigneCommande> dataList = new ArrayList<LigneCommande>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commande);
        listView = (ListView)findViewById(R.id.listView5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        Commande  commande= gson.fromJson(strObj, Commande.class);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                Config.base_url+ "/commandes").build();
        final RestServeur rest = restAdapter.create(RestServeur.class);

        rest.getLigneComandeByIDCommande(commande.getIdCommande(), new Callback<List<LigneCommande>>() {
            @Override
            public void success(List<LigneCommande> ligneCommandes, Response response) {

                dataList.addAll(ligneCommandes);
                ListAdapter listinfoadapter = new LigneCommandeAdapter(DetailCommandeActivity.this,
                        R.layout.carview_lignecommande, dataList);
                listView.setAdapter(listinfoadapter);
            }

            @Override
            public void failure(RetrofitError error) {

                Log.v("erreur,",error.getMessage());
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
