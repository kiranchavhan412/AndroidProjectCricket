package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ViewRegistration extends AppCompatActivity {

    ArrayList fee = new ArrayList<>(Arrays.asList());
    ArrayList bid = new ArrayList<>(Arrays.asList());
    ArrayList discount = new ArrayList<>(Arrays.asList());
    ArrayList total = new ArrayList<>(Arrays.asList());
    ArrayList agegroup = new ArrayList<>(Arrays.asList());
    ArrayList batchtype = new ArrayList<>(Arrays.asList());





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registration);
        Config s1=new Config();
        s1.abc();
        s1.api.viewregistration(
                ""+Config.uid,

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        try {
                            BufferedReader reader =new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String  output = reader.readLine();
                            JSONArray jsonArr = new JSONArray(output);
                            for (int i = 0; i < jsonArr.length(); i++)
                            {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                bid.add(jsonObj.getString("bid").toString());
                                discount.add(jsonObj.getString("discount").toString());
                                agegroup.add(jsonObj.getString("agegroup").toString());
                                batchtype.add(jsonObj.getString("batchtype").toString());



                                fee.add(jsonObj.getString("fee").toString());

                                int Total=Integer.parseInt(jsonObj.getString("fee"))-Integer.parseInt((jsonObj.getString("discount")));
                                total.add(Total+"");
                            }
                            // get the reference of RecyclerView
                            RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
// set a LinearLayoutManager with default vertical orientaion
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView2.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            ViewRegistrationAdapter viewRegistrationAdapter = new ViewRegistrationAdapter(ViewRegistration.this,fee,bid,discount,total,agegroup,batchtype);
                            recyclerView2.setAdapter(viewRegistrationAdapter); // set the Adapter to RecyclerView
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(ViewRegistration.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}