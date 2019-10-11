package com.example.booksharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetSubmit;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resetEmail = findViewById(R.id.forem);
        resetSubmit = findViewById(R.id.bu5);
        fbAuth = FirebaseAuth.getInstance();
    }

    public void buonclick5(View view) {
        String email = resetEmail.getText().toString().trim();
        if (email.equals("")) {
            Toast.makeText(ForgotPassActivity.this, "Please Enter Your Registred Email id", Toast.LENGTH_SHORT).show();
        }
        else {
            fbAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPassActivity.this, "Password Reset Link Sent", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ForgotPassActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(ForgotPassActivity.this, "Email ID not Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
