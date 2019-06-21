package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mukul.finddoctor.Activity.AdviceActivityPatient;
import com.mukul.finddoctor.Activity.PatientDiseaseSumActivity;
import com.mukul.finddoctor.Activity.PatientPersonalInfoActivity;
import com.mukul.finddoctor.Activity.PrescriptionActivityPatient;
import com.mukul.finddoctor.Activity.TestRecomendationListActivity;
import com.mukul.finddoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFragment extends Fragment  {
    View v;
    Context context;
    @BindView(R.id.linearPersonal)
    LinearLayout linearPersonal;
    @BindView(R.id.linearTest)
    LinearLayout linearTest;
    @BindView(R.id.linearDisease)
    LinearLayout linearDisease;

    @BindView(R.id.linearPrescription)
    LinearLayout linearPrescription;



    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.profile_fragment, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        linearPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PatientPersonalInfoActivity.class));
            }
        });

        linearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, TestRecomendationListActivity.class));
            }
        });
        linearDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PatientDiseaseSumActivity.class));
            }
        });

        linearPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PrescriptionActivityPatient.class));
            }
        });



        return v;
    }

    //





}
