package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SobreNosActivity extends AppCompatActivity {
    Button bt_perfil, bt_produtos, br_fale, bt_planos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nos);

        bt_produtos=findViewById(R.id.bt_produtosSobre);
        br_fale=findViewById(R.id.bt_faleSobre);
        bt_planos=findViewById(R.id.bt_planosSobre);
        bt_perfil=findViewById(R.id.bt_perfilSobre);


        bt_produtos.setBackgroundColor(Color.rgb(128, 128, 0));
        br_fale.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_planos.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_perfil.setBackgroundColor(Color.rgb(128, 128, 0));
    }
    public void fale(View v){
        Intent i = new Intent( SobreNosActivity.this, FaleConoscoActivity.class);
        startActivity(i);
    }
    public void planos(View v){
        Intent i = new Intent( SobreNosActivity.this, PlanosActivity.class);
        startActivity(i);
    }
    public void perfil(View v){
        Intent i = new Intent( SobreNosActivity.this, MeuPerfilActivity.class);
        startActivity(i);
    }
    public void produtos(View v){
        Intent i = new Intent( SobreNosActivity.this, ListaProdutosActivity.class);
        startActivity(i);
    }
}

