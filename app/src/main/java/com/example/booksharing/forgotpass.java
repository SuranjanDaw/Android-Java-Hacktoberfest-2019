package com.example.booksharing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpass extends AppCompatActivity {

    private EditText resetEmail;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        resetEmail = findViewById(R.id.forem);
        fbAuth = FirebaseAuth.getInstance();
    }

    public void forgotPass(View view) {
        String email = resetEmail.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(forgotpass.this, "Please Enter Your Registred Email id", Toast.LENGTH_SHORT).show();
        }
        else {
            fbAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(forgotpass.this, "Password Reset Link Sent", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(forgotpass.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(forgotpass.this, "Email ID not Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
