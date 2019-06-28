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
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.DepartmentsAdapter;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterPatient;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModelNew;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.TOKEN;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;


public class PendingAppointmentsFragment extends Fragment  implements ApiListener.appoinetmentsDownloadListener{
    View v;
    Context context;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;



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
        v = inflater.inflate(R.layout.patient_pending, container, false);
        context=v.getContext();
        ButterKnife.bind(this,v);
        Api.getInstance().getAppointments( TOKEN,"patient",USER_ID,"0",this);





        return v;
    }


    @Override
    public void onAppointmentDownloadSuccess(List<AppointmentModelNew> status) {
        Toast.makeText(context, ""+status.size(), Toast.LENGTH_SHORT).show();
        PendingAppointmentAdapterPatient mAdapter = new PendingAppointmentAdapterPatient(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
