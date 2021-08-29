package com.example.esercitazionebonus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.esercitazionebonus.databinding.ActivityRegistrazioneBinding;

import java.util.Calendar;

public class RegistrazioneActivity extends AppCompatActivity {

    private ActivityRegistrazioneBinding regBind;

    private String username, password, confermaPassword, citta, dataNascita, adminCheck;

    /*Shared preference*/
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        regBind = DataBindingUtil.setContentView(this, R.layout.activity_registrazione);

        regBind.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*Inserimento dei dati del form di registrazione nella shared preference*/
                if (data()) {
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    adminCheck = "0";

                    editor.putString(username, username + "-" + password + "-" + citta + "-" + dataNascita + "-" + adminCheck);
                    editor.commit();

                    Intent loginScreen = new Intent(RegistrazioneActivity.this, MainActivity.class);
                    startActivity(loginScreen);
                } else {
                    Toast.makeText(getApplicationContext(), "This is my Toast message!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public Boolean data() {

        /*Prende i dati dalle edit text*/
        username = regBind.attrUsername.getText().toString(); //Controllare se esiste gia l'username
        password = regBind.attrPassword.getText().toString();
        confermaPassword = regBind.attrConfPassword.getText().toString();
        citta = regBind.attrCitta.getText().toString();
        dataNascita = regBind.attrDataNascita.getText().toString();

        if (!password.equals(confermaPassword)) {
            regBind.attrPassword.setError("Password diverse");
            regBind.attrConfPassword.setError("Password diverse");
            return false;
        }
        else{return true;}
    }
}
