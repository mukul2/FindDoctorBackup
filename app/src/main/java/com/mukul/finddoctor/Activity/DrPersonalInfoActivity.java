package com.mukul.finddoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.StatusResponse;
import com.mukul.finddoctor.model.UserProfileResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrPersonalInfoActivity extends AppCompatActivity implements ApiListener.profileDownloadListener,
        ApiListener.drprofileUpdateListener {
    SessionManager sessionManager;
    @BindView(R.id.tv_currentlyworking)
    EditText tv_currentlyworking;
    @BindView(R.id.tv_name)
    EditText tv_name;
    @BindView(R.id.tv_designation)
    EditText tv_designation;
    String USER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_personal_info);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        setTitle(sessionManager.getUserName());
        USER_ID = sessionManager.getUserId();
        MyProgressBar.with(DrPersonalInfoActivity.this);
        Api.getInstance().getThisPfofile(USER_ID, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onprofileDownloadSuccess(UserProfileResponse list) {
        MyProgressBar.dismiss();
        tv_currentlyworking.setText(list.getHospitalName());
        tv_name.setText(list.getDrName());
        tv_designation.setText(list.getLastEducationDegree());
        sessionManager.setuserName(list.getDrName());


    }

    @Override
    public void onprofileDownloadFailed(String msg) {
        MyProgressBar.dismiss();


    }

    public void UpdateProfile(View view) {
        MyProgressBar.with(DrPersonalInfoActivity.this);
        String hospital = tv_currentlyworking.getText().toString().trim();
        String degree = tv_designation.getText().toString().trim();
        String name = tv_name.getText().toString().trim();
        Api.getInstance().updateDrInfo(USER_ID, hospital, degree, name, this);
    }

    @Override
    public void ondrprofileUpdateSuccess(StatusResponse list) {
        MyProgressBar.dismiss();
        if (list.getStatus()) {
            String name = tv_name.getText().toString().trim();

            Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show();
            sessionManager.setuserName(name);
        } else {
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ondrprofileUpdateFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();


    }

    public void back(View view) {
        onBackPressed();
    }
}
