package com.example.esercitazionebonus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.esercitazionebonus.databinding.ActivityRegistrazioneBinding;

import java.time.Year;
import java.util.Calendar;

import static java.sql.Types.NULL;

public class RegistrazioneActivity extends AppCompatActivity {

    private ActivityRegistrazioneBinding regBind;

    private String username, password, confermaPassword, citta, adminCheck;
    private TextView mDisplayDate;
    int flagData;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    /*Shared preference*/
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        regBind = DataBindingUtil.setContentView(this, R.layout.activity_registrazione);
        mDisplayDate = (TextView) regBind.attrDataNascita;

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrazioneActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date;
                month++;
                date = dayOfMonth + "/" + month + "/" + year;
                regBind.attrDataNascita.setText(date);
                flagData = 1;
            }
        };

        regBind.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*Inserimento dei dati del form di registrazione nella shared preference*/
                if (data()) {
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    adminCheck = "0";

                    editor.putString(username, username + "-" + password + "-" + citta + "-" + mDisplayDate.getText() + "-" + adminCheck);
                    editor.commit();

                    Intent loginScreen = new Intent(RegistrazioneActivity.this, MainActivity.class);
                    startActivity(loginScreen);
                } else {
                }
            }
        });
    }

    public Boolean data() {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedpreferences.edit();

        /*Prende i dati dalle edit text*/
        username = regBind.attrUsername.getText().toString(); //Controllare se esiste gia l'username
        password = regBind.attrPassword.getText().toString();
        confermaPassword = regBind.attrConfPassword.getText().toString();
        citta = regBind.attrCitta.getText().toString();
        //dataNascita = regBind.attrDataNascita.getText().toString();

        if (!password.equals(confermaPassword)) {
            regBind.attrPassword.setError("Password diverse");
            regBind.attrConfPassword.setError("Password diverse");
            return false;
        }

        else if (username.isEmpty() || password.isEmpty() || confermaPassword.isEmpty() || flagData!=1 ){
            regBind.attrUsername.setError("Compila tutti i campi");
            return false;
        }

        else if(sharedpreferences.contains(username)){
            regBind.attrUsername.setError("Username gia utilizzato");
            return false;
        }
        else{return true;}
    }


}
