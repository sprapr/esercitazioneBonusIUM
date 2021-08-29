package com.example.esercitazionebonus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatExtras;
import androidx.databinding.DataBindingUtil;

import com.example.esercitazionebonus.databinding.ActivityHomeBinding;

import static com.example.esercitazionebonus.RegistrazioneActivity.MyPREFERENCES;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding homeBind;
    private String username, password, confermaPassword, citta, dataNascita, adminCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeBind = DataBindingUtil.setContentView(this, R.layout.activity_home);
        Intent toMain = new Intent(HomeActivity.this, MainActivity.class);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
            } else {
                //Prendo i parametri dall'activity Main
                username = extras.getString("username");
                password = extras.getString("password");
                citta = extras.getString("citta");
                dataNascita = extras.getString("data");
                adminCheck = extras.getString("adminCheck");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }

        Log.i("c", username + adminCheck);


        /*Fa vedere soltanto i pulsanti che servono in base all'utente che entra*/
        if (username.equals("admin")){
            homeBind.adminIcon.setVisibility(View.VISIBLE);
            homeBind.gestisci.setVisibility(View.VISIBLE);
            homeBind.cambiaPassword.setVisibility(View.GONE);
            homeBind.username.setText("Admin");
            homeBind.password.setText("Admin");
            homeBind.citta.setVisibility(View.GONE);
            homeBind.nomeDataNascita.setVisibility(View.GONE);
        }
        else if (adminCheck.equals("1")){
            homeBind.adminIcon.setVisibility(View.VISIBLE);
            homeBind.gestisci.setVisibility(View.VISIBLE);
            homeBind.nome.setText(username);
            homeBind.nomePassword.setText(password);
            homeBind.nomeCitta.setText(citta);
            homeBind.nomeDataNascita.setText(dataNascita);
        }
        else{
            homeBind.adminIcon.setVisibility(View.GONE);
            homeBind.gestisci.setVisibility(View.GONE);
            homeBind.nome.setText(username);
            homeBind.nomePassword.setText(password);
            homeBind.nomeCitta.setText(citta);
            homeBind.nomeDataNascita.setText(dataNascita);
        }

        homeBind.gestisci.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toManager = new Intent(HomeActivity.this, UserManager.class);
                startActivity(toManager);
            }
        }));

        homeBind.logout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(HomeActivity.this, MainActivity.class);
                toMain.putExtra("username", username);
                startActivity(toMain);
            }
        }));

        homeBind.cambiaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEdit = new Intent(HomeActivity.this, EditPasswordActivity.class);
                toEdit.putExtra("username", username);
                startActivity(toEdit);
            }
        });
    }
}













