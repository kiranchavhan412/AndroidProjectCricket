package com.example.cc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BatchDetails extends AppCompatActivity {


    mylib l1=new mylib();
    TextView batchtype,agegroup,batchtiming,fee,batchstart,batchend,bid;
    Button registerbatch,viewRegistration,btnDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_details);




        batchtype = (TextView) findViewById(R.id.batchtype);
        agegroup = (TextView) findViewById(R.id.agegroup);
        batchtiming = (TextView) findViewById(R.id.batchtiming);
        fee = (TextView) findViewById(R.id.fee);
        batchstart = (TextView) findViewById(R.id.batchstart);
        batchend = (TextView) findViewById(R.id.batchend);
        bid = (TextView) findViewById(R.id.bid);
        registerbatch=(Button) findViewById(R.id.registerbatch);

        viewRegistration=(Button) findViewById(R.id.viewRegistration);
        viewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(BatchDetails.this,ViewRegistration.class);
                startActivity(i1);
            }
        });
        registerbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdetails(R.layout.dialog_postive_layout);
            }
        });
        show();


    }



    public void showdetails(final int layout) {
        Config s1 = new Config();
        s1.abc();
        s1.api.addbatch(
                ""+getIntent().getExtras().get("bid"),
                ""+Config.uid,

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String output = reader.readLine();
                            if(output.equals("1")) {

                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( BatchDetails.this );
                                View layoutView = getLayoutInflater().inflate(layout, null);
                                final Button btnDialog = layoutView.findViewById(R.id.btnDialog);
                                dialogBuilder.setView(layoutView);
                                final AlertDialog alertDialog = dialogBuilder.create();
                                alertDialog.show();
                                btnDialog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.dismiss();
                                    }
                                });
                            }
                            else
                            {
                                l1.showpopup(BatchDetails.this, ""+output, "Failed");

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(BatchDetails.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void show(){
        Config s1=new Config();
        s1.abc();
        s1.api.showbatchdetails(
                ""+getIntent().getExtras().get("bid"),

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        try {
                            BufferedReader reader =new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String  output = reader.readLine();
                            // Toast.makeText(BatchDetails.this, ""+output,Toast.LENGTH_SHORT).show();
                            JSONArray jsonArr = new JSONArray(output);
                            for (int i = 0; i < jsonArr.length(); i++)
                            {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);

                                batchtype.setText(jsonObj.getString("batchtype").toString());
                                agegroup.setText(jsonObj.getString("agegroup").toString());
                                batchtiming.setText(jsonObj.getString("batchtiming").toString());
                                fee.setText(jsonObj.getString("fee").toString());
                                batchstart.setText(jsonObj.getString("batchstart").toString());
                                batchend.setText(jsonObj.getString("batchend").toString());
                                bid.setText(jsonObj.getString("bid").toString());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(BatchDetails.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}