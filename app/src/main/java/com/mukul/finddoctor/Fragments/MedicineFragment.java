package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Activity.CallLogHistoryActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.CurrentlyOnlineDoctorAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.VideoCallModel;
import com.mukul.finddoctor.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MedicineFragment extends Fragment  {
    View v;
    Context context;


    public static MedicineFragment newInstance() {
        MedicineFragment fragment = new MedicineFragment();
        return fragment;
    }

    public MedicineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_medicine, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);



        return v;
    }





}
