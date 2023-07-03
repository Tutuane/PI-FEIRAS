package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MeuPerfilActivity extends AppCompatActivity {

    Button bt_editar, bt_produtos, br_fale, bt_planos, bt_sobre;

    private TextView tv_email;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);
        firebaseAuth = FirebaseAuth.getInstance();

        bt_produtos=findViewById(R.id.bt_produtosPerfil);
        br_fale=findViewById(R.id.bt_falePerfil);
        bt_planos=findViewById(R.id.bt_planosPerfil);
        bt_sobre=findViewById(R.id.bt_sobrePerfil);
        bt_editar=findViewById(R.id.bt_editar);

        tv_email=findViewById(R.id.tv_emailUser);

        bt_produtos.setBackgroundColor(Color.rgb(128, 128, 0));
        br_fale.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_planos.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_sobre.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_editar.setBackgroundColor(Color.rgb(128, 128, 0));

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            tv_email.setText(userEmail);
        }


    }
    public void fale(View v){
        Intent i = new Intent( MeuPerfilActivity.this, FaleConoscoActivity.class);
        startActivity(i);
    }
    public void planos(View v){
        Intent i = new Intent( MeuPerfilActivity.this, PlanosActivity.class);
        startActivity(i);
    }
    public void editar(View v){
        Intent i = new Intent( MeuPerfilActivity.this, EditaUsuarioActivity.class);
        startActivity(i);
    }
    public void produtos(View v){
        Intent i = new Intent( MeuPerfilActivity.this, ListaProdutosActivity.class);
        startActivity(i);
    }
    public void sobre(View v){
        Intent i = new Intent( MeuPerfilActivity.this, SobreNosActivity.class);
        startActivity(i);
    }
}
