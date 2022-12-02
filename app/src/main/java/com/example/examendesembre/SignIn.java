package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            if(estadoInscribir) {
                try {
                    FileOutputStream outputStream = openFileOutput("users.txt", MODE_APPEND);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    outputStreamWriter.write(mEmailInputEditText.getText().toString());
                    outputStreamWriter.write(mPasswordTextInputEditText.getText().toString());
                    Log.d("SignIn", "Grabado: " + mEmailInputEditText.getText().toString() +
                            " : " + mPasswordTextInputEditText.getText().toString() +
                            " en " + outputStream.getFD());
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    finishAffinity();
                } catch (IOException e) {
                    Log.e("SignIn", e.getMessage());
                }
            } else {
                if(comprobarPassword(mPasswordTextInputEditText.getText().toString())) {
                    // ir a los juegos
                } else {
                    finishAffinity();
                }
            }
        });
    }

    public boolean comprobarPassword(String password) {
        try {
            InputStream inputStream = this.openFileInput("users.txt");
            if(inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    if(receiveString.equals(mEmailInputEditText.getText().toString())) {
                        String pass = bufferedReader.readLine();
                        if(pass != null && pass.equals(password))
                            return true;
                    }
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return false;
    }
}