package com.mukul.finddoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.TestListTypeAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.RecomentationModel;
import com.mukul.finddoctor.model.TestList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.Data.testList;

public class VisitActivityDr extends AppCompatActivity implements ApiListener.TestDownloadListener {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_problems)
    TextView tv_problems;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_dr);
        ButterKnife.bind(this);
        AppointmentModel2 model = Data.drServingModel;
        setTitle("" + model.getId());
        tv_name.setText(model.getAppointmentFor());
        tv_problems.setText(model.getProblems());
        tv_time.setText(DataStore.changeDateformate(model.getDate()));
        initSpinner(model.getStatus());
        Api.getInstance().downlaodRecomendedLits("" + model.getId(), this);
    }

    private void initSpinner(String status) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Data.getAllStatusTypes());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(dataAdapter);
        for (int i = 0; i < Data.getAllStatusTypes().size(); i++) {
            if (status.equals("" + i)) {
                spinnerStatus.setSelection(i);
                break;
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }


    @Override
    public void onTestDownloadSuccess(List<TestList> list) {
        Toast.makeText(this, "" + list.size(), Toast.LENGTH_SHORT).show();

        TestListTypeAdapter mAdapter = new TestListTypeAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //holder.recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onTestDownloadFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
