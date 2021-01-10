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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpDriver extends AppCompatActivity {
    EditText driver_first_name,driver_last_name,driver_model,driver_car_number,driver_phone;
    Button btnSaveDriver;
    FirebaseAuth fauth=FirebaseAuth.getInstance();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Map<String,Object> drivers=new HashMap<>();
    FirebaseUser driver=fauth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_driver);
        driver_first_name=findViewById(R.id.driver_first_name);
        driver_model=findViewById(R.id.driver_model);
        driver_car_number=findViewById(R.id.driver_car_number);
        driver_last_name=findViewById(R.id.driver_last_name);
        driver_phone=findViewById(R.id.driver_phone);
        btnSaveDriver=findViewById(R.id.btnSaveDriver);
        btnSaveDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidations()) {
                drivers.put("name",driver_first_name.getText().toString()+" "+driver_last_name.getText().toString());
                drivers.put("car number",driver_car_number.getText().toString());
                drivers.put("car model",driver_model.getText().toString());
                drivers.put("phone",driver_phone.getText().toString());
                drivers.put("type","driver");
                db.collection("data").document(driver.getUid()).set(drivers).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(SignUpDriver.this,NewLogin.class);
                        intent.putExtra("phone",driver_phone.getText().toString());
                        startActivity(intent);

                    }
                });
            }
            }
        });
    }
    private boolean checkValidations() {
        if(TextUtils.isEmpty(driver_first_name.getText().toString())){
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(driver_phone.getText().toString())){
            Toast.makeText(this, "Please enter a phone Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(driver_car_number.getText().toString())){
            Toast.makeText(this, "Please enter your car number", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(driver_model.getText().toString())){
            Toast.makeText(this, "Please enter model of your car", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}