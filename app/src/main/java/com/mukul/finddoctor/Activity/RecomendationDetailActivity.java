package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.adapter.TestListTypeAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.StatusResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.Data.testList;
import static com.mukul.finddoctor.Data.ListenerPatientsData.PatientALLDataDownloadListener;
import static com.mukul.finddoctor.Data.ListenerPatientsData.PatientNotificationDataDownloadListener;

public class RecomendationDetailActivity extends AppCompatActivity implements ApiListener.appointmentStateChangeListener,
        ApiListener.appoinetmentsDownloadListener {
    TestListTypeAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_serial)
    TextView tv_serial;
    @BindView(R.id.tv_name)
    TextView tv_name;
    Context context = this;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendation_detail);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);

        mAdapter = new TestListTypeAdapter(testList.getTestList());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //holder.recycler_view.addItemDecoration(new_ DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recycler_view.setAdapter(mAdapter);

        Resources res = context.getResources();
        String text = res.getString(R.string.text);
        tv_name.setText(testList.getDrName() + " " + text);
        tv_serial.setText("Serial No : "+testList.getAppointmentId());
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

    public void cancel(View view) {
        MyProgressBar.with(context);
        Api.getInstance().changeStatus(testList.getAppointmentId(), "" + Data.STATUS_CANCEL, this);
    }

    @Override
    public void onAppointmentChangeSuccess(StatusResponse list) {
        Api.getInstance().getAppointmentsBypatient(sessionManager.getUserId(), this);


    }

    @Override
    public void onPppointmentChangeFailed(String msg) {
        onBackPressed();


    }

    @Override
    public void onAppointmentDownloadSuccess(AppointmentResponse status) {
        PatientALLDataDownloadListener.onDownloaded(status);
        PatientNotificationDataDownloadListener.onNotificationDownloaded(status.getNotification());
    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        onBackPressed();

    }

    public void back(View view) {
        onBackPressed();
    }
}
