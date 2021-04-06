package com.example.apirestmanipulation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apirestmanipulation.Interface.JsonPlaceHolderApi;
import com.example.apirestmanipulation.Models.Post;
import com.example.apirestmanipulation.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn_getposts;
    Button btn_getcomments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getposts = findViewById(R.id.MainActivity_getposts);
        btn_getcomments = findViewById(R.id.MainActivity_createpost);

        btn_getposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotogetpostsactivity();
            }
        });

        btn_getcomments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotogetcomentsactivity();
            }
        });

    }

    private void gotogetcomentsactivity() {
        Intent i = new Intent(MainActivity.this,CreatePostActivity.class);
        startActivity(i);
    }

    private void gotogetpostsactivity() {
        Intent i = new Intent(MainActivity.this,GetAllPostsActivity.class);
        startActivity(i);
    }
}