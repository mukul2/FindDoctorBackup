package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.BasicByDrResponse;
import com.mukul.finddoctor.model.LoginResponse;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ApiListener.LoginUserListener, ApiListener.testNamesDownloadListener {
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_password)
    EditText ed_password;
    String phone, password;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String DOCTOR = "d";
    String PATIENT = "p";

    //changes by 15:50 secondsdad


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        // setUpStatusbar();

    }

    private void setUpStatusbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {


            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void openSignUpActivity(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void login(View view) {
        phone = ed_phone.getText().toString().trim();
        password = ed_password.getText().toString().trim();

        if (phone.length() > 0 && password.length() > 0) {
            progressDialog.show();
            Api.getInstance().loginUser(phone, password, this);

        }


    }

    @Override
    public void onUserLoginSuccess(LoginResponse status) {
        progressDialog.dismiss();
        if (status.getStatus()) {
            sessionManager.setuserId("" + status.getUserInfo().getId());
            sessionManager.setLoggedIn(true);
            sessionManager.setuserName(status.getUserInfo().getName());
            sessionManager.setuserType(status.getUserInfo().getUserType());
            sessionManager.setToken("Bearer " + status.getAccessToken());
            sessionManager.set_userPhoto(status.getUserInfo().getPhoto());
            Toast.makeText(this, status.getMessage(), Toast.LENGTH_LONG).show();

            if (status.getUserInfo().getUserType().equals(DOCTOR)) {
                startActivity(new Intent(LoginActivity.this, HomeActivityDrActivity.class));
                finishAffinity();
            } else if (status.getUserInfo().getUserType().equals(PATIENT)) {
                startActivity(new Intent(LoginActivity.this, PatientHomeActivity.class));
                finishAffinity();

            } else {
                Toast.makeText(this, "Unknown usertype", Toast.LENGTH_SHORT).show();
            }


        } else {
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
    public void ontestNamesDownloadSuccess(BasicByDrResponse data) {
        DataStore.testModelList.clear();
        //   Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < data.getTestNames().size(); i++) {
            DataStore.testModelList.add(new testSelectedModel(false, data.getTestNames().get(i)));
        }
    }

    @Override
    public void ontestNamesDownloadFailed(String msg) {

    }


}
