package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.DrListAdapter;
import com.mukul.finddoctor.adapter.HospitalsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.CLICKED_TITLE;
import static com.mukul.finddoctor.Data.DataStore.downloadedDoctors;

public class DrListActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_list);
        ButterKnife.bind(this);
        tv_title.setText(CLICKED_TITLE);
        initRecyclerView();
    }
    private void initRecyclerView() {
        DrListAdapter mAdapter = new DrListAdapter(downloadedDoctors);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    public void back(View view) {
        onBackPressed();
    }
}
