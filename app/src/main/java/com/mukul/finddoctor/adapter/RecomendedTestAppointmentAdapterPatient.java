package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.finddoctor.Activity.RecomendationDetailActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.RecomentationModel;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.testList;

/**
 * Created by mukul on 3/10/2019.
 */


public class RecomendedTestAppointmentAdapterPatient extends RecyclerView.Adapter<RecomendedTestAppointmentAdapterPatient.MyViewHolder> {
    List<RecomentationModel>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_address, tv_appointmentfor, tv_date;
        RecyclerView recycler_view;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);



        }
    }


    public RecomendedTestAppointmentAdapterPatient(List<RecomentationModel> lists ) {
        list.clear();
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recomended_test_patient, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final RecomentationModel movie = list.get(position);
        context = holder.tv_name.getContext();
        Resources res =context. getResources();
        String text = res.getString(R.string.text);
        holder.tv_name.setText(movie.getDrName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testList=movie;
                context.startActivity(new Intent(context, RecomendationDetailActivity.class));

            }
        });



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}