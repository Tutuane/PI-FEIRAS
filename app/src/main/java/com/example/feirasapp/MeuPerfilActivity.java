package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeuPerfilActivity extends AppCompatActivity {

    Button bt_editar, bt_produtos, br_fale, bt_planos, bt_sobre;
    View viewUser;

    TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);


        viewUser=findViewById(R.id.viewUser);
        bt_produtos=findViewById(R.id.bt_produtosUser);
        br_fale=findViewById(R.id.bt_faleUser);
        bt_planos=findViewById(R.id.bt_planosUser);
        bt_sobre=findViewById(R.id.bt_sobreUser);
        bt_editar=findViewById(R.id.bt_editar);

        viewUser.setBackgroundColor(Color.rgb(244, 164, 96));
        bt_produtos.setBackgroundColor(Color.rgb(128, 128, 0));
        br_fale.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_planos.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_sobre.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_editar.setBackgroundColor(Color.rgb(128, 128, 0));

        tv_email = findViewById(R.id.tv_emailUser);
        String valor = getIntent().getStringExtra("Chave");
        tv_email.setText(valor);
    }
    public void editar(View v){
        Intent i = new Intent( MeuPerfilActivity.this, EditaUsuarioActivity.class);
        startActivity(i);
    }
}