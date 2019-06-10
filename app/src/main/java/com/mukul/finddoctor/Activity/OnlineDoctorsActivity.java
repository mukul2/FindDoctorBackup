package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.OnlineDrListAdapter;
import com.mukul.finddoctor.adapter.SearchResultAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.OnlineDoctorModel;
import com.mukul.finddoctor.model.VideoCallModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnlineDoctorsActivity extends AppCompatActivity implements ApiListener.OnlineDoctorsDownloadListener {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context=this;
   public static List<OnlineDoctorModel>DOCTORS_LIST=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_doctors);
        ButterKnife.bind(this);
        Api.getInstance().downlaodOnlineDoctorsLits(this);
    }

    public void back(View view) {
        onBackPressed();
    }


    @Override
    public void onOnlineDoctorsDownloadSuccess(List<OnlineDoctorModel> list) {
        if (list!=null){
            OnlineDrListAdapter mAdapter = new OnlineDrListAdapter(list);
            DOCTORS_LIST=list;
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);
        }

    }

    @Override
    public void onOnlineDoctorsDownloadFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
