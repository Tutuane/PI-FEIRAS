package com.example.feirasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_cadastro, bt_login;

    View viewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewMain=findViewById(R.id.viewMain);
        bt_login=findViewById(R.id.bt_login);
        bt_cadastro=findViewById(R.id.bt_registrar);

        viewMain.setBackgroundColor(Color.rgb(244, 164, 96));
        bt_login.setBackgroundColor(Color.rgb(128, 128, 0));
        bt_cadastro.setBackgroundColor(Color.rgb(128, 128, 0));

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });
    }
}