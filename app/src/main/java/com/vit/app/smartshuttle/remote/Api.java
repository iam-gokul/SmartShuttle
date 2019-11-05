package com.vit.app.smartshuttle.remote;

import com.vit.app.smartshuttle.modals.Cabs;
import com.vit.app.smartshuttle.modals.LogObject;
import com.vit.app.smartshuttle.modals.Profile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("authLogin")
    Call<LogObject> validate(
            @Field("regNo") String regNo,
            @Field("pwd") String pwd
    );

    @FormUrlEncoded
    @POST("authDriverLogin")
    Call<LogObject> driverValidate(
            @Field("driverID") String driverID,
            @Field("pwd") String pwd
    );

    @FormUrlEncoded
    @POST("fetchProfile")
    Call<Profile> loginp(
            @Field("regNo") String regNo
    );

    @FormUrlEncoded
    @POST("makeTransaction")
    Call<Object> transaction(
            @Field("regNo") String regNo,
            @Field("paymentID") String paymentID,
            @Field("amount") String amount,
            @Field("status") String status


    );


    @POST("fetchCabsLocation")
    Call<Cabs[]> getLocations();




}
