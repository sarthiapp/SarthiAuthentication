package com.example.sarthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyProfileFragment extends Fragment {

    TextView tvTypeUser,tvNameUser,tvAgeUser,tvAddressUser,tvPhoneUser,tvEmailUser;
    TextView tvTypeDriver,tvNameDriver,tvModelDriver,tvNumberDriver,tvEmailDriver,tvPhoneDriver,tvRadiusDriver;
    TextView tvTypeAttendant,tvNameAttendant,tvGenderAttendant,tvEducationAttendant,tvEmailAttendant,tvPhoneAttendant,tvPastAttendant,tvAgeAttendant;
    LinearLayout layout_user,layout_driver,layout_attendant;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_my_profile, container, false);
        tvTypeUser=v.findViewById(R.id.tvTypeUser);
        tvNameUser=v.findViewById(R.id.tvNameUser);
        tvAddressUser=v.findViewById(R.id.tvAddressUser);
        tvAgeUser=v.findViewById(R.id.tvAgeUser);
        tvPhoneUser=v.findViewById(R.id.tvPhoneUser);
        tvEmailUser=v.findViewById(R.id.tvEmailUser);
        tvTypeDriver=v.findViewById(R.id.tvTypeDriver);
        tvNameDriver=v.findViewById(R.id.tvNameDriver);
        tvModelDriver=v.findViewById(R.id.tvModelDriver);
        tvNumberDriver=v.findViewById(R.id.tvNumberDriver);
        tvEmailDriver=v.findViewById(R.id.tvEmailDriver);
        tvPhoneDriver=v.findViewById(R.id.tvPhoneDriver);
        tvRadiusDriver=v.findViewById(R.id.tvRadiusDriver);
        tvTypeAttendant=v.findViewById(R.id.tvTypeAttendant);
        tvNameAttendant=v.findViewById(R.id.tvNameAttendant);
        tvGenderAttendant=v.findViewById(R.id.tvGenderAttendant);
        tvEducationAttendant=v.findViewById(R.id.tvEducationAttendant);
        tvEmailAttendant=v.findViewById(R.id.tvEmailAttendant);
        tvPhoneAttendant=v.findViewById(R.id.tvPhoneAttendant);
        tvPastAttendant=v.findViewById(R.id.tvPastAttendant);
        tvAgeAttendant=v.findViewById(R.id.tvAgeAttendant);
        layout_user=v.findViewById(R.id.layout_user);
        layout_driver=v.findViewById(R.id.layout_driver);
        layout_attendant=v.findViewById(R.id.layout_attendant);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        DocumentReference dref=db.collection("data").document(firebaseUser.getUid());
        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists())
                    {
                        set(document);
                    }
                }
            }
        });
        return v;
    }

    private void set(DocumentSnapshot document) {
        if(document.get("type").toString().equals("user"))
        {
            layout_user.setVisibility(View.VISIBLE);
            tvTypeUser.setText(document.get("type").toString());
            tvNameUser.setText(document.get("name").toString());
            tvAgeUser.setText(document.get("age").toString());
            tvAddressUser.setText(document.get("address").toString());
            tvPhoneUser.setText(document.get("phone").toString());
            tvEmailUser.setText(document.get("email").toString());
        }
        else if(document.get("type").toString().equals("driver"))
        {
            layout_driver.setVisibility(View.VISIBLE);
            tvTypeDriver.setText(document.get("type").toString());
            tvNameDriver.setText(document.get("name").toString());
            tvModelDriver.setText(document.get("car model").toString());
            tvNumberDriver.setText(document.get("car number").toString());
            tvPhoneDriver.setText(document.get("phone").toString());
            tvEmailDriver.setText(document.get("email").toString());
            tvRadiusDriver.setText(document.get("radius").toString());
        }
        else if(document.get("type").toString().equals("attendant"))
        {
            layout_attendant.setVisibility(View.VISIBLE);
            tvTypeAttendant.setText(document.get("type").toString());
            tvNameAttendant.setText(document.get("name").toString());
            tvGenderAttendant.setText(document.get("gender").toString());
            tvAgeAttendant.setText(document.get("age").toString());
            tvPhoneAttendant.setText(document.get("phone").toString());
            tvEmailAttendant.setText(document.get("email").toString());
            tvEducationAttendant.setText(document.get("education").toString());
            tvPastAttendant.setText(document.get("experience").toString());
        }
    }
}
