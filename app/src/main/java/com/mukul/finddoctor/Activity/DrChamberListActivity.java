package com.mukul.finddoctor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.adapter.ChambersListAdapterDr;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.Chamber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrChamberListActivity extends AppCompatActivity implements ApiListener.chamberListDownloadListener{
    SessionManager sessionManager;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    ChambersListAdapterDr mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_chamber_list);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        Api.getInstance().getMyChambersList(sessionManager.getUserId(),this);
    }

    @Override
    public void onChamberListDownloadSuccess(List<Chamber> list) {
        mAdapter = new ChambersListAdapterDr(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(DrChamberListActivity.this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(DrChamberListActivity.this, LinearLayoutManager.VERTICAL));
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onChamberListDownloadFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    public void addNewChamber(View view) {
        startActivity(new Intent(this,AdddChamberActivity.class));
    }

    public void back(View view) {
        onBackPressed();
    }
}
