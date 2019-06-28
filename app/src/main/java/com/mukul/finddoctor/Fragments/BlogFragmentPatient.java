package com.mukul.finddoctor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.AccountPicker;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.adapter.BlogAdapterPatient;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.BlogModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.TOKEN;


public class BlogFragmentPatient extends Fragment implements ApiListener.BlogDownloadListener {
    View v;
    Context context;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


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
        //List<BlogModel>
        Api.getInstance().blogsDownload(TOKEN,this);



        return v;
    }


    @Override
    public void onBlogDownloaSuccess(List<BlogModel> list) {
        BlogAdapterPatient mAdapter = new BlogAdapterPatient(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onBlogDownloaSuccessFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
