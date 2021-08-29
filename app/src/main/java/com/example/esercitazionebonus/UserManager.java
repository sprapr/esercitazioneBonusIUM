package com.example.esercitazionebonus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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

        bind = DataBindingUtil.setContentView(this, R.layout.activity_user_manager);

        //userList();
    }

    public void userList(){
        List<String> list = new ArrayList<String>();


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Map map = sharedpreferences.getAll();

        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            list.add(pair.getKey());
            Log.i("k", pair.getKey() + " " + pair.getValue());
        }

        MyAdapter myAdapter = new MyAdapter(this, list);
    }
}
