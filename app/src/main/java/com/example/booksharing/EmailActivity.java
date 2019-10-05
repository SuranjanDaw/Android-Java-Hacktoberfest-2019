package com.example.booksharing;

import android.content.Intent;  
import android.os.Bundle;  
import android.support.v7.app.AppCompatActivity;  
import android.view.View;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.TextView;  
  
public class EmailActivity extends AppCompatActivity implements View.OnClickListener {  
  
    EditText inSubject, inBody;  
    TextView txtEmailAddress;  
    Button btnSendEmail;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_email);  
        initViews();  
    }  
  
    private void initViews() {  
        inSubject = findViewById(R.id.inSubject);  
        inBody = findViewById(R.id.inBody);  
        txtEmailAddress = findViewById(R.id.txtEmailAddress);  
        btnSendEmail = findViewById(R.id.btnSendEmail);  
  
        if (getIntent().getStringExtra("email_address") != null) {  
            txtEmailAddress.setText("Recipient : " + getIntent().getStringExtra("email_address"));  
        }  
  
        btnSendEmail.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                Intent intent = new Intent(Intent.ACTION_SEND);  
                intent.setType("text/plain");  
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{txtEmailAddress.getText().toString()});  
                intent.putExtra(Intent.EXTRA_SUBJECT, inSubject.getText().toString().trim());  
                intent.putExtra(Intent.EXTRA_TEXT, inBody.getText().toString().trim());  
  
                startActivity(Intent.createChooser(intent, "Send Email"));  
            }  
        });  
    }  
  
    @Override  
    public void onClick(View v) {  
  
        switch (v.getId()) {  
            case R.id.btnScanBarcode:  
                startActivity(new Intent(EmailActivity.this, ScannedBarcodeActivity.class));  
                break;  
        }  
    }  
}  
