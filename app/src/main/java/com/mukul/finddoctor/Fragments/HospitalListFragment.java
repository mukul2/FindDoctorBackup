package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.finddoctor.Activity.DepartmentsActivity;
import com.mukul.finddoctor.Data.lis;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.HospitalsAdapter;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HospitalListFragment extends Fragment {
    View v;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context;

    public HospitalListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_fragment, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        initRecyclerView();

        return v;
    }

    private void initRecyclerView() {
        HospitalsAdapter mAdapter = new HospitalsAdapter(DepartmentsActivity.HOSPITALS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }


}
