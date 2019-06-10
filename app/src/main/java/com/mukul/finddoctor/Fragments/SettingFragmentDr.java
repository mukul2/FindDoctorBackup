package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.DrServicesListAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.DrServiceModel;
import com.mukul.finddoctor.model.ServiceWithBoolean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;
import static com.mukul.finddoctor.Data.DataStore.serviceNameList;


public class SettingFragmentDr extends Fragment implements ApiListener.DrServiceDownloadListener  {
    View v;
    Context context;

    public static List<ServiceWithBoolean> SERVICES_LIST=new ArrayList<>();


    public static SettingFragmentDr newInstance() {
        SettingFragmentDr fragment = new SettingFragmentDr();
        return fragment;
    }

    public SettingFragmentDr() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.setting_fragment_dr, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        Api.getInstance().downloadDrServiceList("40",this);




        return v;
    }


    @Override
    public void onDrServiceDownloadSuccess(List<DrServiceModel> list) {
        for (int i=0;i<serviceNameList.size();i++){
            SERVICES_LIST.add(new ServiceWithBoolean(false,serviceNameList.get(i)));

        }
        for (int i=0;i<SERVICES_LIST.size();i++){
            for (int j=0;j<list.size();j++){
                if (SERVICES_LIST.get(i).getServiceName().getId().equals(list.get(j).getServiceId())){
                    SERVICES_LIST.get(i).setSelected(true);
                    break;

                }
            }
        }
        Gson gson=new Gson();
        Toast.makeText(context, gson.toJson(list), Toast.LENGTH_LONG).show();
//        DrServicesListAdapter mAdapter = new_ DrServicesListAdapter();
//        RecyclerView.LayoutManager mLayoutManager = new_ LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new_ DefaultItemAnimator());
//        //recyclerView.addItemDecoration(new_ DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//
//        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onDrServiceDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }




}
