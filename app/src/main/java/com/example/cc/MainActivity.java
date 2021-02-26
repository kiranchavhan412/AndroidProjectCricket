package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Runnable{

    Thread t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=new Thread(MainActivity.this);
        t1.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void run() {
        try {
            t1.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            Intent i1=new Intent(MainActivity.this,Register.class);
            startActivity(i1);
        }

    }
}
