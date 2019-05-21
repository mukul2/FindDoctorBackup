package com.mukul.finddoctor.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.LoginResponse;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ApiListener.LoginUserListener,ApiListener.testNamesDownloadListener{
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_password)
    EditText ed_password;
    String phone,password;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String DOCTOR="d";
    String PATIENT="p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager=new SessionManager(this);
        ButterKnife.bind(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
    }

    public void openSignUpActivity(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
    }

    public void login(View view) {
        phone=ed_phone.getText().toString().trim();
        password=ed_password.getText().toString().trim();

        if (phone.length()>0 && password.length()>0){
            progressDialog.show();
            Api.getInstance().loginUser(phone,password,this);

        }


    }

    @Override
    public void onUserLoginSuccess(LoginResponse status) {
        progressDialog.dismiss();
        if (status.getStatus()){
            sessionManager.setuserId(status.getId());
            sessionManager.setLoggedIn(true);
            sessionManager.setuserName(status.getName());
            sessionManager.setuserType(status.getType());
            sessionManager.setuserId(status.getId());
          //  Toast.makeText(this, status.getId(), Toast.LENGTH_SHORT).show();
            if (status.getType().equals(DOCTOR)) {
                startDownloadTestNames();
                startActivity(new Intent(LoginActivity.this, DrNewHomeActivity.class));
                finishAffinity();
            }else {
                startActivity(new Intent(LoginActivity.this, PatientNewHome.class));
                finishAffinity();

            }


        }else {
            MyDialog.getInstance().with(LoginActivity.this)
                    .message("Wrong mobile or password")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }
    }

    private void startDownloadTestNames() {
        Api.getInstance().downloadTestNames(this);
    }

    @Override
    public void onUserLoginFailed(String msg) {
        progressDialog.dismiss();
        MyDialog.getInstance().with(LoginActivity.this)
                .message(msg)
                .autoBack(false)
                .autoDismiss(false)
                .show();

    }

    @Override
    public void ontestNamesDownloadSuccess(List<TestModel> data) {
        DataStore.testModelList.clear();
     //   Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();
        for (int i=0;i<data.size();i++){
            DataStore.testModelList.add(new testSelectedModel(false,data.get(i)));
        }
    }

    @Override
    public void ontestNamesDownloadFailed(String msg) {

    }
}
