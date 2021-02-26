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

public class Login extends AppCompatActivity implements View.OnClickListener{
    mylib l1=new mylib();
    EditText email,pass,pass1;
    TextView register_now;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.pass);
        pass1=(EditText) findViewById(R.id.pass1);
        register_now=(TextView) findViewById(R.id.register_now);
        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Login.this, Register.class);
                startActivity(i1);
            }
        });
        signin=(Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signin:
                Config s1=new Config();
                s1.abc();
                s1.api.loginUser(
                        email.getText().toString(),
                        pass.getText().toString(),


                        new Callback<Response>() {
                            @Override
                            public void success(Response result, Response response) {

                                try {

                                    BufferedReader reader =new BufferedReader(new InputStreamReader(result.getBody().in()));
                                    String  output = reader.readLine();
                                    if(output.equals("0"))
                                    {
                                        l1.showpopup(Login.this,"Error","Invalid Credentials");

                                    }
                                    else
                                    {

                                        l1.showpopup(Login.this,"Success","Login Successfull");
                                        Config.uid=output;

                                        Intent i1=new Intent(Login.this,Dashboard.class);
                                        startActivity(i1);

                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }



                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(Login.this, error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                );
                break;
            case R.id.register_now:
                Intent i1=new Intent(Login.this,Register.class);
                startActivity(i1);
                break;

        }

    }

}