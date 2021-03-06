package com.example.esercitazionebonus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esercitazionebonus.databinding.ActivityHomeBinding;
import java.util.List;
import static com.example.esercitazionebonus.RegistrazioneActivity.MyPREFERENCES;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<String> list;
    Context context;
    String qualcosa = "OOOOOOOOOOOO";
    String dati;
    String username;
    SharedPreferences sharedpreferences;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;

    String adminCheck;

    public MyAdapter(Context context, List<String> list){
        this.list = list;
        this.context=context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_listitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.nome.setText(list.get(position));

        holder.tasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if (sharedpreferences.contains((list.get(position)))){
                    dati = sharedpreferences.getString(list.get(position), null);
                    String[] arrayDati = dati.split("-");

                    editor.remove(list.get(position));
                    editor.apply();

                    if (arrayDati[4].equals("0")){
                        adminCheck = "1";
                        toast = Toast.makeText(context, "Ora ?? admin!", duration);
                        toast.show();
                    }
                    else {
                        toast = Toast.makeText(context, "E' gi?? admin", duration);
                        toast.show();
                    }
                    editor.putString(arrayDati[0], arrayDati[0] + "-" + arrayDati[1] + "-" + arrayDati[2] + "-" + arrayDati[3] + "-" + adminCheck);
                    editor.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        Button tasto;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.account1);
            tasto = itemView.findViewById(R.id.loginButton);
        }
    }
}
