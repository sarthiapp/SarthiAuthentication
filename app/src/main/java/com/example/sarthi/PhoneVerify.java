package com.example.sarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerify extends AppCompatActivity {
    Button btnVerify,resend;
    EditText otpNumberOne,getOtpNumberTwo,getOtpNumberThree,getOtpNumberFour,getOtpNumberFive,otpNumberSix;
    ProgressBar progressBar;
    private String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        btnVerify = findViewById(R.id.verifyPhoneBTn);
        resend = findViewById(R.id.resendOTP);
        otpNumberOne = findViewById(R.id.otpNumberOne);
        getOtpNumberTwo = findViewById(R.id.optNumberTwo);
        getOtpNumberThree = findViewById(R.id.otpNumberThree);
        getOtpNumberFour = findViewById(R.id.otpNumberFour);
        getOtpNumberFive = findViewById(R.id.otpNumberFive);
        otpNumberSix = findViewById(R.id.optNumberSix);
        progressBar = findViewById(R.id.progressBar);

        setotpcursor();
        verificationId = getIntent().getStringExtra("verificationId");
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otpNumberOne.getText().toString().trim().isEmpty()
                        || getOtpNumberTwo.getText().toString().trim().isEmpty()
                        || getOtpNumberThree.getText().toString().trim().isEmpty()
                        || getOtpNumberFour.getText().toString().trim().isEmpty()
                        || getOtpNumberFive.getText().toString().trim().isEmpty()
                        || otpNumberSix.getText().toString().trim().isEmpty()) {
                    Toast.makeText(PhoneVerify.this, "Please enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = otpNumberOne.getText().toString() + getOtpNumberTwo.getText().toString() + getOtpNumberThree.getText().toString() + getOtpNumberFour.getText().toString() +
                        getOtpNumberFive.getText().toString() + otpNumberSix.getText().toString();
                if (verificationId != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    btnVerify.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), NewLogin.class);
                                        //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);


                                    } else {
                                        Toast.makeText(PhoneVerify.this, "The Verification Code Was invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,                           // Timeout duration

                        TimeUnit.SECONDS,
                        PhoneVerify.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(PhoneVerify.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = s;
                                Toast.makeText(PhoneVerify.this, "Code sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });
    }

    public void setotpcursor() {
        otpNumberOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty())
                    getOtpNumberTwo.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getOtpNumberTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty())
                    getOtpNumberThree.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getOtpNumberThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty())
                    getOtpNumberFour.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getOtpNumberFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty())
                    getOtpNumberFive.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getOtpNumberFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty())
                    otpNumberSix.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}