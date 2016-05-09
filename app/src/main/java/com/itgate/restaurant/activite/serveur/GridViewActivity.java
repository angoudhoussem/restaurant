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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itgate.restaurant.R;
import com.itgate.restaurant.activite.grillardin.ListCommande;
import com.itgate.restaurant.adapter.GridviewAdapter;
import com.itgate.restaurant.model.Categorie;
import com.itgate.restaurant.model.Commande;
import com.itgate.restaurant.model.LigneCommande;
import com.itgate.restaurant.model.Plat;
import com.itgate.restaurant.model.Utilisateur;
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

public class GridViewActivity extends AppCompatActivity {


    private GridviewAdapter mAdapter;
    private ArrayList<Categorie> categoriesList = new ArrayList<>();
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferences1;
    AlertDialogManager alertDialogManager = new AlertDialogManager();
    Gson gson = new Gson();
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor1;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sharedpreferences = getSharedPreferences("lignes", Context.MODE_PRIVATE);

        editor = sharedpreferences.edit();
        sharedpreferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);


        // prepared arraylist and passed it to the Adapter class
        gridView = (GridView) findViewById(R.id.gridView1);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                Config.base_url + "/categories").build();
        final RestServeur rest = restAdapter.create(RestServeur.class);
        rest.getAllCategorie(100, 100, new Callback<List<Categorie>>() {
            @Override
            public void success(List<Categorie> categories, Response response) {

                ArrayList<String> listCat = new ArrayList<String>();
                ArrayList<Drawable> listFlag = new ArrayList<Drawable>();


                for (Categorie categorie : categories) {

                    listCat.add(categorie.getNomCategorie());
                    Bitmap bitmap;
//Convert bitmap to drawable
                    Drawable drawable = new BitmapDrawable(getResources(), ConvertImageFromStringToBitmap.convert(categorie.getImageCategorie()));
                    // imageView.setImageDrawable(drawable);
                    listFlag.add(drawable);


                }


                mAdapter = new GridviewAdapter(GridViewActivity.this, listCat, listFlag);
                gridView.setAdapter(mAdapter);
            }

            @Override
            public void failure(RetrofitError error) {


                Log.v("erreur", error.getMessage());

            }
        });


        // Implement On Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                String categorie = (String) gridView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "cat" + categorie, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),
                        PlatActivity.class);
                intent.putExtra("obj", categorie);
                //intent.putExtra("valeur", "bonjour" + position);

                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //creates a menu inflater
        MenuInflater inflater = getMenuInflater();
        //generates a Menu from a menu resource file
        //R.menu.main_menu represents the ID of the XML resource file
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //check selected menu item
        // R.id.exit is @+id/exit
        Gson gson = new Gson();
        String json = sharedpreferences.getString("ligne", "");

       final LigneCommande obj = gson.fromJson(json, LigneCommande.class);

         String jsonuser = sharedpreferences1.getString("user", "");

        final Utilisateur user = gson.fromJson(jsonuser, Utilisateur.class);
        if (item.getItemId() == R.id.commande) {

            AlertDialog.Builder alert = new AlertDialog.Builder(GridViewActivity.this);


            final EditText input;

            if (obj != null) {

                alert.setTitle("Passer Commande "); //Set Alert dialog title here
                alert.setMessage("Quantite Total :" + obj.getQuantite()); //Message here

                // Set an EditText view to get user input
                input = new EditText(GridViewActivity.this);
                input.setHint("Saisir Numero Table");

                alert.setView(input);


                editor.clear();
                editor.commit();

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                                Config.base_url + "/commandes").build();
                        final RestServeur rest2 = restAdapter.create(RestServeur.class);

                        Log.v("num table", input.getText().toString());
                        Log.v("num user", "" + user.getIdUtilisateur());
                        Log.v("num plat", "" + obj.getPlat().getIdPlat());
                        Log.v("num quantite", String.valueOf(obj.getQuantite()));
                        Double d = new Double(obj.getQuantite());
                        int quantite = d.intValue();
                        rest2.PasserCommande(input.getText().toString(), user.getIdUtilisateur(), obj.getPlat().getIdPlat(), quantite, new Callback<Commande>() {
                            @Override
                            public void success(Commande commande, Response response) {
                                if (commande != null) {
                                    alertDialogManager.showAlertDialog(GridViewActivity.this, "passer commande avec succes", "", true);

                                } else {

                                    alertDialogManager.showAlertDialog(GridViewActivity.this, "Erreur", "", false);
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Log.v("erreur", error.getMessage());
                            }
                        });
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

            } else {

                alert.setTitle("Pas de Quantite "); //Set Alert dialog title here

                 input = new EditText(GridViewActivity.this);

                input.setFocusable(false);

                alert.setView(input);
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        dialog.cancel();
                    }
                }); //End of alert.setNegativeButton
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }

            return true;
        }
        if (item.getItemId() == R.id.mescommandes) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.exit) {
            //close the Activity
            this.finish();
            return true;
        }
        return false;
    }
}
