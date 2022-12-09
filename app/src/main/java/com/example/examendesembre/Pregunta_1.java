package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class Pregunta_1 extends AppCompatActivity {

    Button mButtonNext;

    CheckBox mCheckBox[];

    int resposta = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        mButtonNext = findViewById(R.id.button_next_pregunta_1);
        mCheckBox = new CheckBox[4];

        mCheckBox[0] = findViewById(R.id.pregunta_1_resposta_1);
        mCheckBox[1] = findViewById(R.id.pregunta_1_resposta_2);
        mCheckBox[2] = findViewById(R.id.pregunta_1_resposta_3);
        mCheckBox[3] = findViewById(R.id.pregunta_1_resposta_4);

        for(int i=0; i<4; ++i) {
            int finalI = i;
            mCheckBox[i].setOnClickListener(view -> {
                if(mCheckBox[finalI].isChecked()) {
                    resposta |= 1<<finalI;
                } else {
                    resposta ^= 1<<finalI;
                }
            });
        }
        mButtonNext.setOnClickListener(view -> {
            SharedPreferences sp = getApplicationContext().getSharedPreferences("respostes", MODE_PRIVATE);
            sp.edit().putInt("resposta_1", resposta);
            Intent intent = new Intent(this, Pregunta_2.class);
            startActivity(intent);
        });
    }
}