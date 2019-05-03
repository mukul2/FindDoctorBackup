package com.mukul.finddoctor.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Fragments.AppointmentsListFragment;
import com.mukul.finddoctor.Fragments.AppointmentsListPatient;
import com.mukul.finddoctor.Fragments.NotificationFragment;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.CustomDrawerButton;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.ListenerPatientsData.PatientALLDataDownloadListener;
import static com.mukul.finddoctor.Data.ListenerPatientsData.PatientNotificationDataDownloadListener;

public class HomeActivity extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener,
SwipeRefreshLayout.OnRefreshListener{
    SessionManager sessionManager;
    TabLayout tabLayout;
    ViewPager viewPager;
    CustomDrawerButton customDrawerButton;
    DrawerLayout drawer;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        sessionManager=new SessionManager(this);
        tv_user_name.setText(sessionManager.getUserName());

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.parseColor("#E6E6E6"), Color.WHITE);
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setupWithViewPager(viewPager);

        drawer= (DrawerLayout)findViewById(R.id.drawer_layout);
        customDrawerButton = (CustomDrawerButton)findViewById(R.id.customDrawer);
        customDrawerButton.setDrawerLayout( drawer );
        customDrawerButton.getDrawerLayout().addDrawerListener( customDrawerButton );
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
        swiperefresh.setOnRefreshListener(this);
        onRefresh();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AppointmentsListPatient(), "Appointments");
        adapter.addFragment(new NotificationFragment(), "Recomendation");
        viewPager.setAdapter(adapter);
    }

    public void FindDoctorActivity(View view) {
        startActivity(new Intent(this, FindDoctorActivity.class));
    }

    @Override
    public void onAppointmentDownloadSuccess(AppointmentResponse status) {
        swiperefresh.setRefreshing(false);
        PatientALLDataDownloadListener.onDownloaded(status);
        PatientNotificationDataDownloadListener.onNotificationDownloaded(status.getNotification());
      //  Toast.makeText(this, ""+status.getNotification().size(), Toast.LENGTH_SHORT).show();



    }
    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this,LoginActivity.class));
        finishAffinity();
    }
    @Override
    public void onAppointmentDownloadFailed(String msg) {

    }

    @Override
    public void onRefresh() {
        Api.getInstance().getAppointmentsBypatient(sessionManager.getUserId(),this);

    }

    public void history(View view) {
        startActivity(new Intent(this,HistoryActivity.class));

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
}
