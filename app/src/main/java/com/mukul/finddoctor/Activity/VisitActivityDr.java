package com.mukul.finddoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitActivityDr extends AppCompatActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_problems)
    TextView tv_problems;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_dr);
        ButterKnife.bind(this);
        AppointmentModel2 model= Data.drServingModel;
        tv_name.setText(model.getAppointmentFor());
        tv_problems.setText(model.getProblems());
        tv_time.setText(DataStore.changeDateformate(model.getDate()));
        initSpinner(model.getStatus());
    }

    private void initSpinner(String status) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Data.getAllStatusTypes());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(dataAdapter);
        for (int i=0;i<Data.getAllStatusTypes().size();i++){
            if (status.equals(""+i)){
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
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

}
