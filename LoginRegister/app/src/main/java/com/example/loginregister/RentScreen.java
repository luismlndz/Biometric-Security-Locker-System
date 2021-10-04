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

public class RentScreen extends AppCompatActivity {

    TextView empty_locker_id, emptyUsername;
    Button buttonRent;
    TextView textViewReturn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_screen);

        empty_locker_id = findViewById(R.id.emptyLocker_id);
        emptyUsername = findViewById(R.id.emptyUsername);
        buttonRent = findViewById(R.id.buttonRent);
        textViewReturn = findViewById(R.id.back);
        progressBar = findViewById(R.id.progress);

        final GlobalClass globalClass = (GlobalClass) getApplicationContext();

        empty_locker_id.setText(globalClass.getLocker_id());
        emptyUsername.setText(globalClass.getUsername());

        textViewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.setLocker_id("");
                Intent intent = new Intent(getApplicationContext(), LockerSelection.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String locker_id, availability, username;
                locker_id = String.valueOf(globalClass.getLocker_id());
                username = String.valueOf(globalClass.getUsername());
                availability = String.valueOf('N');

                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[3];
                        field[0] = "locker_id";
                        field[1] = "username";
                        field[2] = "availability";

                        //Creating array for data
                        String[] data = new String[3];
                        data[0] = locker_id;
                        data[1] = username;
                        data[2] = availability;

                        PutData putData = new PutData("http://192.168.56.1/LoginRegister/rentConfirmation.php", "POST", field, data);
                        //PutData putData = new PutData("https://biometricsecuritysystem.000webhostapp.com/rentConfirmation.php", "POST", field, data);
                        if (putData.startPut())
                        {
                            if (putData.onComplete())
                            {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if(result.equals("Locker Registration Success"))
                                {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainSelection.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                });

            }

        });
    }
}