package com.example.sarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpAttendant extends AppCompatActivity {

    EditText attendant_first_name,attendant_last_name,attendant_phone,attendant_age;
    Button btnSaveAttendant;
    RadioGroup radioGender;
    RadioButton radioButton;
    int selected;
    FirebaseAuth fauth=FirebaseAuth.getInstance();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Map<String,Object> attendants=new HashMap<>();
    FirebaseUser attendant=fauth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_attendant);
        attendant_first_name=findViewById(R.id.attendant_first_name);
        attendant_last_name=findViewById(R.id.attendant_last_name);
        attendant_phone=findViewById(R.id.attendant_phone);
        attendant_age=findViewById(R.id.attendant_age);
        btnSaveAttendant=findViewById(R.id.btnSaveAttendant);
        radioGender=findViewById(R.id.radioGender);
        btnSaveAttendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=radioGender.getCheckedRadioButtonId();
                radioButton=findViewById(selected);
                if(checkValidations()) {
                    attendants.put("name",attendant_first_name.getText().toString()+" "+attendant_last_name.getText().toString());
                    attendants.put("age",attendant_age.getText().toString());
                    attendants.put("phone",attendant_phone.getText().toString());
                    attendants.put("gender",radioButton.getText().toString());
                    attendants.put("type","attendant");
                    db.collection("data").document(attendant.getUid()).set(attendants).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent=new Intent(SignUpAttendant.this,NewLogin.class);
                            intent.putExtra("phone",attendant_phone.getText().toString());
                            startActivity(intent);

                        }
                    });
                }
            }
        });
    }
    private boolean checkValidations() {
        if(TextUtils.isEmpty(attendant_first_name.getText().toString())){
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(attendant_phone.getText().toString())){
            Toast.makeText(this, "Please enter a phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(attendant_age.getText().toString())){
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(selected==-1)
        {
            Toast.makeText(this, "Please choose gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}