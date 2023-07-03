package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FaleConoscoActivity extends AppCompatActivity {

    Button bt_produtos, br_sobre, bt_planos, bt_perfil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fale_conosco);


        bt_produtos=findViewById(R.id.bt_produtosFale);
        br_sobre=findViewById(R.id.bt_sobreFale);
        bt_planos=findViewById(R.id.bt_planosFale);
        bt_perfil=findViewById(R.id.bt_perfilFale);

        bt_produtos.setBackgroundColor(Color.rgb(128, 128, 0));
        br_sobre.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_planos.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_perfil.setBackgroundColor(Color.rgb(128, 128, 0));

    }
    public void planos(View v){
        Intent i = new Intent( FaleConoscoActivity.this, PlanosActivity.class);
        startActivity(i);
    }
    public void perfil(View v){
        Intent i = new Intent( FaleConoscoActivity.this, MeuPerfilActivity.class);
        startActivity(i);
    }

    public void produtos(View v){
        Intent i = new Intent( FaleConoscoActivity.this, ListaProdutosActivity.class);
        startActivity(i);
    }
    public void sobre(View v){
        Intent i = new Intent( FaleConoscoActivity.this, SobreNosActivity.class);
        startActivity(i);
    }
}