package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.DepartmentsAdapter;
import com.mukul.finddoctor.adapter.OnlineDrListAdapter;
import com.mukul.finddoctor.adapter.SearchResultAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.DepartmentModel;
import com.mukul.finddoctor.model.DeptModel;
import com.mukul.finddoctor.model.OnlineDoctorModel;
import com.mukul.finddoctor.model.VideoCallModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.Data.TYPE_OF_ACTIVITY;
import static com.mukul.finddoctor.Data.DataStore.TOKEN;

public class OnlineDoctorsActivity extends AppCompatActivity  {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context=this;
   public static List<OnlineDoctorModel>DOCTORS_LIST=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_doctors);
        setUpStatusbar();
        ButterKnife.bind(this);
        Api.getInstance().getDepList(TOKEN, new ApiListener.DeptDownloadListener() {
            @Override
            public void onDepartmentDownloadSuccess(List<DeptModel> list) {
                Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();
                TYPE_OF_ACTIVITY="OnlineDoc";

                DepartmentsAdapter mAdapter = new DepartmentsAdapter(list);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);

            }

            @Override
            public void onDepartmentDownloadFailed(String msg) {

            }
        });

    }
    public  void setUpStatusbar(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    public void back(View view) {
        onBackPressed();
    }


}
