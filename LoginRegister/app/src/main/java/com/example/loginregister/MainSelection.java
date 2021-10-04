package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.concurrent.Executor;

public class MainSelection extends AppCompatActivity {

    Button buttonViewAvailable, buttonDropLocker;
    ProgressBar progressBar;
    TextView textViewSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonViewAvailable = findViewById(R.id.buttonViewAvailable);
        buttonDropLocker = findViewById(R.id.buttonDropLocker);
        progressBar = findViewById(R.id.progress);
        textViewSignOut = findViewById(R.id.signOut);

        textViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonViewAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LockerSelection.class);
                startActivity(intent);
                finish();
            }
        });

        buttonDropLocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DropLocker.class);
                startActivity(intent);
                finish();
            }
        });


    }
}