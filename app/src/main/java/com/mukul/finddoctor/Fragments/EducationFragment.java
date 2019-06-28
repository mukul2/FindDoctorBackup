package com.mukul.finddoctor.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.adapter.EducationsAdapterDoctor;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.StatusMessage;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.EDUCATIONSKILLMODEL;
import static com.mukul.finddoctor.Data.DataStore.TOKEN;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;
import static com.mukul.finddoctor.Fragments.HomeFragmentDrTwo.EDUCATION;


public class EducationFragment extends Fragment  {
    View v;
    Context context;
    @BindView(R.id.cardAddNew)
    CardView cardAddNew;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;



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
        cardAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_education_info_dialog);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                CardView cardDone=(CardView)dialog.findViewById(R.id.cardDone);
                TextView ed_title=(TextView)dialog.findViewById(R.id.ed_title);
                TextView ed_body=(TextView)dialog.findViewById(R.id.ed_body);
                cardDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title=ed_title.getText().toString().trim();
                        String body=ed_body.getText().toString().trim();
                        if (title.length()>0 && body.length()>0){
                            MyProgressBar.with(getContext()).show();
                            Api.getInstance().postEducationInfo(TOKEN, USER_ID, title, body, new ApiListener.PostEducationInfoListener() {
                                @Override
                                public void onPostEducationInfoSuccess(StatusMessage status) {
                                    MyProgressBar.dismiss();
                                    if (status.getStatus()==true){
                                        Toast.makeText(getContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }else {
                                        Toast.makeText(getContext(), status.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onPostEducationInfoFailed(String msg) {
                                    MyProgressBar.dismiss();
                                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();


                                }
                            });
                        }
                    }
                });


            }
        });

        initRecyclerView();




        return v;
    }

    private void initRecyclerView() {
        EducationsAdapterDoctor mAdapter = new EducationsAdapterDoctor(EDUCATION);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }


}
