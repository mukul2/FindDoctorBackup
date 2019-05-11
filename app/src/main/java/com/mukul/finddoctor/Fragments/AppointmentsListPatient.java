package com.mukul.finddoctor.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.finddoctor.Data.ListenerPatientsData;
import com.mukul.finddoctor.Data.lis;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterPatient;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterPatient;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppointmentsListPatient extends Fragment implements ApiListener.patientAllDataDownloadListener {
    View v;
    @BindView(R.id.recycler_view_confirmed)
    RecyclerView recycler_view_confirmed;
    @BindView(R.id.recycler_view_pending)
    RecyclerView recycler_view_pending;
    ConfirmedAppointmentAdapterPatient confirmedAdapter;
    PendingAppointmentAdapterPatient pendingAdapter;
    @BindView(R.id.tv_confirmed)
    TextView tv_confirmed;
    @BindView(R.id.tv_all)
    TextView tv_all;
    int ALL_SHOWING = 1;
    int CONFIRMED = 0;
    int showing = CONFIRMED;
    @BindView(R.id.tv_no_item)
    TextView tv_no_item;
    int pendingCount=0;
    int ConfirmedCount=0;

    public AppointmentsListPatient() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_appointment_patient, container, false);

        ButterKnife.bind(this, v);
        tv_confirmed.setOnClickListener((View v) -> selectConfirmed());
        tv_all.setOnClickListener((View v) -> selectPending());
        ListenerPatientsData.setPatientALLDataDownloadListener(this);
        return v;
    }

    private void selectPending() {
        tv_all.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rect_right_fill_corner_primary));
        tv_all.setTextColor(Color.WHITE);
        tv_confirmed.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rect_right_fill_corner_transparent));
        tv_confirmed.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        recycler_view_pending.setVisibility(View.VISIBLE);
        recycler_view_confirmed.setVisibility(View.GONE);
        if (pendingCount>0){
            tv_no_item.setVisibility(View.GONE);
        }else {
            tv_no_item.setVisibility(View.VISIBLE);

        }

    }

    private void selectConfirmed() {
        tv_all.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rect_right_fill_corner_transparent));
        tv_all.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        tv_confirmed.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rect_left_fill_corner_primary));
        tv_confirmed.setTextColor(Color.WHITE);
        recycler_view_pending.setVisibility(View.GONE);
        recycler_view_confirmed.setVisibility(View.VISIBLE);
        if (ConfirmedCount>0){
            tv_no_item.setVisibility(View.GONE);
        }else {
            tv_no_item.setVisibility(View.VISIBLE);

        }


    }


    @Override
    public void onDownloaded(AppointmentResponse status) {
        pendingCount=status.getNotConfirmed().size();
        ConfirmedCount=status.getConfirmed().size();
        if (ConfirmedCount>0){
            tv_no_item.setVisibility(View.GONE);
        }else {
            tv_no_item.setVisibility(View.VISIBLE);

        }

        confirmedAdapter = new ConfirmedAppointmentAdapterPatient(status.getConfirmed());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycler_view_confirmed.setLayoutManager(mLayoutManager);
        recycler_view_confirmed.setItemAnimator(new DefaultItemAnimator());
        //recycler_view_confirmed.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recycler_view_confirmed.setAdapter(confirmedAdapter);

        pendingAdapter = new PendingAppointmentAdapterPatient(status.getNotConfirmed());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext());
        recycler_view_pending.setLayoutManager(mLayoutManager1);
        recycler_view_pending.setItemAnimator(new DefaultItemAnimator());
        //recycler_view_pending.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recycler_view_pending.setAdapter(pendingAdapter);
        selectConfirmed();

    }
}
