package com.example.esercitazionebonus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.esercitazionebonus.databinding.ActivityMainBinding;

import java.util.Arrays;

import static com.example.esercitazionebonus.RegistrazioneActivity.MyPREFERENCES;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bind.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        bind.newUserLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void login() {
        Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String dati;
        String insertUsername = bind.username.getText().toString();
        String insertPassword = bind.password.getText().toString();

        if (insertUsername.equals("admin") && insertPassword.equals("admin")){
            Log.i("b","qui");
            toHome.putExtra("username", bind.username.getText().toString());
            startActivity(toHome);
        }

        if(insertPassword.length() == 0 || insertUsername.length() == 0){
            bind.username.setError("inserire username");
            bind.password.setError("inserire password");
        }

        Log.i("k", sharedpreferences.getString(insertUsername, "1"));

        if(!sharedpreferences.contains(insertUsername) && !insertUsername.equals("admin")){
            bind.username.setError("Credenziali errate");
        }

        if (sharedpreferences.contains(insertUsername)){
            dati = sharedpreferences.getString(insertUsername, null);
            String[] arrayDati = dati.split("-");

            if (insertUsername.equals(arrayDati[0]) && insertPassword.equals(arrayDati[1])){
                toHome.putExtra("username", arrayDati[0]);
                toHome.putExtra("password", arrayDati[1]);
                toHome.putExtra("citta", arrayDati[2]);
                toHome.putExtra("data", arrayDati[3]);
                startActivity(toHome);
            }
            else{
                bind.username.setError("Credenziali errate");
            }

        }

        //Controllare se l'username esiste
        /*if (//Arrays.asList(yourArray).contains(yourValue)) {
            Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
            toHome.putExtra("username", usernameInsert);
            toHome.putExtra("password", passwordInsert);
            startActivity(toHome);
        }*/
    }

    public void register(){
        Intent toRegistration = new Intent(MainActivity.this, RegistrazioneActivity.class);
        startActivity(toRegistration);

    }
}



















