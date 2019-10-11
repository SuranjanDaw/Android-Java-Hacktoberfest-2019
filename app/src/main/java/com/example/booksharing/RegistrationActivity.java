package com.example.booksharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText pass;
    private Button register;
    private TextView backSign;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fbAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.etrname);
        email = findViewById(R.id.etremail);
        pass = findViewById(R.id.etrpassword);
        register = findViewById(R.id.busubmit);
        backSign = findViewById(R.id.return1);
    }

    public void submit(View view) {
        int fill = 0;
        String n = name.getText().toString();
        String em = email.getText().toString().trim();
        String p = pass.getText().toString();
        if (n.isEmpty() || em.isEmpty() || p.isEmpty()) {
            Toast.makeText(this, "All details are mandatory", Toast.LENGTH_SHORT).show();
        }
        else {
            fill = 1;
        }
        if (fill == 1) {
            //upload data to database
            fbAuth.createUserWithEmailAndPassword(em, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }
                    else {
                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                        Toast.makeText(RegistrationActivity.this, "Failed RegistrationActivity: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void bu2(View view) {
        finish();
    }
}