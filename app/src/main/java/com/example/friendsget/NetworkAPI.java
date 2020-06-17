package com.example.friendsget;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkAPI {
    //init
    private static NetworkAPI mInstance;
    private static final String BASE_URL = "https://api.vk.com/";
    private Retrofit mRetrofit;
    //end init
    private NetworkAPI() {
        mRetrofit = new Retrofit.Builder() // builder request
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkAPI getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkAPI();
        }
        return mInstance;
    }
    public PlaceHolderAPI getJSONApi() {
        return mRetrofit.create(PlaceHolderAPI.class);
    }
}
