package com.mukul.finddoctor.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;

import java.util.ArrayList;
import java.util.List;

public class DrAllAppointmentsActivity extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener{
    SessionManager sessionManager;
    public  static List<AppointmentModel> PENDING=new ArrayList<>();
    public  static List<AppointmentModel> CONFIRMED=new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_all_appointments);
        sessionManager=new SessionManager(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
       // progressDialog.show();
        //Api.getInstance().getAppointmentsByDoctor(sessionManager.getUserId(), this);

    }

    public void openApprovedDr(View view) {
        startActivity(new Intent(this,DrConfirmedActivity.class));

    }

    public void openPendingDr(View view) {
        startActivity(new Intent(this,DrPendingActivity.class));
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onAppointmentDownloadSuccess(AppointmentResponse data) {
        progressDialog.dismiss();
        PENDING=data.getNotConfirmed();
        CONFIRMED=data.getConfirmed();


    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        progressDialog.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
