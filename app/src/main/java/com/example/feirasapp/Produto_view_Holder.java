package com.example.feirasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Produto_view_Holder extends RecyclerView.Adapter<Produto_view_Holder.ProdutoViewHolder> {

    private List<Produtos_cadastrados> produtos;

    public Produto_view_Holder() {
    }

    public void setProdutos(List<Produtos_cadastrados> produtos) {
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produtos_item, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produtos_cadastrados produto = produtos.get(position);
        holder.bind(produto);
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        public TextView Produto_tvtextView, Nome_tvtextView;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            Produto_tvtextView = itemView.findViewById(R.id.Produto_tvtextView);
            Nome_tvtextView = itemView.findViewById(R.id.Nome_tvtextView);
        }

        public void bind(Produtos_cadastrados produto) {
            Produto_tvtextView.setText(produto.getNome());
            Nome_tvtextView.setText(produto.getProduto());
        }
    }
}
