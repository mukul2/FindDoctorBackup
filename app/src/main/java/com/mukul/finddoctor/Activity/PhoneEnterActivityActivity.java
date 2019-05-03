package com.mukul.finddoctor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mukul.finddoctor.R;

public class PhoneEnterActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_enter_activity);
    }

    public void openVarificationCodeActivity(View view) {
        startActivity(new Intent(this,CodeVerificationActivity.class));
    }
}
