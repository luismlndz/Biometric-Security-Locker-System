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

public class DropLocker extends AppCompatActivity {

    TextInputEditText textInputEditLocker_id;
    Button buttonDrop;
    TextView textViewSignOut, textViewReturn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_locker);

        final GlobalClass globalClass = (GlobalClass) getApplicationContext();

        textInputEditLocker_id = findViewById(R.id.locker_id);
        buttonDrop = findViewById(R.id.buttonDrop);
        textViewReturn = findViewById(R.id.return_to_selection);
        textViewSignOut = findViewById(R.id.signOut);
        progressBar = findViewById(R.id.progress);

        textViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.setUsername("");
                globalClass.setFullname("");
                globalClass.setEmail("");
                globalClass.setLocker_id("");
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        textViewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainSelection.class);
                startActivity(intent);
                finish();
            }
        });

        buttonDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String locker_id, username;
                locker_id = String.valueOf(textInputEditLocker_id.getText());
                username = String.valueOf(globalClass.getUsername());

                if (!locker_id.equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "locker_id";
                            field[1] = "username";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = locker_id;
                            data[1] = username;

                            PutData putData = new PutData("http://192.168.56.1/LoginRegister/dropLocker.php", "POST", field, data);
                            //PutData putData = new PutData("https://biometricsecuritysystem.000webhostapp.com/dropLocker.php", "POST", field, data);
                            if (putData.startPut())
                            {
                                if (putData.onComplete())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Drop Success"))
                                    {
                                        globalClass.setLocker_id("");
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
                else
                {
                    Toast.makeText(getApplicationContext(), "Locker ID required", Toast.LENGTH_SHORT).show();
                }


            }

        });
    }
}