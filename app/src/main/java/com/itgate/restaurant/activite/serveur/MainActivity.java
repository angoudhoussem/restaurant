package com.itgate.restaurant.activite.serveur;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itgate.restaurant.R;
import com.itgate.restaurant.activite.AuthentificationUser;
import com.itgate.restaurant.activite.grillardin.ListCommande;
import com.itgate.restaurant.adapter.ServeurAdapter;
import com.itgate.restaurant.model.Commande;
import com.itgate.restaurant.model.Utilisateur;
import com.itgate.restaurant.service.RestServeur;
import com.itgate.restaurant.util.Config;
import com.itgate.restaurant.util.ConvertImageFromStringToBitmap;
import com.itgate.restaurant.util.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Utilisateur utilisateur;
    ListView listView;
    ArrayList<Commande> dataList = new ArrayList<Commande>();
    SharedPreferences sharedpreferences1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Gson gson = new Gson();
        sharedpreferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);
        String jsonuser = sharedpreferences1.getString("user", "");
        utilisateur = gson.fromJson(jsonuser, Utilisateur.class);
        listView = (ListView)findViewById(R.id.listView4);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                Config.base_url + "/commandes").build();
        final RestServeur rest = restAdapter.create(RestServeur.class);

        rest.getAllCommande(utilisateur.getIdUtilisateur(), new Callback<List<Commande>>() {
            @Override
            public void success(List<Commande> commandes, Response response) {
                Log.v("list", "id tab :" + commandes.get(0).getTableCommande().getNumTable());
                dataList.addAll(commandes);
                ListAdapter listinfoadapter = new ServeurAdapter(MainActivity.this,
                        R.layout.carview_serveur, dataList);
                listView.setAdapter(listinfoadapter);
            }


            @Override
            public void failure(RetrofitError error) {

                Log.v("erreur",error.getMessage());

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Commande commande = (Commande) listView.getItemAtPosition(position);
                Gson gson = new Gson();

                Intent intent = new Intent(getApplicationContext(),
                        DetailCommandeActivity.class);
                intent.putExtra("obj", gson.toJson(commande));
                //intent.putExtra("valeur", "bonjour" + position);

                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Passer Commande", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                  Intent intent=new Intent(getApplicationContext(),GridViewActivity.class);
                   startActivity(intent);



            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        ImageView imageView;

        TextView text2,text3;
        View headView = navigationView.getHeaderView(0);

        text2 = (TextView) headView.findViewById(R.id.textView);
        text3 = (TextView) headView.findViewById(R.id.textviewUser);
        imageView = (ImageView) headView.findViewById(R.id.imageView);
        text3.setText(utilisateur.getNom()+ " " +utilisateur.getPrenom());
        text2.setText(String.valueOf(utilisateur.getCin()));
        imageView.setImageBitmap(RoundedImageView.getCroppedBitmap(ConvertImageFromStringToBitmap.convert(utilisateur.getImageUtilisateur()),54));
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
             //fragment=new ListCommandePasser();


            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("vous avez déconnecté");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(getApplicationContext(),
                                    AuthentificationUser.class);
                            startActivity(i);
                            dialog.cancel();
                        }
                    });
            builder1.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.drawer_layout, fragment);
            transaction.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        }

        return true;
    }
     public boolean onKeyDown(int keyCode, KeyEvent event) {
    switch (keyCode) {
     case KeyEvent.KEYCODE_BACK:
     startActivity(new Intent(getApplicationContext(),
     MainActivity.class));

     }

    return super.onKeyDown(keyCode, event);
     }
}
