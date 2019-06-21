package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.EducationSkillModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.EDUCATIONSKILLMODEL;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;


public class HomeFragmentDrTwo extends Fragment implements ApiListener.doctorEduSkillDownloadListener  {
    View v;
    Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    public static HomeFragmentDrTwo newInstance() {
        HomeFragmentDrTwo fragment = new HomeFragmentDrTwo();
        return fragment;
    }

    public HomeFragmentDrTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dr_fragment_two, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        viewPager = (ViewPager)v. findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)v. findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Api.getInstance().doctorEduSkillDownload("41",this);



        return v;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new EducationFragment(), "Education");
        adapter.addFragment(new SpecialSkillFragment(), "Skill");
        adapter.addFragment(new ChamberFragment(), "Chamber");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void ondoctorEduSkillDownloadSuccess(EducationSkillModel data) {

        EDUCATIONSKILLMODEL=data;

    }

    @Override
    public void ondoctorEduSkillDownloadSuccessFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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
