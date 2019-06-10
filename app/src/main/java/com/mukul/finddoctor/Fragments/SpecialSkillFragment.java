package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mukul.finddoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.EDUCATIONSKILLMODEL;


public class SpecialSkillFragment extends Fragment  {
    View v;
    Context context;
    @BindView(R.id.ed_skill)
    EditText ed_skill;


    public static SpecialSkillFragment newInstance() {
        SpecialSkillFragment fragment = new SpecialSkillFragment();
        return fragment;
    }

    public SpecialSkillFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.skill_fragment, container, false);
        context=v.getContext();
        ButterKnife.bind(this,v);
        ed_skill.setText(EDUCATIONSKILLMODEL.getSkill());





        return v;
    }





}
