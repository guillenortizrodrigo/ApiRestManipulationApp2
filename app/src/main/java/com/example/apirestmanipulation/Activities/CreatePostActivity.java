package com.example.apirestmanipulation.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apirestmanipulation.Interface.JsonPlaceHolderApi;
import com.example.apirestmanipulation.Models.Post;
import com.example.apirestmanipulation.Models.PostRequest;
import com.example.apirestmanipulation.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePostActivity extends AppCompatActivity {

    TextInputEditText et_idusuario;
    TextInputEditText et_titulo;
    TextInputEditText et_body;
    Button btn_create;
    TextView tv_mypost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        ///////////////PARTE GRAFICA//////////////////////////////////////////////////////////
        et_body= findViewById(R.id.CreatePost_body);
        et_idusuario = findViewById(R.id.CreatePost_id);
        et_titulo = findViewById(R.id.CreatePost_titulo);
        btn_create = findViewById(R.id.CreatePost_create);
        tv_mypost = findViewById(R.id.CreatePost_mypost);
        ///////////////////////////////////////////////////////////////////////////////////////

        /////////BOTON PARA EGRESAR//////////////////////////////////////////////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ////////////////////////////////////////////////////////////////////////////////////

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createandget();
            }
        });
    }

    private void createandget() {
        String iduser = et_idusuario.getText().toString();
        String titulo = et_titulo.getText().toString();
        String body = et_body.getText().toString();
        if(!titulo.isEmpty() && !body.isEmpty() && !iduser.isEmpty()){

            PostRequest postRequest = new PostRequest();
            postRequest.setUserId(Integer.parseInt(iduser));
            postRequest.setTitle(titulo);
            postRequest.setBody(body);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            JsonPlaceHolderApi jsonPlaceHolderApi =retrofit.create(JsonPlaceHolderApi.class);
            Call<Post> createpost = jsonPlaceHolderApi.createpost(postRequest);
            createpost.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    String content="";
                    content += "ID: "+response.body().getId();
                    content += "\n";
                    content += "UserID: "+response.body().getUserId();
                    content += "\n";
                    content += "TITLE: "+response.body().getTitle();
                    content += "\n";
                    content += "CUERPO: "+response.body().getBody();
                    tv_mypost.setText(content);
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });

        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}