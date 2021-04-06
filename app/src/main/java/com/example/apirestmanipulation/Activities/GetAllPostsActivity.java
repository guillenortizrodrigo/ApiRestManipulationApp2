package com.example.apirestmanipulation.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apirestmanipulation.Interface.JsonPlaceHolderApi;
import com.example.apirestmanipulation.Models.Post;
import com.example.apirestmanipulation.R;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllPostsActivity extends AppCompatActivity {

    TextView tv_posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_posts);

        /////////CONEXION PARTE GRAFICA//////////////////////////////////////////////////////
        tv_posts = findViewById(R.id.MainActivity_POSTS);
        /////////////////////////////////////////////////////////////////////////////////////

        /////////BOTON PARA EGRESAR//////////////////////////////////////////////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ////////////////////////////////////////////////////////////////////////////////////

        ////////CALLING THE API REST GETTING POSTS//////////////////////////////////////////
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi =retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> posts = jsonPlaceHolderApi.getposts();
        posts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for(Post post:posts){
                    String content = "";
                    content += "ID: "+post.getId();
                    content += "\n";
                    content += "UserID: "+post.getUserId();
                    content += "\n";
                    content += "TITLE: "+post.getTitle();
                    content += "\n";
                    content += "CUERPO: "+post.getBody();
                    content += "\n";
                    content += "\n";

                    tv_posts.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("ErroralConectar",t.getMessage());
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
    }
}