package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.CurrentlyOnlineDoctorAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.VideoCallModel;
import com.mukul.finddoctor.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoCallPatientActivity extends AppCompatActivity implements  ApiListener.onlineDoctorListener{
    View v;
    Context context=this;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_no_item)
    TextView tv_no_item;
    @BindView(R.id.tv_callLog)
    TextView tv_callLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_patient);
        ButterKnife.bind(this);
        Api.getInstance().downloadOnlineDoctors(this);
        tv_callLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CallLogHistoryActivity.class));
            }
        });

    }

    @Override
    public void onOnlineDoctorSearchSuccess(List<VideoCallModel> list) {
        if (list.size()>0) {
            tv_no_item.setVisibility(View.GONE);
            CurrentlyOnlineDoctorAdapter mAdapter = new CurrentlyOnlineDoctorAdapter(list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));

            recycler_view.setAdapter(mAdapter);
        }else {
            tv_no_item.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onOnlineDoctorSearchFailed(String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public void Back(View view) {
        onBackPressed();
    }
}
