package com.mukul.finddoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.UserProfileResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrPersonalInfoActivity extends AppCompatActivity implements ApiListener.profileDownloadListener{
    SessionManager sessionManager;
    @BindView(R.id.tv_currentlyworking)
    TextView tv_currentlyworking;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_designation)
    TextView tv_designation;
    String USER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_personal_info);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        setTitle(sessionManager.getUserName());
        USER_ID=sessionManager.getUserId();
        MyProgressBar.with(DrPersonalInfoActivity.this);
        Api.getInstance().getThisPfofile(USER_ID,this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onprofileDownloadSuccess(UserProfileResponse list) {
        MyProgressBar.dismiss();
        tv_currentlyworking.setText(list.getHospitalName());
        tv_name.setText(list.getDrName());
        tv_designation.setText(list.getLastEducationDegree());

    }

    @Override
    public void onprofileDownloadFailed(String msg) {
        MyProgressBar.dismiss();


    }
}
