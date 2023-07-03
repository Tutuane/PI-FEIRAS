package com.example.feirasapp.Util;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.feirasapp.Produto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private static ProdutoController instance;

    public static ProdutoController getInstance() {
        if(instance==null) {
            instance = new ProdutoController();
        }
        return instance;
    }
    DatabaseReference database;

    private ProdutoController(){
        database = FirebaseDatabase.getInstance().getReference("produtos");
    }



    public boolean save(Produto p){
        final boolean[] status = new boolean[1];
        // Criar um ID único para o produto no Firebase Database
        if(p.getId()==null) {
            String produtoId = database.push().getKey();
            p.setId(produtoId);
        }
            // Criar um objeto Produto com os dados fornecidos
        database.child(p.getId()).setValue(p)
                    .addOnSuccessListener(aVoid -> {
                        status[0] = true;
                    })
                    .addOnFailureListener(e -> {
                        status[0] = false;

                    });

        // Salvar o produto no Firebase Database
        return status[0];
    }

}
