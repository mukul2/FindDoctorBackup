package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterPatientNew;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterPatientNew;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.RecomentationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;

public class PatientNewHome extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;

    SessionManager sessionManager;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_new_home);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        tv_name.setText(sessionManager.getUserName());
        USER_ID=sessionManager.getUserId();

    }



    private void setUpPendingRecycler(List<AppointmentModel> notConfirmed) {
//        PendingAppointmentAdapterPatientNew mAdapter = new PendingAppointmentAdapterPatientNew(notConfirmed);
//        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        pendingRecycler.setLayoutManager(layoutManager);
//        pendingRecycler.setItemAnimator(new DefaultItemAnimator());
//        pendingRecycler.setAdapter(mAdapter);
    }

    private void setupUpcommingRecycler(List<AppointmentModel> confirmed) {
//        ConfirmedAppointmentAdapterPatientNew mAdapter = new ConfirmedAppointmentAdapterPatientNew(confirmed);
//        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        upcomingRecycler.setLayoutManager(layoutManager);
//        upcomingRecycler.setItemAnimator(new DefaultItemAnimator());
//        upcomingRecycler.setAdapter(mAdapter);
    }



    public void openFindDoctor(View view) {
        startActivity(new Intent(context,FindDoctorActivity.class));
    }

    public void appointments(View view) {
        startActivity(new Intent(context,AppointmentsActivityPatient.class));

    }

    public void openVideoCall(View view) {
        startActivity(new Intent(context,VideoCallPatientActivity.class));

    }

    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this,LoginActivity.class));
        finishAffinity();
    }
}
