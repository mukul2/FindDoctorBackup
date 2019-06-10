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


public class EducationFragment extends Fragment  {
    View v;
    Context context;
    @BindView(R.id.ed_educationInfo)
    EditText ed_educationInfo;


    public static EducationFragment newInstance() {
        EducationFragment fragment = new EducationFragment();
        return fragment;
    }

    public EducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_education, container, false);
        ButterKnife.bind(this,v);
        context=v.getContext();

        ButterKnife.bind(this,v);
        ed_educationInfo.setText(EDUCATIONSKILLMODEL.getEducation());




        return v;
    }





}
