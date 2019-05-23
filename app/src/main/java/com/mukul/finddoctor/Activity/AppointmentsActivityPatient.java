package com.mukul.finddoctor.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.RecomentationModel;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivityPatient extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener {
    SessionManager sessionManager;
    Context context = this;
    public static List<AppointmentModel> PENDING_LIST=new ArrayList<>();
    public static List<AppointmentModel> CONFIRMED_LIST=new ArrayList<>();
    public static List<RecomentationModel> RECOMENDED_LIST=new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments_patient);
        sessionManager=new SessionManager(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait");
       // progressDialog.show();
        //Api.getInstance().getAppointmentsBypatient(sessionManager.getUserId(), this);

    }

    public void openApproved(View view) {
        startActivity(new Intent(this, PatientConfirmedActivity.class));
    }

    public void openPending(View view) {
        startActivity(new Intent(this, PatientPendingActivity.class));
    }

    public void openTestRecomendtions(View view) {
        startActivity(new Intent(this, PatientTestRecomActivity.class));

    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onAppointmentDownloadSuccess(AppointmentResponse data) {
        progressDialog.dismiss();
        PENDING_LIST=data.getNotConfirmed();
        CONFIRMED_LIST=data.getNotConfirmed();
        RECOMENDED_LIST=data.getNotification();
    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        progressDialog.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
