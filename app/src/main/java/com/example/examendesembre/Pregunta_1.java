package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Pregunta_1 extends AppCompatActivity {

    Button mButtonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        mButtonNext = findViewById(R.id.button_next_pregunta_1);

        mButtonNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, Pregunta_2.class);
            startActivity(intent);
        });
    }
}