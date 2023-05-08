package com.example.feirasapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Cadastro_Produtos extends AppCompatActivity {

    private EditText descricaoProdutoEditText;
    private EditText nomeProdutoEditText;
    private Button adicionarProdutoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        //vincular os xml
        descricaoProdutoEditText = (EditText) findViewById(R.id.textViewDescricaoProduto);
        nomeProdutoEditText = (EditText) findViewById(R.id.editTextNomeProduto);
        adicionarProdutoButton = (Button) findViewById(R.id.adicionar);

        adicionarProdutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get nas informações enviadas pelo usuario
                String descricaoProduto = descricaoProdutoEditText.getText().toString();
                String nomeProduto = nomeProdutoEditText.getText().toString();

                //validar se os campos estao preenchidos
                if(descricaoProduto.isEmpty()){
                    descricaoProdutoEditText.setError("O campo de descrição não pode estar vazio!");
                    return;
                }if (nomeProduto.isEmpty()){
                    nomeProdutoEditText.setError("Preencha o nome do produto!");
                    return;
                }

                //adicionando ao firebase
                addProdutoFireBase(descricaoProduto, nomeProduto);

            }
        });

    }

    private void addProdutoFireBase(String descricaoProduto, String nomeProduto) {

        //create a hashmap
        HashMap<String, Object> descricaoProdutoHashMap = new HashMap<>();
        descricaoProdutoHashMap.put("Descricao", descricaoProduto);
        descricaoProdutoHashMap.put("Nome", nomeProduto);
        //instanciar a conexao na database
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ProdutosRef = database.getReference("Produtos");

        String key = ProdutosRef.push().getKey();
        descricaoProdutoHashMap.put("Key", key);

        ProdutosRef.child(key).setValue(descricaoProdutoHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Cadastro_Produtos.this, "Produto Adicionado", Toast.LENGTH_SHORT).show();
                descricaoProdutoEditText.getText().clear();
                nomeProdutoEditText.getText().clear();
            }
        });
        //preencha na database

    }
}