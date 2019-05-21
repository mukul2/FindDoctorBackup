package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterPatientNew;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientPendingActivity extends AppCompatActivity {
    Context context=this;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_pending);
        ButterKnife.bind(this);
        PendingAppointmentAdapterPatientNew mAdapter = new PendingAppointmentAdapterPatientNew(AppointmentsActivityPatient.PENDING_LIST);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    public void Back(View view) {
        onBackPressed();
    }
}
