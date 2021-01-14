package com.example.sarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button btnUser,btnAttendant,btnDriver;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            login();
        }

        btnUser = (Button) findViewById(R.id.btnUser);
        btnAttendant = (Button) findViewById(R.id.btnAttendant);
        btnDriver = (Button) findViewById(R.id.btnDriver);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhoneSignup.class);
                intent.putExtra("type","user");
                startActivity(intent);
            }
        });
        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhoneSignup.class);
                intent.putExtra("type","driver");
                startActivity(intent);
            }
        });
        btnAttendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhoneSignup.class);
                intent.putExtra("type","attendant");
                startActivity(intent);
            }
        });

    }

    private void login() {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        DocumentReference dref=db.collection("data").document(user.getUid());
        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                         String type=document.get("type").toString();
                        if(type.equals("user"))
                        {
                            Toast.makeText(MainActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,User_Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else if(type.equals("driver"))
                        {
                            Toast.makeText(MainActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Driver_Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else if(type.equals("Attendant"))
                        {
                            Toast.makeText(MainActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Attendant_Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "document does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "get failed with "+ task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}