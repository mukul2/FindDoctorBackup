package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterDoctor;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModelNew;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.TOKEN;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;


public class DrConfirmedActivity extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener{
    Context context=this;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_confirmed);
        ButterKnife.bind(this);
        setUpStatusbar();
        Api.getInstance().getAppointments(TOKEN,"doctor",USER_ID,"1",this);



    }
    public  void setUpStatusbar(){
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

    public void Back(View view) {
        onBackPressed();
    }


    @Override
    public void onAppointmentDownloadSuccess(List<AppointmentModelNew> status) {
        ConfirmedAppointmentAdapterDoctor mAdapter = new ConfirmedAppointmentAdapterDoctor(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();

    }
}
