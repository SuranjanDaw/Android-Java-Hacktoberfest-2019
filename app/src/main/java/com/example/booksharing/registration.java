package com.example.booksharing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class registration extends AppCompatActivity {

    public EditText name;
    public EditText email;
    public EditText pass;
    public Button register;
    public TextView back2sign;
    FirebaseAuth fbauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fbauth=FirebaseAuth.getInstance();
        name=(EditText)findViewById(R.id.etrname);
        email=(EditText)findViewById(R.id.etremail);
        pass=(EditText)findViewById(R.id.etrpassword);
        register=(Button)findViewById(R.id.busubmit);
        back2sign=(TextView)findViewById(R.id.return1);
    }

    public void submit(View view) {
        int fill=0;
        String n=name.getText().toString();
        String em=email.getText().toString().trim();
        String p=pass.getText().toString();
        if(n.isEmpty()||em.isEmpty()||p.isEmpty())
        {
            Toast.makeText(this,"All details are mandatory",Toast.LENGTH_SHORT).show();
        }
        else
        {
            fill=1;
        }
        if(fill==1)
        {
            //upload data to database
            fbauth.createUserWithEmailAndPassword(em,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                      Toast.makeText(registration.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(registration.this,MainActivity.class));
                    }
                    else
                    {
                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                        Toast.makeText(registration.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }



    }

    public void bu2(View view) {
       startActivity(new Intent(registration.this,MainActivity.class));
    }
}
