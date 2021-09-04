package com.example.esercitazionebonus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.esercitazionebonus.databinding.ActivityEditPasswordBinding;
import com.example.esercitazionebonus.databinding.ActivityMainBinding;
import com.example.esercitazionebonus.databinding.ActivityRegistrazioneBinding;

import static com.example.esercitazionebonus.RegistrazioneActivity.MyPREFERENCES;

public class EditPasswordActivity extends AppCompatActivity {
    private ActivityEditPasswordBinding bind;
    String username;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
            } else {
                username = extras.getString("username");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }
        Log.i("edit", username);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_edit_password);
        //intent


        bind.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String password, confPassword;

                password = bind.attrPassword.getText().toString();
                confPassword = bind.attrConfPassword.getText().toString();

                String dati;

                Log.i("Edit2", sharedpreferences.getString(username,null));

                if (password.length() == 0 || confPassword.length() == 0){
                    bind.attrPassword.setError("Campo vuoto");
                    bind.attrConfPassword.setError("Campo vuoto");
                }

                else if(!password.equals(confPassword)){
                    bind.attrPassword.setError("Password diverse");
                }

                else if (password.equals(confPassword)){ //non entra qui
                    //Salvare nelle shared preference
                    if (sharedpreferences.contains(username)){
                        Log.i("Entra","si");
                        dati = sharedpreferences.getString(username, null);
                        String[] arrayDati = dati.split("-");

                        editor.putString(username, username + "-" + password + "-" + arrayDati[2] + "-" + arrayDati[3] + "-" + arrayDati[4]);
                        editor.commit();

                        Intent toHome = new Intent(EditPasswordActivity.this, HomeActivity.class);
                        toHome.putExtra("username", arrayDati[0]);
                        toHome.putExtra("password", password);
                        toHome.putExtra("citta", arrayDati[2]);
                        toHome.putExtra("data", arrayDati[3]);
                        toHome.putExtra("adminCheck",arrayDati[4]);
                        startActivity(toHome);
                    }
                }
            }
        });

    }
}
