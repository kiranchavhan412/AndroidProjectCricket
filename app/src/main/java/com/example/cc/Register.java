package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Register extends AppCompatActivity implements View.OnClickListener{
    mylib l1=new mylib();
    TextView login_now;
    EditText name,email,pass,phone;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_register);
        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);
        pass=(EditText) findViewById(R.id.pass);
        login_now=(TextView) findViewById(R.id.login_now);
        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Register.this,Login.class);
                startActivity(i1);
            }
        });
        signup=(Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signup:
                Config s1=new Config();
                s1.abc();
                s1.api.insertUser(
                        name.getText().toString(),
                        email.getText().toString(),
                        pass.getText().toString(),
                        phone.getText().toString(),

                        new Callback<Response>() {
                            @Override
                            public void success(Response result, Response response) {
                                try {
                                    BufferedReader reader =new BufferedReader(new InputStreamReader(result.getBody().in()));
                                    String  output = reader.readLine();
                                    if(output.equals("1"))
                                    {
                                        l1.showpopup(Register.this,"Success","Records Inserted Successfull");
                                        Intent i1=new Intent(Register.this,Login.class);
                                        startActivity(i1);
                                    }
                                    else
                                    {
                                        l1.showpopup(Register.this,"Error","Check the values");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(Register.this, error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                );
                break;

        }
    }


}

