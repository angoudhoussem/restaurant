
package com.itgate.restaurant.activite.serveur;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itgate.restaurant.R;
import com.itgate.restaurant.adapter.GridviewAdapter;
import com.itgate.restaurant.adapter.PlatAdapter;
import com.itgate.restaurant.adapter.ServeurAdapter;
import com.itgate.restaurant.model.Categorie;
import com.itgate.restaurant.model.Commande;
import com.itgate.restaurant.model.LigneCommande;
import com.itgate.restaurant.model.Plat;
import com.itgate.restaurant.service.RestServeur;
import com.itgate.restaurant.util.AlertDialogManager;
import com.itgate.restaurant.util.Config;
import com.itgate.restaurant.util.ConvertImageFromStringToBitmap;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlatActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Plat> dataList = new ArrayList<Plat>();
    String cat;
    int quantite=0;
  ArrayList<LigneCommande> ligneCommandes=new ArrayList<>();
    AlertDialogManager alertDialogManager=new AlertDialogManager();
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat);

        sharedpreferences= getSharedPreferences("lignes", Context.MODE_PRIVATE);
         editor = sharedpreferences.edit();
        listView = (ListView)findViewById(R.id.listView6);


        Intent  i=getIntent();

         cat=i.getStringExtra("obj");


        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                Config.base_url + "/categories").build();
        final RestServeur rest = restAdapter.create(RestServeur.class);
        rest.getAllCategorie(100, 100, new Callback<List<Categorie>>() {
            @Override
            public void success(List<Categorie> categories, Response response) {


                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                        Config.base_url + "/plats").build();
                final RestServeur rest2 = restAdapter.create(RestServeur.class);

                for (Categorie categorie : categories) {

                    if (categorie.getNomCategorie().equals(cat)) {

                        rest2.getAllPlatByCat(categorie.getIdCategorie(), new Callback<List<Plat>>() {
                            @Override
                            public void success(List<Plat> plats, Response response) {
                                dataList.addAll(plats);
                                ListAdapter listinfoadapter = new PlatAdapter(PlatActivity.this,
                                        R.layout.carview_plat, dataList);
                                listView.setAdapter(listinfoadapter);

                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Log.v("erreur", error.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {


                Log.v("erreur", error.getMessage());

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                final Plat plat = (Plat) listView.getItemAtPosition(position);
                 final LigneCommande ligneCommande=new LigneCommande();
                AlertDialog.Builder alert = new AlertDialog.Builder(PlatActivity.this);
                alert.setTitle("Quantite Plat "); //Set Alert dialog title here
                alert.setMessage("Saisir qunatite plat"); //Message here

                // Set an EditText view to get user input
                final EditText input = new EditText(PlatActivity.this);
                alert.setView(input);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String srt = input.getEditableText().toString();
                        int str=Integer.parseInt(srt);
                         quantite=quantite+str;
                         ligneCommande.setPlat(plat);
                        ligneCommande.setQuantite(Double.parseDouble(String.valueOf(quantite)));
                        ligneCommandes.add(ligneCommande);
                        for (LigneCommande ligneCommande:ligneCommandes) {
                            String json = gson.toJson(ligneCommande);
                            editor.putString("ligne", json);
                            editor.commit();
                        }
                        Toast.makeText(PlatActivity.this,"Somme :"+quantite, Toast.LENGTH_LONG).show();



                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        dialog.cancel();
                    }
                }); //End of alert.setNegativeButton
                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });




    }
}
