package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.RadioButton;

public class Pregunta_2 extends AppCompatActivity {

    Button mButtonPrevious;
    Button mButtonNext;
    RadioButton[] mRBResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);

        mButtonPrevious = findViewById(R.id.button_previous_pregunta_2);
        mButtonNext = findViewById(R.id.button_next_pregunta_2);

        mButtonNext.setEnabled(false);

        
    }
}