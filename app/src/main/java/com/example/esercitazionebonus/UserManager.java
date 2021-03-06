package com.example.esercitazionebonus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.esercitazionebonus.databinding.ActivityUserManagerBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserManager extends AppCompatActivity {
    ActivityUserManagerBinding bind ;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Log.i("b", "Qui ci arrivo");
        bind = DataBindingUtil.setContentView(this, R.layout.activity_user_manager);

        userList();
    }

    public void userList(){
        List<String> list = new ArrayList<String>();
        Log.i("b", "Qui ci arrivo");
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Map<String,?> map = sharedpreferences.getAll();

        for(Map.Entry<String,?> entry : map.entrySet()){
            list.add(entry.getKey());
        }
        MyAdapter myAdapter = new MyAdapter(this, list);
        bind.recycler.setAdapter(myAdapter);
        bind.recycler.setLayoutManager(new LinearLayoutManager(this));
    }


}
