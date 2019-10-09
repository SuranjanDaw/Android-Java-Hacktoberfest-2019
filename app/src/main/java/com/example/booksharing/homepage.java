package com.example.booksharing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.booksharing.model.Post;
import com.example.booksharing.network.GithubService;
import com.example.booksharing.network.RetrofitInstance;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homepage extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth fbauth;
    private TextView titleField;
    private TextView bodyField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        titleField = findViewById(R.id.title_value);
        bodyField = findViewById(R.id.body_value);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(homepage.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }

        fbauth = FirebaseAuth.getInstance();
        BottomNavigationView navigationView = findViewById(R.id.btm_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.apiCaller) {
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();

                }
                if (id == R.id.qr_scanner) {
                    QR_ScanFragment fragment = new QR_ScanFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
        //The default fragment is home(apiCaller)
        navigationView.setSelectedItemId(R.id.apiCaller);
        makeNetworkCall();
    }

    private void makeNetworkCall() {
        GithubService githubService = RetrofitInstance.getRetrofitInstance().create(GithubService.class);
        Call<List<Post>> allPosts = githubService.getAllPhotos(1);
        allPosts.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.body() != null) {
                    showFirstTwoPosts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(homepage.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFirstTwoPosts(List<Post> response) {
        titleField.setText(response.get(0).getTitle());
        bodyField.setText(response.get(0).getBody());

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
