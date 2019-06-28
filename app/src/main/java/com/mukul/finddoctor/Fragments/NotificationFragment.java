package com.mukul.finddoctor.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Data.ListenerPatientsData;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.RecomendedTestAppointmentAdapterPatient;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.RecomentationModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationFragment extends Fragment implements ApiListener.patientNotificationDataDownloadListener{
    View v;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    RecomendedTestAppointmentAdapterPatient mAdapter;
    @BindView(R.id.tv_no_item)
    TextView tv_no_item;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this,v);
        ListenerPatientsData.setPatientNotificationDataDownloadListener(this);
        return  v;
    }


    @Override
    public void onNotificationDownloaded(List<RecomentationModel> status) {
//        mAdapter = new RecomendedTestAppointmentAdapterPatient(status);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recycler_view.setLayoutManager(mLayoutManager);
//        recycler_view.setItemAnimator(new DefaultItemAnimator());
//        recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//        recycler_view.setAdapter(mAdapter);
//        if (status.size()>0){
//            tv_no_item.setVisibility(View.GONE);
//        }else {
//            tv_no_item.setVisibility(View.VISIBLE);
//
//        }
    }
}
