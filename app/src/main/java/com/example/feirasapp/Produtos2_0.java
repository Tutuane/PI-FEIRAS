package com.example.feirasapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Produtos2_0 extends Activity {

    private Button button;
    private RecyclerView recyclerView;
    private List<Produtos_cadastrados> produtosList = new ArrayList<>();
    private Produto_view_Holder adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos20);

        // Inicializa o FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Obtém uma referência para o nó "Produtos"
        DatabaseReference produtosRef = database.getReference("Produtos");

        recyclerView = findViewById(R.id.produtos_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Produto_view_Holder();
        recyclerView.setAdapter(adapter);

        // Configura o listener para a referência dos produtos
        produtosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                produtosList.clear();
                for (DataSnapshot produtoSnapshot : snapshot.getChildren()) {
                    Produtos_cadastrados produto = produtoSnapshot.getValue(Produtos_cadastrados.class);
                    produtosList.add(produto);
                }
                adapter.setProdutos(produtosList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        button = findViewById(R.id.add_Produtos);
    }

    public void Adicionar_Produtos(View view) {
        Intent intent = new Intent(Produtos2_0.this, Cadastro_Produtos.class);
        startActivity(intent);
    }
}
