package com.mukul.finddoctor.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mukul.finddoctor.Activity.AddNewChamberActivity;
import com.mukul.finddoctor.Activity.PatientHomeActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.ChamberDaysListAdapter;
import com.mukul.finddoctor.adapter.ChambersListAdapterDr;
import com.mukul.finddoctor.adapter.DepartmentsAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.DaysTimeModel;
import com.mukul.finddoctor.model.DrChamberResponse;
import com.mukul.finddoctor.widgets.DividerItemDecoration;
import com.mukul.finddoctor.widgets.MyDialogList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;


public class ChamberFragment extends Fragment implements ApiListener.chamberListDownloadListener{
    View v;
    Context context;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    List<DaysTimeModel> list = new ArrayList<>();
    List<DaysTimeModel> listReserved = new ArrayList<>();
    List<String> days = new ArrayList<>();
    List<String> timeRange = new ArrayList<>();


    public static ChamberFragment newInstance() {
        ChamberFragment fragment = new ChamberFragment();
        return fragment;
    }

    public ChamberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.chamber_fragment, container, false);
        context = v.getContext();

        ButterKnife.bind(this, v);

        //dayAddDialog
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context, AddNewChamberActivity.class));
            }
        });
        Api.getInstance().getMyChambersList(USER_ID,this);



        return v;
    }


    @Override
    public void onChamberListDownloadSuccess(List<DrChamberResponse> list) {
        if (list!=null){
            ChambersListAdapterDr mAdapter=new  ChambersListAdapterDr(list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);
        }
    }

    @Override
    public void onChamberListDownloadFailed(String msg) {

    }
}
