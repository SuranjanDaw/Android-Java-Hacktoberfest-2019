package com.example.booksharing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.GridView;

import com.example.booksharing.retrofit.Retrofitclientinstance;
import com.example.booksharing.retrofit.dataItem;
import com.example.booksharing.retrofit.endpoints;
import com.example.booksharing.retrofit.responseModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homepage extends AppCompatActivity {

    FirebaseAuth fbauth;

    String TAG="MainActivity";
    GridView simpleGrid;
    List<dataItem> dataItemsList;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        fbauth=FirebaseAuth.getInstance();


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        simpleGrid = findViewById(R.id.simpleGridView);
        progressDoalog = new ProgressDialog(homepage.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        dataItemsList = new ArrayList<>();

        endpoints api = Retrofitclientinstance.getRetrofitInstance().create(endpoints.class);

        Call<responseModel> call = api.getImages();

        call.enqueue(new Callback<responseModel>() {
            @Override
            public void onResponse(Call<responseModel> call, Response<responseModel> response) {

//                DataItem dataItem = response.body();

                dataItemsList = response.body().getDataItem();

                responseModel responseModel = response.body();

                GridViewCustomAdapter customAdapter = new GridViewCustomAdapter(getApplicationContext(), dataItemsList);
                simpleGrid.setAdapter(customAdapter);
                progressDoalog.dismiss();


            }

            @Override
            public void onFailure(Call<responseModel> call, Throwable t) {

            }


        });








    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logoutmenu:
                {
                fbauth.signOut();
                finish();
                startActivity(new Intent(homepage.this, MainActivity.class));

                }
        }
        return super.onOptionsItemSelected(item);
    }
}
