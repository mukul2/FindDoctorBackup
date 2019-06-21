package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mukul.finddoctor.R;

import butterknife.ButterKnife;


public class BlogFragmentPatient extends Fragment  {
    View v;
    Context context;


    public static BlogFragmentPatient newInstance() {
        BlogFragmentPatient fragment = new BlogFragmentPatient();
        return fragment;
    }

    public BlogFragmentPatient() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.blog_fragment_patient, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);



        return v;
    }





}
