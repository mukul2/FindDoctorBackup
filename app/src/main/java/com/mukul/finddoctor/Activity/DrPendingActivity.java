package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterDoctor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrPendingActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_pending);
        ButterKnife.bind(this);
        PendingAppointmentAdapterDoctor mAdapter = new PendingAppointmentAdapterDoctor(DrAllAppointmentsActivity.PENDING);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recycler_view.setAdapter(mAdapter);
    }

    public void Back(View view) {
        onBackPressed();
    }
}
