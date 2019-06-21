package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mukul.finddoctor.R;


public class PendingAppointmentsFragment extends Fragment  {
    View v;
    Context context;



    public static PendingAppointmentsFragment newInstance() {
        PendingAppointmentsFragment fragment = new PendingAppointmentsFragment();
        return fragment;
    }

    public PendingAppointmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.appointment_fragment, container, false);
        context=v.getContext();




        return v;
    }





}
