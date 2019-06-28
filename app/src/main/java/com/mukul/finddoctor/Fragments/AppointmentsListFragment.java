package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Activity.DoctorHomeActivity;
import com.mukul.finddoctor.Data.lis;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.adapter.AppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppointmentsListFragment extends Fragment implements ApiListener.dataDownloadListener {
    View v;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_no_item)
    TextView tv_no_item;
    ConfirmedAppointmentAdapterDoctor mAdapter;

    public AppointmentsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_appointments_list, container, false);

        ButterKnife.bind(this, v);
        lis.setConfirmedlistener(this);

        return v;
    }


    @Override
    public void onDownloaded(List<AppointmentModel> status) {
        if (status.size() > 0) {
            tv_no_item.setVisibility(View.GONE);

        }
        //  mAdapter = new ConfirmedAppointmentAdapterDoctor(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recycler_view.setAdapter(mAdapter);
        if (status.size() > 0) {
            tv_no_item.setVisibility(View.GONE);
        } else {
            tv_no_item.setVisibility(View.VISIBLE);

        }
    }
}
