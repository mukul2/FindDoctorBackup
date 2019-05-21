package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrConfirmedActivity extends AppCompatActivity {
    Context context=this;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_confirmed);
        ButterKnife.bind(this);
        ConfirmedAppointmentAdapterDoctor  mAdapter = new ConfirmedAppointmentAdapterDoctor(DrAllAppointmentsActivity.CONFIRMED);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recycler_view.setAdapter(mAdapter);
    }

    public void Back(View view) {
        onBackPressed();
    }
}
