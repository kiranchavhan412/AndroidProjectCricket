package com.example.cc;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class mylib extends Activity {
    public void showpopup(Context context, String Title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }


}