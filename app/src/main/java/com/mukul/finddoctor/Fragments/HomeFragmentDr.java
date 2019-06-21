package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mukul.finddoctor.Activity.DrChatActivity;
import com.mukul.finddoctor.Activity.DrConfirmedActivity;
import com.mukul.finddoctor.Activity.DrPendingActivity;
import com.mukul.finddoctor.Activity.DrPrescriptionListActivity;
import com.mukul.finddoctor.Activity.OnlineDoctorsActivity;
import com.mukul.finddoctor.Activity.PrescriptionGivingActivity;
import com.mukul.finddoctor.Activity.RecheckActivityDr;
import com.mukul.finddoctor.Activity.SpecialistActivity;
import com.mukul.finddoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragmentDr extends Fragment  {
    View v;
    Context context;
    @BindView(R.id.linearNew)
    LinearLayout linearNew;
    @BindView(R.id.linerChat)
    LinearLayout linerChat;
    @BindView(R.id.linearPrescription)
    LinearLayout linearPrescription;
    @BindView(R.id.linearRecheck)
    LinearLayout linearRecheck;
    @BindView(R.id.linearPending)
    LinearLayout linearPending;




    public static HomeFragmentDr newInstance() {
        HomeFragmentDr fragment = new HomeFragmentDr();
        return fragment;
    }

    public HomeFragmentDr() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dr_fragment_one, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        linearNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(context, DrConfirmedActivity.class));
            }
        });
        linerChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DrChatActivity.class));
            }
        });
//DrPrescriptionListActivity
        linearPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DrPrescriptionListActivity.class));
            }
        });
//RecheckActivityDr

        linearRecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RecheckActivityDr.class));
            }
        });
//DrPendingActivity
        linearPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DrPendingActivity.class));
            }
        });
        //DrConfirmedActivity

        return v;
    }







}
