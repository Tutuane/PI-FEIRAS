package com.example.feirasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditaProdutoActivity extends AppCompatActivity {

    String produtoId;
    private EditText et_valorProduto, et_quilosProduto, et_nomeProduto;
    private Button bt_edita;

    private Produto produto;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_produto);

        et_valorProduto = findViewById(R.id.et_valorProduto);
        et_quilosProduto = findViewById(R.id.et_quilosProduto);
        et_nomeProduto = findViewById(R.id.et_nomeProduto);
        bt_edita = findViewById(R.id.bt_editaProduto);

        bt_edita.setBackgroundColor(Color.rgb(128, 128, 0));

        produtoId = getIntent().getStringExtra("produtoId");

        // Obter uma referência para o nó "produtos" no Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("produtos");

        // Recuperar os dados do produto com base no ID
        DatabaseReference produtoRef = databaseReference.child(produtoId);
        produtoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Produto encontrado
                    produto = dataSnapshot.getValue(Produto.class);
                    if (produto != null) {
                        // Preencher os campos de texto com os dados do produto
                        et_nomeProduto.setText(produto.getNome());
                        et_valorProduto.setText(produto.getValor());
                        et_quilosProduto.setText(produto.getQuilos());
                    }
                } else {
                    // Produto não encontrado
                    Toast.makeText(EditaProdutoActivity.this, "Produto não encontrado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Ocorreu um erro ao ler os dados do produto
                Toast.makeText(EditaProdutoActivity.this, "Erro ao ler os dados do produto.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    public void editarProduto(View view) {
        // Obter os valores atualizados dos campos de texto
        String nome = et_nomeProduto.getText().toString();
        String valor = "R$ " +et_valorProduto.getText().toString();
        String quilos = et_quilosProduto.getText().toString()+"kg";

        // Atualizar os valores do objeto produto
        if (produto != null) {
            produto.setNome(nome);
            produto.setValor(valor);
            produto.setQuilos(quilos);

            // Obter a referência do nó "produtos" no Firebase Database
            DatabaseReference produtoRef = databaseReference.child(produtoId);

            // Atualizar os dados do produto no Firebase Database
            produtoRef.setValue(produto)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Produto atualizado com sucesso
                            Toast.makeText(EditaProdutoActivity.this, "Produto editado com sucesso.", Toast.LENGTH_SHORT).show();
                            finish(); // Finalizar a atividade atual
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Ocorreu um erro ao editar o produto
                            Toast.makeText(EditaProdutoActivity.this, "Erro ao editar o produto.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Produto não encontrado
            Toast.makeText(EditaProdutoActivity.this, "Produto não encontrado.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}