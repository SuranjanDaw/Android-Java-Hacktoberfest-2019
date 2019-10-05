package com.example.booksharing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {

    EditText resetemail;
    Button resetsubmit;
    FirebaseAuth fbauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        resetemail=(EditText)findViewById(R.id.forem);
        resetsubmit=(Button)findViewById(R.id.bu5);
        fbauth=FirebaseAuth.getInstance();
    }

    public void buonclick5(View view) {
        String email=resetemail.getText().toString().trim();
        if(email.equals(""))
        {
            Toast.makeText(Forgotpass.this,"Please Enter Your Registred Email id",Toast.LENGTH_SHORT).show();
        }
        else
        {
            fbauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Forgotpass.this,"Password Reset Link Sent",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Forgotpass.this,MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Forgotpass.this,"Email ID not Registered",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }
}
