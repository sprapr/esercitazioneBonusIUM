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
    private String username, password, confermaPassword, citta, dataNascita;


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
                username = extras.getString("username");
                password = extras.getString("password");
                citta = extras.getString("citta");
                dataNascita = extras.getString("data");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }

        Log.i("c", username);

        /*Fa vedere soltanto i pulsanti che servono in base all'utente che entra*/
        if (username.equals("admin")){
            homeBind.adminIcon.setVisibility(View.VISIBLE);
            homeBind.gestisci.setVisibility(View.VISIBLE);
            homeBind.cambiaPassword.setVisibility(View.GONE);
            homeBind.username.setText("Admin");
            homeBind.password.setText("Admin");
            homeBind.citta.setText("Admin");
        }
        else{
            homeBind.adminIcon.setVisibility(View.GONE);
            homeBind.gestisci.setVisibility(View.GONE);
            homeBind.nome.setText(username);
            homeBind.nomePassword.setText(password);
            homeBind.nomeCitta.setText(citta);
            homeBind.nomeDataNascita.setText(dataNascita);
        }

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












