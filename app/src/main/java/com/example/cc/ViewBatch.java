package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
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

public class ViewBatch extends AppCompatActivity {

    // ArrayList for person names
    ArrayList batchtype = new ArrayList<>(Arrays.asList());
    ArrayList agegroup = new ArrayList<>(Arrays.asList());
    ArrayList bid=new ArrayList<>(Arrays.asList());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_batch);





        TextView txtView=(TextView) findViewById(R.id.scroller1);
        txtView.setSelected(true);

//        //image slider start..............

//        ImageSlider imageSlider=findViewById(R.id.slider);
//        List<SlideModel> slideModels=new ArrayList<>();
//        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEjGxwVfcylHaFFVb_2LqtJUve91Ka9u-jIg&usqp=CAU"));
//        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4iXfU3QjdOMpaMQc_4lIQlXEyb3kMUDp9rw&usqp=CAU"));
//        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJ3lVd-cmN-nzzV0og0QdDn5uHvrWcbrL5pQ&usqp=CAU"));
//        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfDfaUyBjqDz3SfODNbirc-D8WgA-v2MECSQ&usqp=CAU"));
//        imageSlider.setImageList(slideModels,true);

//        //image slider end...........

        Config s1=new Config();
        s1.abc();
        s1.api.selectbatch(
                "",





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

                                batchtype.add(jsonObj.getString("batchtype").toString());
                                agegroup.add(jsonObj.getString("agegroup").toString());
                                bid.add(jsonObj.getString("bid").toString());
                            }
                            // get the reference of RecyclerView
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
// set a LinearLayoutManager with default vertical orientaion
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
                            CustomAdapter customAdapter = new CustomAdapter(ViewBatch.this, batchtype,agegroup,bid);
                            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void failure(RetrofitError error) {
                         Toast.makeText(ViewBatch.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}
