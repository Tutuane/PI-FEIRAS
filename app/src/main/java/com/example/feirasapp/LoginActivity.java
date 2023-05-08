package com.example.feirasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;

    Button bt_login;
    EditText et_email, et_senha;

    View viewLogin;

    private FirebaseAuth mAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        viewLogin = findViewById(R.id.viewLogin);
        bt_login = findViewById(R.id.bt_logar);
        et_email = findViewById(R.id.et_EmailLogin);
        et_senha = findViewById(R.id.et_senhaLogin);


        viewLogin.setBackgroundColor(Color.rgb(244, 164, 96));
        bt_login.setBackgroundColor(Color.rgb(128, 128, 0));

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDados();
            }
        });

    }


    public void validarDados() {

        String nome = et_email.getText().toString();
        String email = et_email.getText().toString();
        String senha1 = et_senha.getText().toString();
        // String senha2 = et_senha2.getText().toString();


        if (!nome.isEmpty()) {
            if (!senha1.isEmpty()) {

                loginFirebase(email, senha1);

            } else {
                Toast.makeText(this, "Preencha o senha", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
        }

    }


    private void loginFirebase(String email, String senha1) {

        mAuth.signInWithEmailAndPassword(
                email, senha1
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                System.err.println("entrou sucesso");

                // startActivity(new Intent(this, MainMenuActivity.class));

                Intent i = new Intent(this, MainMenuActivity.class);
                i.putExtra("Chave", et_email.getText().toString());
                startActivity(i);
                finish();

            } else {
                System.err.println("entrou com erro");
                Toast.makeText(this, "Email ou senha incorreto", Toast.LENGTH_SHORT).show();
            }

        });

    }

}

