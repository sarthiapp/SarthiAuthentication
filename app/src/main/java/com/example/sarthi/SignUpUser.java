package com.example.sarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpUser extends AppCompatActivity {

    EditText user_first_name,user_last_name,user_age,user_address,user_phone;
    Button btnSaveUser;
    FirebaseAuth fauth=FirebaseAuth.getInstance();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Map<String,Object> users=new HashMap<>();
    FirebaseUser user=fauth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        user_first_name=findViewById(R.id.user_first_name);
        user_age=findViewById(R.id.user_age);
        user_address=findViewById(R.id.user_address);
        user_last_name=findViewById(R.id.user_last_name);
        user_phone=findViewById(R.id.user_phone);
        btnSaveUser=findViewById(R.id.btnSaveUser);
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidations()) {
                    users.put("name",user_first_name.getText().toString()+" "+user_last_name.getText().toString());
                    users.put("address",user_address.getText().toString());
                    users.put("age",user_age.getText().toString());
                    users.put("phone",user_phone.getText().toString());
                    users.put("type","user");
                    db.collection("data").document(user.getUid()).set(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SignUpUser.this, "Registration sucessfull.Please Check Your Mail Id", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUpUser.this,NewLogin.class);
                            intent.putExtra("phone",user_phone.getText().toString());
                            startActivity(intent);

                        }
                    });
                }
            }
        });

    }
    private boolean checkValidations() {
        if(TextUtils.isEmpty(user_first_name.getText().toString())){
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(user_phone.getText().toString())){
            Toast.makeText(this, "Please enter a phone Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(user_address.getText().toString())){
            Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(user_age.getText().toString())){
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}