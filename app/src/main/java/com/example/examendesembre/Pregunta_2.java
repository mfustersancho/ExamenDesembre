package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Pregunta_2 extends AppCompatActivity {

    Button mButtonPrevious;
    Button mButtonNext;
    RadioButton[] mResposta;

    int resposta = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);

        mButtonPrevious = findViewById(R.id.button_previous_pregunta_2);
        mButtonNext = findViewById(R.id.button_next_pregunta_2);

        mButtonNext.setEnabled(false);

        mButtonPrevious.setOnClickListener(view -> {
            finish();
        });

        mButtonNext.setOnClickListener(view -> {
            SharedPreferences sp = getApplicationContext().getSharedPreferences("respostes", MODE_PRIVATE);
            sp.edit().putInt("resposta_2", resposta);
            Intent pregunta_3 = new Intent(this, Pregunta_3.class);
            startActivity(pregunta_3);
        });

        mResposta = new RadioButton[4];
        mResposta[0] = findViewById(R.id.resposta_1);
        mResposta[1] = findViewById(R.id.resposta_2);
        mResposta[2] = findViewById(R.id.resposta_3);
        mResposta[3] = findViewById(R.id.resposta_4);

        for(int i=0; i<4; ++i) {
            int finalI = i;
            mResposta[i].setOnClickListener(view -> {
                resposta = finalI;
                mButtonNext.setEnabled(true);
            });
        }
    }
}