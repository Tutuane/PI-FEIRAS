package com.example.feirasapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlanosActivity extends AppCompatActivity {

    List<String> planos = new ArrayList<>();
    Button bt_produtos, br_fale, bt_sobre, bt_perfil;

    ListView listView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planos);

        bt_produtos=findViewById(R.id.bt_produtosPlanos);
        br_fale=findViewById(R.id.bt_falePlanos);
        bt_sobre=findViewById(R.id.bt_sobrePlanos);
        bt_perfil=findViewById(R.id.bt_perfilPlanos);
        listView=findViewById(R.id.lv_list_planos);

        bt_produtos.setBackgroundColor(Color.rgb(128, 128, 0));
        br_fale.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_sobre.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_perfil.setBackgroundColor(Color.rgb(128, 128, 0));


        planos.add("");

    }
    public void fale(View v){
        Intent i = new Intent( PlanosActivity.this, FaleConoscoActivity.class);
        startActivity(i);
    }
    public void perfil(View v){
        Intent i = new Intent( PlanosActivity.this, MeuPerfilActivity.class);
        startActivity(i);
    }

    public void produtos(View v){
        Intent i = new Intent( PlanosActivity.this, ListaProdutosActivity.class);
        startActivity(i);
    }
    public void sobre(View v){
        Intent i = new Intent( PlanosActivity.this, SobreNosActivity.class);
        startActivity(i);
    }
}