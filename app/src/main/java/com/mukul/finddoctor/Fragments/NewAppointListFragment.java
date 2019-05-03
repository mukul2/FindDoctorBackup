package com.mukul.finddoctor.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mukul.finddoctor.Data.lis;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.AppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.PendingAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.SearchResultAdapter;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewAppointListFragment extends Fragment implements ApiListener.dataDownloadListener{
    View v;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    PendingAppointmentAdapterDoctor mAdapter;
    public NewAppointListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lis. setPendinglistener(this);
        v = inflater.inflate(R.layout.fragment_new_appointment, container, false);
        ButterKnife.bind(this,v);




        return v;
    }
    @Override
    public void onDownloaded(List<AppointmentModel> status) {
        mAdapter = new PendingAppointmentAdapterDoctor(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recycler_view.setAdapter(mAdapter);

    }

}
