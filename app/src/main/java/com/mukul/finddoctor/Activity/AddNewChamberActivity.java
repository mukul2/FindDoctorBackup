package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.adapter.ChamberDaysListAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.ChamberDaysPostModel;
import com.mukul.finddoctor.model.DaysTimeModel;
import com.mukul.finddoctor.model.StatusMessage;
import com.mukul.finddoctor.widgets.DividerItemDecoration;
import com.mukul.finddoctor.widgets.MyDialogList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;

public class AddNewChamberActivity extends AppCompatActivity implements ApiListener.drSchedulePostListener{
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context=this;
    List<DaysTimeModel> list=new ArrayList<>();
    List<DaysTimeModel>listReserved=new ArrayList<>();
    List<String>days=new ArrayList<>();
    List<String>startTime=new ArrayList<>();
    List<String>endTime=new ArrayList<>();
    @BindView(R.id.ed_address)
    EditText ed_address;
    @BindView(R.id.ed_fees)
    EditText ed_fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_chamber);
        ButterKnife.bind(this);
        ChamberDaysListAdapter mAdapter = new ChamberDaysListAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL,false);
        recycler_view.setAdapter(mAdapter);
        recycler_view.addItemDecoration(dividerItemDecoration);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogList.getInstance().with((Activity) context).dayAddDialog(new MyDialogList.DaysSelectedListener() {
                    @Override
                    public void onDaysSelected(DaysTimeModel day) {
                        //Toast.makeText(context, day.getDayName(), Toast.LENGTH_SHORT).show();
                        list.add(list.size(),day);
                        mAdapter.notifyItemInserted(list.size()-1);

                        days.add(day.getDayName());
                        startTime.add(day.getStartTime());
                        endTime.add(day.getStartTime());
                        //List<Day>list=new ArrayList<>();



                    }

                    @Override
                    public void onDaysSelectCanceled(String canceled) {

                    }
                });
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }

    public void save(View view) {
        String address=ed_address.getText().toString().trim();
        String fees=ed_fees.getText().toString().trim();
        List<ChamberDaysPostModel>data=new ArrayList<>();

        for (int i=0;i<days.size();i++){
           // data.add(new ChamberDaysPostModel(days.get(i),startTime.get(i),endTime.get(i)));
            ChamberDaysPostModel model=new ChamberDaysPostModel();
            model.setDay(days.get(i));
            model.setStartTime(startTime.get(i));
            model.setEndTime(endTime.get(i));
            data.add(model);

        }
        MyProgressBar.with(context).show();
        Gson gson=new Gson();
     //  Toast.makeText(context, gson.toJson(data), Toast.LENGTH_LONG).show();
        Api.getInstance().setDrSchedule(USER_ID,address,fees,"no data",gson.toJson(data),this);

    }

    @Override
    public void ondrSchedulePostSuccess(StatusMessage data) {
        MyProgressBar.dismiss();
        if (data!=null && data.getStatus()!=null && data.getStatus()==true){
           Toast.makeText(context, data.getMessage(), Toast.LENGTH_LONG).show();
           onBackPressed();
        }else {
            Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void ondrSchedulePostFailed(String msg) {
        MyProgressBar.dismiss();

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
