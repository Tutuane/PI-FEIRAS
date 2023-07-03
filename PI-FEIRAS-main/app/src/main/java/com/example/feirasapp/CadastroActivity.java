package com.example.feirasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.feirasapp.Util.ConfigBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {


    FirebaseAuth autenticacao;
    Button bt_cadastra;
    EditText et_senha;
    EditText et_senha2;
    EditText et_email;

    View viewCadastro;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        viewCadastro = findViewById(R.id.viewCadastro);
        bt_cadastra = findViewById(R.id.bt_registraUsuario);
        et_senha = findViewById(R.id.et_senhaCadastro);
        et_senha2 = findViewById(R.id.et_senha2Cadastro);
        et_email = findViewById(R.id.et_emailCadastro);

        viewCadastro.setBackgroundColor(Color.rgb(244, 164, 96));
        bt_cadastra.setBackgroundColor(Color.rgb(128, 128, 0));


        mAuth = FirebaseAuth.getInstance();

        bt_cadastra.setOnClickListener(new View.OnClickListener() {
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
                criarContaFirebase(email, senha1);
            } else {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }



    private void criarContaFirebase ( String email, String senha1){
        mAuth.createUserWithEmailAndPassword(
                email, senha1
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CadastroActivity.this, MainMenuActivity.class);
                startActivity(i);
                finish();
                System.err.println("entrou");
            } else {
                System.err.println("caiu aqui");
                Toast.makeText(CadastroActivity.this, "Ocorreu um erro no cadastro", Toast.LENGTH_SHORT).show();
            }
        });
    }






}