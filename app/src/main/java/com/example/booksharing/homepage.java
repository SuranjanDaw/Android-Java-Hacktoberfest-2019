package com.example.booksharing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {

    private FirebaseAuth fbauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        fbauth = FirebaseAuth.getInstance();
        BottomNavigationView navigationView = findViewById(R.id.btm_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.apiCaller:
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, homeFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.qr_scanner:
                        QR_ScanFragment qr_scanFragment = new QR_ScanFragment();
                        FragmentTransaction qrScanTransaction = getSupportFragmentManager().beginTransaction();
                        qrScanTransaction.replace(R.id.frame_layout, qr_scanFragment);
                        qrScanTransaction.commit();
                        break;
                }
                return true;
            }
        });
        navigationView.setSelectedItemId(R.id.apiCaller);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutmenu: {
                fbauth.signOut();
                finish();
                startActivity(new Intent(homepage.this, MainActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
