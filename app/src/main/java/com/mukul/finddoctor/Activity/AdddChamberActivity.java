package com.mukul.finddoctor.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.adapter.ChambersListAdapterDr;
import com.mukul.finddoctor.adapter.DaysTimesAdapterDoctor;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.Day;
import com.mukul.finddoctor.model.DaysTimeModel;
import com.mukul.finddoctor.model.StatusMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdddChamberActivity extends AppCompatActivity implements ApiListener.drSchedulePostListener{
    @BindView(R.id.autoCT)
    AutoCompleteTextView autoCT;
    @BindView(R.id.ed_city)
    EditText ed_city;
    @BindView(R.id.ed_address)
    EditText ed_address;

    @BindView(R.id.ed_fees)
    EditText ed_fees;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    DaysTimesAdapterDoctor mAdapter;
    public static List<DaysTimeModel>Dayslist=new ArrayList<>();
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addd_chamber);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        progressDialog=new ProgressDialog(this);
        init_specialist_autoComplete();
        init_days_();

    }

    private void init_days_() {
        Dayslist.clear();

        for (int i=0;i<DataStore.sevenDays().size();i++){
            Dayslist.add(new DaysTimeModel(DataStore.sevenDays().get(i),"",""));
        }
        mAdapter = new DaysTimesAdapterDoctor();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AdddChamberActivity.this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(AdddChamberActivity.this, LinearLayoutManager.VERTICAL));
        recycler_view.setAdapter(mAdapter);

    }

    private void init_specialist_autoComplete() {
        List<String>specialists=new ArrayList<>();
        for (int i=0;i<Data.spacialist.size();i++){
            specialists.add(Data.spacialist.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, specialists);

        autoCT.setThreshold(1);
        autoCT.setAdapter(adapter);
    }

    public void SaveSchedule(View view) {
        progressDialog.setMessage("Please wait");
       // progressDialog.show();
        String address=ed_address.getText().toString().trim();
        String city=ed_city.getText().toString().trim();
        String fees=ed_fees.getText().toString().trim();
        String specialist=autoCT.getText().toString().trim();

        List<Day>list=new ArrayList<>();
        for (DaysTimeModel daysTimeModel:Dayslist){
            if (true){
                list.add(new Day(DataStore.convertDayToNmber(daysTimeModel.getDayName()),(daysTimeModel.getStartTime()+" to "+daysTimeModel.getEndTime())));
            }
        }
        Gson gson=new Gson();
        //Toast.makeText(this, gson.toJson(list), Toast.LENGTH_LONG).show();
      //  Api.getInstance().setDrSchedule(sessionManager.getUserId(),gson.toJson(list),address,fees,city,specialist,"","",this);

    }

    @Override
    public void ondrSchedulePostSuccess(StatusMessage data) {
        progressDialog.dismiss();
        Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,DrChamberListActivity.class));
        finish();

    }

    @Override
    public void ondrSchedulePostFailed(String msg) {
        progressDialog.dismiss();

        Toast.makeText(this,"error here "+ msg, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void back(View view) {
        onBackPressed();
    }
}
