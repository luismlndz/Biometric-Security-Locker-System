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

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        final GlobalClass globalClass = (GlobalClass) getApplicationContext();

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.setUsername("");
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullname, username, password, email;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if (!username.equals("") && !password.equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.56.1/LoginRegister/login.php", "POST", field, data);
                            //PutData putData = new PutData("https://biometricsecuritysystem.000webhostapp.com/login.php", "POST", field, data);
                            if (putData.startPut())
                            {
                                if (putData.onComplete())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Login Success"))
                                    {
                                        globalClass.setUsername(username);
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
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }
}