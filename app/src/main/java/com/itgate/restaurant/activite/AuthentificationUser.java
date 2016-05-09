package com.itgate.restaurant.activite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itgate.restaurant.R;
import com.itgate.restaurant.activite.grillardin.MenuGrillardin;
import com.itgate.restaurant.activite.serveur.MainActivity;
import com.itgate.restaurant.model.Utilisateur;
import com.itgate.restaurant.service.RestServeur;
import com.itgate.restaurant.util.Config;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthentificationUser extends AppCompatActivity {
     EditText  editLogin,editPassword;
     Button btnlogin;
    SharedPreferences sharedpreferences1;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification_user);
        ////////////////////////////////
        sharedpreferences = getSharedPreferences("lignes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        /////////////////////////////
        sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editoruser = sharedpreferences.edit();
        //////////////////////

        btnlogin= (Button) findViewById(R.id.button);
        editLogin= (EditText) findViewById(R.id.editText);
        editPassword= (EditText) findViewById(R.id.editText2);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"utilisateur",Toast.LENGTH_LONG).show();
                  String Login=editLogin.getText().toString();
                  String Password=editPassword.getText().toString();

                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
                        Config.base_url).build();
                final RestServeur rest = restAdapter.create(RestServeur.class);

                rest.authentification(Login, Password, new Callback<Utilisateur>() {
                    @Override
                    public void success(Utilisateur utilisateur, Response response) {
                        Gson gson = new Gson();
                        String json = gson.toJson(utilisateur);
                        editoruser.putString("user", json);
                        editoruser.commit();

                           if(utilisateur.getRole().getNomRole().equals("Serveur")) {

                              Intent i=new Intent(getApplicationContext(), MainActivity.class);
                               i.putExtra("obj", gson.toJson(utilisateur));
                               startActivity(i);
                           } else {



                               Intent i=new Intent(getApplicationContext(), MenuGrillardin.class);
                               i.putExtra("obj", gson.toJson(utilisateur));
                               startActivity(i);

                           }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Log.v("erreur login",error.getMessage());
                    }
                });

            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(getApplicationContext(),
                        AuthentificationUser.class));

        }

        return super.onKeyDown(keyCode, event);
    }
}
