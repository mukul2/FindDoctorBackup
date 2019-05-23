package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.RecomendedTestAppointmentAdapterPatient;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.RecomentationModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;

public class PatientTestRecomActivity extends AppCompatActivity implements ApiListener.DrRecomentationDownloadListener{

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context=this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_test_recom);
        ButterKnife.bind(this);
        Api.getInstance().downlaodPaRecomendation(USER_ID,this);

    }

    public void Back(View view) {
        onBackPressed();
    }

    @Override
    public void onRecomendationDownloadSuccess(List<RecomentationModel> list) {
        RecomendedTestAppointmentAdapterPatient mAdapter = new RecomendedTestAppointmentAdapterPatient(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onRecomendationFailed(String msg) {

    }
}
