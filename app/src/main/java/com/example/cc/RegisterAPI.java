package com.example.cc;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Belal on 11/5/2015.
 */
public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/register.php")
    public void insertUser(
            @Field("uname") String name,
            @Field("email") String email,
            @Field("upass") String pass,
            @Field("phone") String phone,


            Callback<Response> callback
    );

    @FormUrlEncoded
    @POST("/login.php")
    public void loginUser(
            @Field("email") String email,
            @Field("upass") String pass,

            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/showbatches.php")
    public void selectbatch(
            @Field("email") String name,
            Callback<Response> callback);



    @FormUrlEncoded
    @POST("/showbatch_details.php")
    public void showbatchdetails(
            @Field("bid") String bid,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/registerbatch.php")
    public void addbatch(
            @Field("bid") String bid,
            @Field("uid") String uid,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/viewRegistration.php")
    public void viewregistration(
            @Field("uid") String bid,


            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/showproducts.php")
    public void selectproduct(
            @Field("pname") String pname,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/show_club.php")
    public void accesslatlang(

            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            Callback<Response> responseCallback);


}
