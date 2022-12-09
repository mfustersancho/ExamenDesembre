package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class Pregunta_3 extends AppCompatActivity {

    Button mButtonPrevious;
    Button mButtonNext;
    SeekBar mSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);
        mButtonPrevious = findViewById(R.id.button_previous_pregunta_3);
        mButtonNext = findViewById(R.id.button_next_pregunta_3);
        mSeekBar = findViewById(R.id.seekBar);

        mButtonPrevious.setOnClickListener(view -> {
            finish();
        });

        mButtonNext.setOnClickListener(view -> {
            SharedPreferences sp = getApplicationContext().getSharedPreferences("respostes", MODE_PRIVATE);
            sp.edit().putInt("resposta_3", mSeekBar.getProgress());
            Intent resultats = new Intent(this, Resultats.class);
            startActivity(resultats);
        });
    }
}