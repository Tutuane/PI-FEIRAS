package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainMenuActivity extends AppCompatActivity {
    Button bt_produtos, br_fale, bt_planos, bt_sobre, bt_perfil;

    View viewMenu;

    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        viewMenu=findViewById(R.id.viewMenu);
        bt_produtos=findViewById(R.id.bt_produtosMenu);
        br_fale=findViewById(R.id.bt_faleMenu);
        bt_planos=findViewById(R.id.bt_planosMenu);
        bt_sobre=findViewById(R.id.bt_sobreMenu);
        bt_perfil=findViewById(R.id.bt_perfilMenu);

        viewMenu.setBackgroundColor(Color.rgb(244, 164, 96));
        bt_produtos.setBackgroundColor(Color.rgb(128, 128, 0));
        br_fale.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_planos.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_sobre.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_perfil.setBackgroundColor(Color.rgb(128, 128, 0));

        usuario = getIntent().getStringExtra("Chave");

    }

    public void perfil(View v){
        Intent i = new Intent( MainMenuActivity.this, MeuPerfilActivity.class);
        i.putExtra("Chave", usuario);
        startActivity(i);
    }

}