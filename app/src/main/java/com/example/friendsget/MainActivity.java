package com.example.friendsget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    //init
    EditText userId; // ET who gets user's text id
    Button getFriends; // Button to show friends
    RecyclerView friendsList; //rv for friends list
    RecyclerAdapter recyclerAdapter;// my Adapter for RV
    final static String token = "56e9037856e9037856e90378b8569b9e83556e956e90378080e4a9db8597a871bd5ebef"; // token of app
    final static String str = "city"; // for api(one part of request)
    final static String ver = "5.110"; //version of request

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init ui elements
        userId = findViewById(R.id.et_userID);
        getFriends = findViewById(R.id.BtnGet);
        //end init
        if (savedInstanceState != null) { // for save state when app change it
            userId.setText(savedInstanceState.getString("ID")); // for user id field
            initViews(); // for RV restore
        }
        getFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
            }
        });//request and RV adapter set
    }

    private void initViews() { // adapter set
        friendsList = findViewById(R.id.rv_friends); //init
        friendsList.setHasFixedSize(true); // because we know count of friends
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        friendsList.setLayoutManager(layoutManager);
        loadJSON();// request
    }

    private void loadJSON() {
        final ArrayList<String> names = new ArrayList<String>();//for names (init here because you need to clean info for new requests
        final ArrayList<String> surnames = new ArrayList<String>();//for surnames
        NetworkAPI.getInstance()
                .getJSONApi()
                .getPostofUser(userId.getText().toString(), token, str, ver)// build request
                .enqueue(new Callback<PlaceHolderAPI.Post>() {
                    @Override
                    public void onResponse(@NonNull Call<PlaceHolderAPI.Post> call,
                                           @NonNull Response<PlaceHolderAPI.Post> response) {
                        PlaceHolderAPI.Post post = response.body();
                        try { // for another exceptions
                            if (post != null) {
                                for (int i = 0; i < post.response.count; i++) { // set arrays info
                                    names.add(post.response.item.get(i).firstName);
                                    surnames.add(post.response.item.get(i).lastName);
                                }
                                recyclerAdapter = new RecyclerAdapter(names, surnames, post.response.count); // setting adapter for RV
                                friendsList.setAdapter(recyclerAdapter);
                            }
                        }
                        catch (Exception e)
                        {
                            Log.d("Post",e.getMessage()); // for debugging
                        }
                    }

                    @Override
                    public void onFailure(Call<PlaceHolderAPI.Post> call, Throwable t) {
                        Log.d("2", t.getMessage()); // maybe here will be Toast "fail to connect"
                    }
                });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { // saving state

        savedInstanceState.putString("ID",userId.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
}