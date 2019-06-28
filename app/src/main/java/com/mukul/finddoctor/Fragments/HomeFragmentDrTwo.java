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

import com.google.gson.Gson;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.ChamberInfo;
import com.mukul.finddoctor.model.DrEduChInfoModel;
import com.mukul.finddoctor.model.EducationInfo;
import com.mukul.finddoctor.model.EducationSkillModel;
import com.mukul.finddoctor.model.SkillInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.EDUCATIONSKILLMODEL;
import static com.mukul.finddoctor.Data.DataStore.TOKEN;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;


public class HomeFragmentDrTwo extends Fragment implements ApiListener.drChamberEduSkillDownloadListener {
    View v;
    Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static List<SkillInfo> SKILLS = new ArrayList<>();
    public static List<EducationInfo> EDUCATION = new ArrayList<>();
    public static List<ChamberInfo> CHAMBERLIST = new ArrayList<>();


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
        context = v.getContext();

        ButterKnife.bind(this, v);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);


        Api.getInstance().getEduSKillChamber(TOKEN, USER_ID, this);


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
    public void onChamberEduSkillDownloadSuccess(DrEduChInfoModel list) {
        Gson gson = new Gson();
        SKILLS=list.getSkillInfo();
        EDUCATION=list.getEducationInfo();
        CHAMBERLIST=list.getChamberInfo();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onChamberEduSkillDownloadFailed(String msg) {
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
