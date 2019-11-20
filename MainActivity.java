package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button  btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("user")
                   && password.getText().toString().equals("123") )
                {
                    //jump to new activity
                    Toast.makeText(MainActivity.this,"welcome",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,MyActivity.class);
                    MainActivity.this.startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"please enter the correct username and password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
