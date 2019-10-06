package com.example.booksharing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private FirebaseAuth fbAuth;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etlname);
        password = findViewById(R.id.etlpassword);
        fbAuth = FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);

        //FUNCTION DISABLED (FUNCTION IS FOR EVERYTIME LOGIN SOLVED)
        /*if(user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,homepage.class));
        }*/
    }

    public void buclick(View view) {
        //if verification part
        String n1 = name.getText().toString();
        String p1 = password.getText().toString();
        if (n1.isEmpty() || p1.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill all credentials", Toast.LENGTH_SHORT).show();
        }
        else {
            progress.setMessage("Please Wait!");
            progress.show();
            fbAuth.signInWithEmailAndPassword(n1, p1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progress.dismiss();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, homepage.class));
                    }
                    else {
                        progress.dismiss();
                        Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    //Goto registration page
    public void bu4(View view) {
        startActivity(new Intent(MainActivity.this, registration.class));
    }

    //goto password reset link page
    public void forgot(View view) {
        startActivity(new Intent(MainActivity.this, forgotpass.class));
    }
}
