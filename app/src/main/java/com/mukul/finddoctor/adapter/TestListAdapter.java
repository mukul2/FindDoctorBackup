package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mukul.finddoctor.Activity.ChamberDetailActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.Day;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.searchResult;
import static com.mukul.finddoctor.Data.Data.singleDrModel;

/**
 * Created by mukul on 3/10/2019.
 */


public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.MyViewHolder> {

    Context context;
    List<testSelectedModel> list = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_testName;
        ImageView circleImageView;
        RelativeLayout relative_container;
        CheckBox checkbox;


        public MyViewHolder(View view) {
            super(view);
            tv_testName = (TextView) view.findViewById(R.id.tv_testName);
            checkbox = (CheckBox) view.findViewById(R.id.checkbox);


        }
    }


    public TestListAdapter(List<testSelectedModel> li) {
        this.list = li;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final testSelectedModel movie = list.get(position);
        holder.tv_testName.setText(movie.getTestModel().getName());
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true) {
                    movie.setSelected(true);
                } else {
                    movie.setSelected(false);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}