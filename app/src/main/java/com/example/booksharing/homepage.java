package com.example.booksharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Scanner;

public class homepage extends AppCompatActivity {

    FirebaseAuth fbauth;

    Button btScanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        fbauth=FirebaseAuth.getInstance();

        btScanner = findViewById(R.id.btScanner);
        btScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homepage.this, scanner.class);
                startActivity(i);
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
