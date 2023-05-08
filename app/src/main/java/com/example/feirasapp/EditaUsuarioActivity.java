package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class EditaUsuarioActivity extends AppCompatActivity {

    Button bt_salva;

    EditText et_email, et_senha, et_senha2;
    View viewEdita;

    Usuario usuario;
    FirebaseAuth autenticacao;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_usuario);

        bt_salva = findViewById(R.id.bt_salvaUsuario);
        viewEdita = findViewById(R.id.viewEdita);

        et_email = findViewById(R.id.et_emailEdita);
        et_senha = findViewById(R.id.et_senhaEdita);
        et_senha2 = findViewById(R.id.et_senha2Edita);


        viewEdita.setBackgroundColor(Color.rgb(244, 164, 96));
        bt_salva.setBackgroundColor(Color.rgb(128, 128, 0));

        bt_salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDados();
            }

        });

    }

    public void validarDados () {
        String email = et_email.getText().toString();
        String senha1 = et_senha.getText().toString();
        String senha2 = et_senha2.getText().toString();

        if (!email.isEmpty() && !email.isEmpty() && !senha1.isEmpty() && !senha2.isEmpty()) {
            if (senha1.equals(senha2)) {
                alteraDados(email, senha1);
            } else {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void alteraDados (String email, String senha1) {

        Intent i = new Intent( EditaUsuarioActivity.this, MeuPerfilActivity.class);
        startActivity(i);
    }

}