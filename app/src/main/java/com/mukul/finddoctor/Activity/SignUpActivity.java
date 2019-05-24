package com.mukul.finddoctor.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.BasicByDrResponse;
import com.mukul.finddoctor.model.StatusId;
import com.mukul.finddoctor.model.StatusResponse;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements ApiListener.drBasicInfoPostListener,
ApiListener.CheckMobileListener,ApiListener.testNamesDownloadListener{
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_currentlyworking)
    EditText ed_currentlyworking;
    @BindView(R.id.ed_degree)
    EditText ed_degree;
    @BindView(R.id.ed_mobile)
    EditText ed_mobile;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_password)
    EditText ed_password;
    ProgressDialog progressDialog;
    String name,email,mobile,password;
    @BindView(R.id.linearDoctor)
    LinearLayout linearDoctor;
    @BindView(R.id.linearPaitent)
    LinearLayout linearPaitent;

    @BindView(R.id.img_doctor)
    ImageView img_doctor;
    @BindView(R.id.img_patient)
    ImageView img_patient;

    @BindView(R.id.tv_patient)
    TextView tv_patient;
    @BindView(R.id.tv_doctor)
    TextView tv_doctor;


    int DOCTOR=0;
    int PATIENT=1;
    int selctedUserType=PATIENT;
    Context context=this;
    SessionManager sessionManager;

    String DOCTOR_="d";
    String PATIENT_="p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        updateTypeUI();
        linearDoctor.setOnClickListener((View v)->moveToDoctor());
        linearPaitent.setOnClickListener((View v)->moveToPatient());
    }

    private void moveToPatient() {
        selctedUserType=PATIENT;
        updateTypeUI();
        ed_degree.setVisibility(View.GONE);
        ed_degree.setText("Not Applicable");
        ed_currentlyworking.setVisibility(View.GONE);
        ed_currentlyworking.setText("Not Applicable");

    }

    private void moveToDoctor() {
        selctedUserType=DOCTOR;
        updateTypeUI();
        ed_degree.setText("");
        ed_currentlyworking.setText("");
        ed_degree.setVisibility(View.VISIBLE);
        ed_currentlyworking.setVisibility(View.VISIBLE);

    }

    private void updateTypeUI() {
        if (selctedUserType==DOCTOR){
           setDoctorUI();
        }else if (selctedUserType==PATIENT){
            setPatientUI();
        }
    }

    private void setPatientUI() {


        tv_patient.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        tv_doctor.setTextColor(context.getResources().getColor(R.color.textColor));


        int sdk = android.os.Build.VERSION.SDK_INT;

        img_doctor.setImageResource(R.drawable.doctor);
        img_patient.setImageResource(R.drawable.patient);

        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            linearPaitent.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_selected) );
            linearDoctor.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_unselected) );
        } else {
            linearPaitent.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_selected));
            linearDoctor.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_unselected));

        }
    }

    private void setDoctorUI() {

        img_doctor.setImageResource(R.drawable.doctor_color);
        img_patient.setImageResource(R.drawable.patient_grey);
        tv_doctor.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        tv_patient.setTextColor(context.getResources().getColor(R.color.textColor));


        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            linearDoctor.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_selected) );
            linearPaitent.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_unselected) );
        } else {
            linearDoctor.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_selected));
            linearPaitent.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_unselected));
        }
    }

    public void OpenHomeActivity(View view) {
        startActivity(new Intent(this,HomeActivity.class));
    }

    public void onSubmit(View view) {
         name=ed_name.getText().toString().trim();
         mobile=ed_mobile.getText().toString().trim();
         email=ed_email.getText().toString().trim();
         password=ed_password.getText().toString().trim();
        if (name.length()>0 && mobile.length()>0 && email.length()>0 && password.length()>0){
            progressDialog.show();

            Api.getInstance().checkMobile(mobile,this);

        }
    }

    @Override
    public void onBasicInfoPostSuccess(StatusId data) {
        progressDialog.dismiss();
        if (data.getStatus()){
            sessionManager.setuserName(name);
            sessionManager.setuserId(""+data.getId());
            sessionManager.setLoggedIn(true);
            String type="";
            if (selctedUserType==DOCTOR){
                type=DOCTOR_;
            }else type=PATIENT_;
            sessionManager.setuserType(type);

            if (type.equals(DOCTOR_)) {
                startDownloadTestNames();
                startActivity(new Intent(SignUpActivity.this, DrNewHomeActivity.class));
                finishAffinity();
            }else {
                startActivity(new Intent(SignUpActivity.this, PatientNewHome.class));
                finishAffinity();

            }

            //MyDialog.getInstance().with(this).autoBack(false).autoDismiss(false).message("Your account is successfully created").moveToLogin();

        }else {
            MyDialog.getInstance().with(this).autoBack(false).autoDismiss(false).message("Error ocured.Try again").showMsgOnly();

        }
    }
    private void startDownloadTestNames() {
        Api.getInstance().downloadTestNames(this);
    }
    @Override
    public void onBasicInfoPostFailed(String msg) {
        progressDialog.dismiss();
        MyDialog.getInstance().with(this).autoBack(false).autoDismiss(false).message(msg).showMsgOnly();


    }

    @Override
    public void onMobileCheckSuccess(StatusResponse status) {
        if (status.getStatus()){
            //Toast.makeText(this, "This Mobile Exists", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

            MyDialog.getInstance().with(SignUpActivity.this)
                    .message("This Mobile number is allready registered")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }else {
            String type="";
            if (selctedUserType==DOCTOR){
                type=DOCTOR_;
            }else type=PATIENT_;
            String currentHospital=ed_currentlyworking.getText().toString().trim();
            String last_degree=ed_degree.getText().toString().trim();
               Api.getInstance().entryGeneralInfoDoctor(name,email,mobile,password,type,last_degree,currentHospital,this);
        }
    }

    @Override
    public void onMobileCheckFailed(String msg) {

    }
    @Override
    public void ontestNamesDownloadSuccess(BasicByDrResponse data) {
        DataStore.testModelList.clear();
        //   Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();
        for (int i=0;i<data.getTestNames().size();i++){
            DataStore.testModelList.add(new testSelectedModel(false,data.getTestNames().get(i)));
        }
    }

    @Override
    public void ontestNamesDownloadFailed(String msg) {

    }

    public void back(View view) {
        onBackPressed();
    }
}
