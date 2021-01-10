package com.example.sarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btnUser,btnAttendant,btnDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
        }

        btnUser = (Button) findViewById(R.id.btnUser);
        btnAttendant = (Button) findViewById(R.id.btnAttendant);
        btnDriver = (Button) findViewById(R.id.btnDriver);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewSignup.class);
                intent.putExtra("type","user");
                startActivity(intent);
            }
        });
        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewSignup.class);
                intent.putExtra("type","driver");
                startActivity(intent);
            }
        });
        btnAttendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewSignup.class);
                intent.putExtra("type","attendant");
                startActivity(intent);
            }
        });

    }
}