package com.example.sarthi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signup extends Fragment implements View.OnClickListener {
    public static final String TAG = "TAG";
    EditText personFullName,personEmailAddress,personPass,personConfPass,personPhoneNumber;
    Button signUpButton;
    Boolean isDataValid = false;
    FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_signup, container, false);
        personFullName = v.findViewById(R.id.editTextTextPersonName2);
        personEmailAddress = v.findViewById(R.id.editTextTextPersonEmail2);
        personPass = v.findViewById(R.id.editTextTextPassword2);
        personConfPass = v.findViewById(R.id.editTextRetypePassword2);
        personPhoneNumber = v.findViewById(R.id.phoneNumber2);
        signUpButton = v.findViewById(R.id.button2);
        signUpButton.setOnClickListener(this);

        fAuth = FirebaseAuth.getInstance();

       /* validateData(personFullName);
        validateData(personEmailAddress);
        validateData(personPass);
        validateData(personConfPass);
        validateData(personPhoneNumber);
*/
        /*if(!personPass.getText().toString().equals(personConfPass.getText().toString())){
            isDataValid = false;
            personConfPass.setError("Password Do not Match");
        }else {
            isDataValid = true;
        }*/


        return v;
    }


    @Override
    public void onClick(View view) {
        if (checkValidations()) {
            fAuth.createUserWithEmailAndPassword(personEmailAddress.getText().toString(),personPass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getActivity()," Account Is Created",Toast.LENGTH_SHORT).show();
                    Intent phone = new Intent(getActivity(),verifyo_otp.class);
                    phone.putExtra("phone",personPhoneNumber.getText().toString());
                    startActivity(phone);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private boolean checkValidations() {
        if(TextUtils.isEmpty(personFullName.getText().toString())){
            Toast.makeText(getContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personEmailAddress.getText().toString())){
            Toast.makeText(getContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personConfPass.getText().toString())){
            Toast.makeText(getContext(), "Create Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personPass.getText().toString())){
            Toast.makeText(getContext(), "Confirm your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personPhoneNumber.getText().toString())){
            Toast.makeText(getContext(), "Enter Your Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}