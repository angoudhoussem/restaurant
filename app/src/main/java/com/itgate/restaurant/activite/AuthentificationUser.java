package com.itgate.restaurant.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification_user);
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

                           if(utilisateur.getRole().getNomRole().equals("serveur")) {

                              Intent i=new Intent(getApplicationContext(), MainActivity.class);
                              // i.putExtr
                               Utilisateur utilisateur1=(Utilisateur)i.getSerializableExtra("serveur");
                               startActivity(i);
                           } else {



                               Intent i=new Intent(getApplicationContext(), MenuGrillardin.class);
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
}
