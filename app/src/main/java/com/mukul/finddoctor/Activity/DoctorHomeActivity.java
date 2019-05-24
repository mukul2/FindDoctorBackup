package com.mukul.finddoctor.Activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.Data.lis;
import com.mukul.finddoctor.Fragments.AppointmentsListFragment;
import com.mukul.finddoctor.Fragments.NewAppointListFragment;
import com.mukul.finddoctor.Fragments.NotificationFragment;
import com.mukul.finddoctor.Fragments.VideoCallFragmenttFragmentDoctor;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.CustomDrawerButton;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.adapter.ConfirmedAppointmentAdapterDoctor;
import com.mukul.finddoctor.adapter.SearchAdapterDoctor;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.SpacialistModel;
import com.mukul.finddoctor.model.StatusMessage;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.Data.spacialist;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;
import static com.mukul.finddoctor.Data.lis.Confirmedlistener;
import static com.mukul.finddoctor.Data.lis.Pendinglistener;

public class DoctorHomeActivity extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener,
        ApiListener.basicInfoDownloadListener,
        SwipeRefreshLayout.OnRefreshListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    CustomDrawerButton customDrawerButton;
    DrawerLayout drawer;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.searchDr_recycler)
    RecyclerView searchDr_recycler;
    SessionManager sessionManager;
    int count = 0;
    Context context = this;
    SearchAdapterDoctor mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        sessionManager = new SessionManager(this);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.parseColor("#E6E6E6"), Color.WHITE);
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setupWithViewPager(viewPager);

        //drayer setup
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        customDrawerButton = (CustomDrawerButton) findViewById(R.id.customDrawer);
        customDrawerButton.setDrawerLayout(drawer);
        customDrawerButton.getDrawerLayout().addDrawerListener(customDrawerButton);
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
        tv_user_name.setText(sessionManager.getUserName());
        USER_ID = sessionManager.getUserId();


        swiperefresh.setOnRefreshListener(this);
        onRefresh();
        init_search();
    }
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    private void init_search() {
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String id = "";
                String key=ed_search.getText().toString().trim();
                String patient_name="";
                if (isNumeric(key)){
                    id=key;
                    patient_name="";

                }else {
                    id="";
                    patient_name=key;

                }

                if ((id.trim().length()+patient_name.trim().length()) > 0 ) {
                  //  Toast.makeText(context, ""+(id.trim().length()+patient_name.trim().length()), Toast.LENGTH_SHORT).show();
                    Api.getInstance().searchAppointment(id, USER_ID, patient_name, new ApiListener.appointmentSearchListener() {
                        @Override
                        public void onAppointmentSearchSuccess(List<AppointmentModel2> data) {
                            mAdapter = new SearchAdapterDoctor(data);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                            searchDr_recycler.setLayoutManager(mLayoutManager);
                            searchDr_recycler.setItemAnimator(new DefaultItemAnimator());
                            //searchDr_recycler.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

                            searchDr_recycler.setAdapter(mAdapter);
                        }

                        @Override
                        public void onAppointmentSearchFailed(String msg) {
                            Toast.makeText(DoctorHomeActivity.this, msg, Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                   // Toast.makeText(context, "clr list", Toast.LENGTH_SHORT).show();
                    if (mAdapter==null){

                    }else {
                        mAdapter.clearAdapter();

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBasicInfoDownloadSuccess(BasicInfoModel data) {
        count++;
        hideSwipeRefresh();


        spacialist.clear();
        for (int i = 0; i < data.getSpacialist().size(); i++) {
            spacialist.add(new SpacialistModel(data.getSpacialist().get(i), false));
        }


    }

    @Override
    public void onBasicInfoDownloadFailed(String msg) {
        count++;
        hideSwipeRefresh();


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AppointmentsListFragment(), "Confirmed");
        adapter.addFragment(new NewAppointListFragment(), "Pending");
        adapter.addFragment(new VideoCallFragmenttFragmentDoctor(), "Video Call");
        viewPager.setAdapter(adapter);
    }

    public void OpenDrPersonalInfoActivity(View view) {
        startActivity(new Intent(this, DrPersonalInfoActivity.class));

    }

    @Override
    public void onAppointmentDownloadSuccess(AppointmentResponse status) {
        count++;
        hideSwipeRefresh();


        // tabLayout.getTabAt(0).setText("Confirmed ("+status.getConfirmed().size()+")");
        // tabLayout.getTabAt(1).setText("Pending ("+status.getNotConfirmed().size()+")");
        Confirmedlistener.onDownloaded(status.getConfirmed());
        Pendinglistener.onDownloaded(status.getNotConfirmed());


    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        count++;
        hideSwipeRefresh();
        MyDialog.getInstance().with(DoctorHomeActivity.this)
                .message("Techinal error.Try again later")
                .autoBack(false)
                .autoDismiss(false)
                .showMsgOnly();

    }

    private void hideSwipeRefresh() {
        if (swiperefresh.isRefreshing()) {
            if (count > 1) {
                swiperefresh.setRefreshing(false);
            }

        }
    }

    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    public void openChamberActivity(View view) {
        customDrawerButton.changeState();
        startActivity(new Intent(this, DrChamberListActivity.class));
    }

    @Override
    public void onRefresh() {
        count = 0;
        Api.getInstance().getAppointmentsByDoctor(sessionManager.getUserId(), this);
        Api.getInstance().downloadBasicInfo(this);
       // Api.getInstance().downloadTestNames(this);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        MyProgressBar.with(DoctorHomeActivity.this);
        Api.getInstance().changeDrOnlineStatus(USER_ID, "0", new ApiListener.doctorOnlineStatusChangeListener() {
            @Override
            public void ondoctorOnlineStatusChangeSuccess(StatusMessage statusMessage) {
                MyProgressBar.dismiss();
                finish();
            }

            @Override
            public void ondoctorOnlineStatusChangeFailed(String msg) {
                MyProgressBar.dismiss();
                finish();

            }
        });

    }
}
