package com.example.cc;

import retrofit.RestAdapter;

public class Config {
    public static final String ROOT_URL = "http://192.168.43.30/db_cricket/";
    public static String uid="";
//    public static String uname="";
    RegisterAPI api;
    public void abc() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Config.ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter
        api = adapter.create(RegisterAPI.class);
    }

}
