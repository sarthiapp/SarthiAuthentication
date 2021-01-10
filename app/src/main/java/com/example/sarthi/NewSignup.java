package com.example.sarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class NewSignup extends AppCompatActivity {
    public static final String TAG = "TAG";
    private static final int RC_SIGN_IN =123 ;
    EditText personEmailAddress,personPass,personConfPass;
    Button signUpButton;
    ImageView btnGoogleSignIn;
    FirebaseAuth fAuth;
    GoogleSignInClient mGoogleSignInClient;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);
        personEmailAddress = findViewById(R.id.editTextTextPersonEmail2);
        personPass = findViewById(R.id.editTextTextPassword2);
        personConfPass = findViewById(R.id.editTextRetypePassword2);
        signUpButton = findViewById(R.id.button2);
        btnGoogleSignIn=findViewById(R.id.btnGoogleSignIn);
        fAuth = FirebaseAuth.getInstance();
        Intent intent=getIntent();
        type=intent.getStringExtra("type");


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidations()){

                    fAuth.createUserWithEmailAndPassword(personEmailAddress.getText().toString(),personPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(NewSignup.this, "Registration successful.Please check your email for verification", Toast.LENGTH_SHORT).show();
                                            nextActivity();
                                        }
                                        else {
                                            Toast.makeText(NewSignup.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });


                            } else {
                                Toast.makeText(NewSignup.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this,gso);

        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (Exception e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, "could not sign in", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            nextActivity();
                        } else {
                            Log.d("error","error");
                        }
                    }
                });
    }


    private void nextActivity()
    {
        /*Intent intent1=new Intent(getActivity(),SignUpAttendant.class);
        startActivity(intent1);
        */
        switch (type) {
            case "user": {
                Intent intent1 = new Intent(this, SignUpUser.class);
                startActivity(intent1);
                break;
            }
            case "driver": {
                Intent intent1 = new Intent(this, SignUpDriver.class);
                startActivity(intent1);
                break;
            }
            case "attendant": {
                Intent intent1 = new Intent(this, SignUpAttendant.class);
                startActivity(intent1);
                break;
            }
        }


    }

    private boolean checkValidations() {
        if(TextUtils.isEmpty(personEmailAddress.getText().toString())){
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personPass.getText().toString())){
            Toast.makeText(this, "Create Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personConfPass.getText().toString())){
            Toast.makeText(this, "Create Your Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

