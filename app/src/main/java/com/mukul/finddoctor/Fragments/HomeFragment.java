package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mukul.finddoctor.Activity.OnlineDoctorsActivity;
import com.mukul.finddoctor.Activity.PersonalPhysicianAcrivity;
import com.mukul.finddoctor.Activity.RequestReviewActivityPatient;
import com.mukul.finddoctor.Activity.SpecialistActivity;
import com.mukul.finddoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends Fragment  {
    View v;
    Context context;
    @BindView(R.id.cardChember)
    CardView cardChember;
    @BindView(R.id.cardOnline)
    CardView cardOnline;
    @BindView(R.id.cardReview)
    CardView cardReview;
    @BindView(R.id.cardPhysician)
    CardView cardPhysician;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        cardChember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context. startActivity(new Intent(context, SpecialistActivity.class));
              //  Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }

    @OnClick(R.id.cardOnline)
    public  void  openOnlineDoctors(){
       startActivity(new Intent(context, OnlineDoctorsActivity.class));
    }
    @OnClick(R.id.cardReview)
    public  void  openReviewReview(){
      startActivity(new Intent(context, RequestReviewActivityPatient.class));
    }
    //PersonalPhysicianAcrivity

    @OnClick(R.id.cardPhysician)
    public  void  openPersonalPhysician(){
        startActivity(new Intent(context, PersonalPhysicianAcrivity.class));
    }




}
