package com.mukul.finddoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.DrServicesListAdapter;
import com.mukul.finddoctor.adapter.SearchResultAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.DrServiceModel;
import com.mukul.finddoctor.model.ServiceName;
import com.mukul.finddoctor.model.ServiceWithBoolean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;
import static com.mukul.finddoctor.Data.DataStore.serviceNameList;

public class ServicesActivityDr extends AppCompatActivity implements ApiListener.DrServiceDownloadListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public static List<ServiceWithBoolean> SERVICES_LIST=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_dr);
        ButterKnife.bind(this);
        Api.getInstance().downloadDrServiceList(USER_ID,this);
    }

    public void back(View view) {
        onBackPressed();
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
        DrServicesListAdapter mAdapter = new DrServicesListAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onDrServiceDownloadFailed(String msg) {

    }

    public void update(View view) {
    }
}
