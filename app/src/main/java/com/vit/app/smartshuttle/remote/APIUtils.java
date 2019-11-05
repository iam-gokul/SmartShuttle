package com.vit.app.smartshuttle.remote;

public class APIUtils {

    private APIUtils(){

    };

    public static final String BASE_URL = "http://192.168.1.11:5000/api/";

    public static Api getUserService()
    {
        return RetrofitClientInstance.getClient(BASE_URL).create(Api.class);
    }

}
