package com.example.sarthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RidesFragment extends Fragment{

    public RidesFragment(){

    }

    RecyclerView recyclerView;
    MyAdapter myAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_rides, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myAdapter = new MyAdapter(getContext(), getMyList());
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();



        return view;
    }
   private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m1 = new Model();
       /* Model m2 = new Model();
        Model m3 = new Model();
        Model m4 = new Model();
        Model m5 = new Model();
        Model m6 = new Model();
        Model m7 = new Model();
        Model m8 = new Model();
        Model m9 = new Model();
        Model m10 = new Model();
*/
        m1.setDay_time("Sun, Dec 01, 01:30 PM");
        m1.setCarNumber("Mini.CRN 3856435623");
        m1.setCost("Rs.175");
        m1.setLocation("Anand Vihar I.S.B.T,plot Alpha...");
        m1.setImg(R.drawable.ic_taxi);
        models.add(m1);

  /*      m2.setDay_time("Wed, Nov 11, 05:30 PM");
        m2.setCarNumber("micro.CRN 8765398765");
        m2.setCost("Rs.250");
        m2.setLocation("Shipra Mall,Vaibhav Khand Indirapuram..");
        m2.setImg(R.drawable.ic_taxi);
        models.add(m2);

        m3.setDay_time("Sat, Feb 15, 06:30 PM");
        m3.setCarNumber("mini.CRN 3586479675");
        m3.setCost("Rs.320");
        m3.setLocation("Wave City center, Gate no.2,..");
        m3.setImg(R.drawable.ic_taxi);
        models.add(m3);

        m4.setDay_time("Thu, Jan 12, 3:30 PM");
        m4.setCarNumber("micro.CRN 4563728910");
        m4.setCost("Rs.430");
        m4.setLocation("Aravali Apartments,Block D,..");
        m4.setImg(R.drawable.ic_taxi);
        models.add(m4);

        m5.setDay_time("Sun, Mar 03, 01:30 PM");
        m5.setCarNumber("mini.CRN 4536279765");
        m5.setCost("Rs.300");
        m5.setLocation("Noida Rd, Sector 39A,..");
        m5.setImg(R.drawable.ic_taxi);
        models.add(m5);

        m6.setDay_time("Fri, Dec 11, 07:30 PM");
        m6.setCarNumber("mini.CRN 4536271345");
        m6.setCost("Rs.290");
        m6.setLocation("Akshardham, Noida Link rd,..");
        m6.setImg(R.drawable.ic_taxi);
        models.add(m6);

        m7.setDay_time("Wed, Dec 23, 01:40 PM");
        m7.setCarNumber("micro.CRN 1234567854");
        m7.setCost("Rs.430");
        m7.setLocation("Inter state bus terminal..");
        m7.setImg(R.drawable.ic_taxi);
        models.add(m7);

        m8.setDay_time("Sun, Dec 01, 01:30 PM");
        m8.setCarNumber("mini.CRN 4532675432");
        m8.setCost("Rs.320");
        m8.setLocation("Jain road, Sector 39A,..");
        m8.setImg(R.drawable.ic_taxi);
        models.add(m8);

        m9.setDay_time("Thu, May 12, 3:30 PM");
        m9.setCarNumber("Micro.CRn 4536271876");
        m9.setCost("Rs.345");
        m9.setLocation("Noida Rd, sector 39A,..");
        m9.setImg(R.drawable.ic_taxi);
        models.add(m9);

        m10.setDay_time("Sun, Dec 05, 04:40 PM");
        m10.setCarNumber("Mini.CRN 4532675432");
        m10.setCost("Rs.254");
        m10.setLocation("Aravali Apartments,Block D,..");
        m10.setImg(R.drawable.ic_taxi);
        models.add(m10);
        */
        return models;
    }

}

