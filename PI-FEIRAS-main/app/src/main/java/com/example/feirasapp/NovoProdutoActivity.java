package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.feirasapp.Util.ProdutoController;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NovoProdutoActivity extends AppCompatActivity {

    private EditText et_valorProduto, et_quilosProduto, et_nomeProduto;
    private Button bt_novo;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        et_valorProduto = findViewById(R.id.et_valorProduto);
        et_quilosProduto = findViewById(R.id.et_quilosProduto);
        et_nomeProduto = findViewById(R.id.et_nomeProduto);
        bt_novo = findViewById(R.id.bt_editaProduto);

        bt_novo.setBackgroundColor(Color.rgb(128, 128, 0));

        // Obter uma referência para o nó "produtos" no Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("produtos");
    }

    public void cadastrarProduto(View view) {
        // Obter os valores dos campos de texto
        String nomeProduto = et_nomeProduto.getText().toString().trim();
        String valorProduto = "R$ " +et_valorProduto.getText().toString().trim();
        String quilosProduto = et_quilosProduto.getText().toString().trim()+"kg";

        // Verificar se os campos estão preenchidos
        if (nomeProduto.isEmpty() || valorProduto.isEmpty() || quilosProduto.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }


        // Criar um objeto Produto com os dados fornecidos
        Produto produto = new Produto(nomeProduto, valorProduto, quilosProduto);


        boolean res = ProdutoController.getInstance().save(produto);
        if(res){
            Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            Intent i = new Intent( NovoProdutoActivity.this, ListaProdutosActivity.class);
            startActivity(i);
            limparCampos();

        }else{
            Toast.makeText(this, "Produto cadastrado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent( NovoProdutoActivity.this, ListaProdutosActivity.class);
            startActivity(i);
        }
    }

    private void limparCampos() {
        et_nomeProduto.setText("");
        et_valorProduto.setText("");
        et_quilosProduto.setText("");
    }
}
