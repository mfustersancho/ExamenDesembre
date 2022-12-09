package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SignIn extends AppCompatActivity {

    TextInputEditText mEmailInputEditText;
    TextInputEditText mPasswordTextInputEditText;
    Button mButtonSignIn;
    TextView mTextFuncion;

    boolean estadoInscribir;

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmailInputEditText = findViewById(R.id.email_edit_text);
        mPasswordTextInputEditText = findViewById(R.id.password_edit_text);
        mButtonSignIn = findViewById(R.id.create_user);
        mTextFuncion = findViewById(R.id.vista_funcion);

        Intent intent = getIntent();
        mEmailInputEditText.setText(intent.getStringExtra("com.example.SignIn.email"));
        mEmailInputEditText.setFocusable(false);
        estadoInscribir = intent.getBooleanExtra("com.example.SignIn.inscribir", true);
        mPasswordTextInputEditText.setFocusableInTouchMode(true);

        mSharedPreferences = getApplicationContext().getSharedPreferences("signin", MODE_PRIVATE);

        if(!estadoInscribir) {
            mTextFuncion.setText("Continue");
        }

        mEmailInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mEmailInputEditText.getText().length() > 0 && mPasswordTextInputEditText.getText().length() > 4) {
                    mButtonSignIn.setEnabled(true);
                } else {
                    mButtonSignIn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPasswordTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mEmailInputEditText.getText().length() > 0 && mPasswordTextInputEditText.getText().length() > 4) {
                    mButtonSignIn.setEnabled(true);
                } else {
                    mButtonSignIn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mButtonSignIn.setOnClickListener(view -> {
            Intent juegos = new Intent(this, Pregunta_1.class);
            if(estadoInscribir) {
                mSharedPreferences.edit().putString("user", mEmailInputEditText.getText().toString()).apply();
                mSharedPreferences.edit().putString("password", mPasswordTextInputEditText.getText().toString()).apply();
                startActivity(juegos);
            } else {
                if(comprobarPassword(mPasswordTextInputEditText.getText().toString())) {
                    startActivity(juegos);
                } else {
                    finish();
                }
            }
        });
    }

    public boolean comprobarPassword(String password) {
        String passGuardado = mSharedPreferences.getString("password", "");
        return passGuardado != null && password.compareTo(passGuardado) == 0;
    }
}