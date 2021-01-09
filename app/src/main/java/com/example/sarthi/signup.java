package com.example.sarthi;

import android.content.Context;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


public class signup extends Fragment {
    public static final String TAG = "TAG";
    private static final int RC_SIGN_IN =123 ;
    EditText personEmailAddress,personPass,personConfPass;
    Button signUpButton;
    ImageView btnGoogleSignIn;
    FirebaseAuth fAuth;
    GoogleSignInClient mGoogleSignInClient;
    String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_signup, container, false);
        personEmailAddress = v.findViewById(R.id.editTextTextPersonEmail2);
        personPass = v.findViewById(R.id.editTextTextPassword2);
        personConfPass = v.findViewById(R.id.editTextRetypePassword2);
        signUpButton = v.findViewById(R.id.button2);
        btnGoogleSignIn=v.findViewById(R.id.btnGoogleSignIn);
        fAuth = FirebaseAuth.getInstance();
        login_signup obj=new login_signup();
        type=obj.type;
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidations()) {
                    fAuth.createUserWithEmailAndPassword(personEmailAddress.getText().toString(),personPass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getActivity()," Account Is Created",Toast.LENGTH_SHORT).show();
                            FirebaseUser user =fAuth.getCurrentUser();
                            nextActivity();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( (login_signup)getActivity(),gso);

        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


        return v;
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
                Toast.makeText(getContext(), "could not sign in", Toast.LENGTH_SHORT).show();
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
        Intent intent1=new Intent(getActivity(),SignUpAttendant.class);
        startActivity(intent1);
        /*
        if(type.equals("user"))
        {
            Intent intent1=new Intent(getActivity(),SignUpUser.class);
            startActivity(intent1);
        }
        if(type.equals("driver"))
        {
            Intent intent1=new Intent(getActivity(),SignUpDriver.class);
            startActivity(intent1);
        }
        if(type.equals("attendant"))
        {
            Intent intent1=new Intent(getActivity(),SignUpAttendant.class);
            startActivity(intent1);
        }

         */
    }

    private boolean checkValidations() {
        if(TextUtils.isEmpty(personEmailAddress.getText().toString())){
            Toast.makeText(getContext(), "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personPass.getText().toString())){
            Toast.makeText(getContext(), "Create Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(personConfPass.getText().toString())){
            Toast.makeText(getContext(), "Create Your Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}