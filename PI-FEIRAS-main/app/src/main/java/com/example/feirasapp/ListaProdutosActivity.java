package com.example.feirasapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feirasapp.Util.ProdutoController;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {
    ProdutoController controller = ProdutoController.getInstance();
    List<Produto> produtos = new ArrayList<>();
    Button bt_perfil, br_fale, bt_planos, bt_sobre;
    ListView listView;

    protected void atualizaListaProduto() {

        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(ListaProdutosActivity.this, R.layout.list_item_produto, R.id.textViewNome, produtos) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_produto, parent, false);
                }

                Produto produto = getItem(position);

                TextView textViewNome = convertView.findViewById(R.id.textViewNome);
                TextView textViewValor = convertView.findViewById(R.id.textViewValor);
                TextView textViewEstoque = convertView.findViewById(R.id.textViewEstoque);

                textViewNome.setText("Nome: " + produto.getNome());
                textViewValor.setText("Valor por quilo: " + produto.getValor());
                textViewEstoque.setText("Estoque: " + produto.getQuilos());

                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        bt_sobre=findViewById(R.id.bt_sobreProdutos);
        br_fale=findViewById(R.id.bt_faleProdutos);
        bt_planos=findViewById(R.id.bt_planosProdutos);
        bt_perfil=findViewById(R.id.bt_perfilProdutos);
        listView=findViewById(R.id.lv_list_produtos);

        bt_perfil.setBackgroundColor(Color.rgb(128, 128, 0));
        br_fale.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_planos.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_sobre.setBackgroundColor(Color.rgb(128, 128, 0));

        recuperarProdutos();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtém o produto selecionado
                Produto produtoSelecionado = (Produto) parent.getItemAtPosition(position);

                // Exibe um AlertDialog com as opções de editar e excluir
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaProdutosActivity.this);
                builder.setTitle("Opções do Produto")
                        .setItems(new CharSequence[]{"Editar", "Excluir"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    // Opção "Editar" selecionada
                                    editarProduto(produtoSelecionado);

                                } else if (which == 1) {
                                    // Opção "Excluir" selecionada
                                    excluirProduto(produtoSelecionado);
                                }
                            }
                        })
                        .create()
                        .show();

                return true; // Indica que o evento de clique longo foi consumido
            }
        });
    }

    protected void recuperarProdutos() {
        FirebaseDatabase.getInstance().getReference("produtos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                produtos.clear(); // Limpa a lista de produtos antes de adicioná-los novamente

                for (DataSnapshot produtoSnapshot : dataSnapshot.getChildren()) {
                    // Converter cada snapshot em um objeto Produto
                    Produto produto = produtoSnapshot.getValue(Produto.class);
                    produtos.add(produto);
                }
                System.out.println("PRODUTOS DO CONTROLLER");
                for (Produto p : produtos) {
                    System.out.println(p);
                }
                System.out.println("FIM DO PRODUTO DO CONTROLLER");
                atualizaListaProduto();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
            }
        });
    }




    private void excluirProduto(Produto produto) {
        // Remove o produto do banco de dados
        DatabaseReference produtoRef = FirebaseDatabase.getInstance().getReference("produtos").child(produto.getId());
        produtoRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Remove o produto da lista
                boolean removed = produtos.remove(produto);

                if (removed) {
                    atualizaListaProduto();

                    Toast.makeText(ListaProdutosActivity.this, "Produto deletado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListaProdutosActivity.this, "Falha ao deletar o produto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void fale(View v){
        Intent i = new Intent( ListaProdutosActivity.this, FaleConoscoActivity.class);
        startActivity(i);
    }
    private void editarProduto(Produto produto) {
        Intent intent = new Intent(ListaProdutosActivity.this, EditaProdutoActivity.class);
        intent.putExtra("produtoId", produto.getId());
        startActivity(intent);
    }
    public void planos(View v){
        Intent i = new Intent( ListaProdutosActivity.this, PlanosActivity.class);
        startActivity(i);
    }
    public void novoProduto(View v){
        Intent i = new Intent( ListaProdutosActivity.this, NovoProdutoActivity.class);
        startActivity(i);
    }
    public void perfil(View v){
        Intent i = new Intent( ListaProdutosActivity.this, MeuPerfilActivity.class);
        startActivity(i);
    }

    public void sobre(View v){
        Intent i = new Intent( ListaProdutosActivity.this, SobreNosActivity.class);
        startActivity(i);
    }



}