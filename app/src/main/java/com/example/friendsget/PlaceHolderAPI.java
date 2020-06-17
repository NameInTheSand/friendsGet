package com.example.friendsget;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface PlaceHolderAPI {
    @GET("method/friends.get")
    public Call<Post> getPostofUser(@Query("user_id") String id,
                                    @Query("access_token") String token,
                                    @Query("fields") String city,
                                    @Query("v") String version); // building post
    public class Post { //serializing and getting request
        @SerializedName("response")
        Response response;

    }
    class Response {
        @SerializedName("items")
        public ArrayList<Items> item = new ArrayList<Items>();
        @SerializedName("count")
        int count;

    }

    class Items {
        @SerializedName("first_name")
        String firstName;
        @SerializedName("last_name")
        String lastName;
    }
}
