package com.example.booksharing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    EditText name;
    EditText password;
    Button login;
    FirebaseAuth fblauth;
    ProgressDialog progress;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =  findViewById(R.id.etlname);
        password =  findViewById(R.id.etlpassword);
        login =  findViewById(R.id.busubmit);
        fblauth = FirebaseAuth.getInstance();
        FirebaseUser user = fblauth.getCurrentUser();
        progress = new ProgressDialog(this);
        signInButton = findViewById(R.id.google_signin);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sighIn();
            }
        });
        //FUNCTION DISABLED (FUNCTION IS FOR EVERYTIME LOGIN SOLVED)

        /*if(user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,Homepage.class));
        }*/
    }

    private void sighIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void buclick(View view) {
        //if verification part
        String n1 = name.getText().toString();
        String p1 = password.getText().toString();
        if (n1.isEmpty() || p1.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill all credentials", Toast.LENGTH_SHORT).show();
        } else {
            progress.setMessage("Please Wait!");
            progress.show();
            fblauth.signInWithEmailAndPassword(n1, p1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progress.dismiss();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Homepage.class));
                    } else {
                        progress.dismiss();
                        Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    //Goto Registration page
    public void bu4(View view) {
        startActivity(new Intent(MainActivity.this, Registration.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleResult(task);
        }
    }

    private void handleResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            finish();
            startActivity(new Intent(MainActivity.this, Homepage.class));
        } catch (ApiException e) {
            Log.w("Google Sign-In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    //goto password reset link page
    public void forgot(View view) {
        startActivity(new Intent(MainActivity.this, Forgotpass.class));
    }

    @Override
    protected void onStart() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            finish();
            startActivity(new Intent(MainActivity.this, Homepage.class));
        }
        super.onStart();
    }
}
