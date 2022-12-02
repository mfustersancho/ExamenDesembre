package com.example.examendesembre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextInputEditText mTextEmail;
    Button mButtonContinue;
    Button mButtonSignIn;
    ImageView mImatgeTitol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextEmail = findViewById(R.id.email_edit_text);
        mButtonContinue = findViewById(R.id.button_continue);
        mButtonSignIn = findViewById(R.id.button_sign_in);
        mImatgeTitol = findViewById(R.id.imatge_titol);

        mButtonContinue.setEnabled(false);
        mButtonSignIn.setEnabled(false);

        mTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0) {
                    mButtonContinue.setEnabled(true);
                    mButtonSignIn.setEnabled(true);
                    mImatgeTitol.setVisibility(View.GONE);
                } else {
                    mButtonContinue.setEnabled(false);
                    mButtonSignIn.setEnabled(false);
                    mImatgeTitol.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mButtonContinue.setOnClickListener(view -> {
            String email = mTextEmail.getText().toString();
            if(comprobarEmail(email)) {
                Intent intent = new Intent(this, SignIn.class);
                intent.putExtra("com.example.SignIn.email", email);
                intent.putExtra("com.example.SignIn.inscribir", false);
                startActivity(intent);
            } else {
                mTextEmail.setText("");
            }
        });

        mButtonSignIn.setOnClickListener(view -> {
            String email = mTextEmail.getText().toString();
            Intent intent = new Intent(this, SignIn.class);
            intent.putExtra("com.example.SignIn.email", email);
            intent.putExtra("com.example.SignIn.inscribir", true);
            startActivity(intent);
        });
    }

    public boolean comprobarEmail(String email) {
        try {
            InputStream inputStream = this.openFileInput("users.txt");
            if(inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    return true;
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