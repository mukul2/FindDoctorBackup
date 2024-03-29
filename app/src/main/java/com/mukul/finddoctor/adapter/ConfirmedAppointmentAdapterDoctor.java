package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModelNew;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class ConfirmedAppointmentAdapterDoctor extends RecyclerView.Adapter<ConfirmedAppointmentAdapterDoctor.MyViewHolder> {
    List<AppointmentModelNew>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_problem, tv_date,tv_serial;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_serial = (TextView) view.findViewById(R.id.tv_serial);



        }
    }


    public ConfirmedAppointmentAdapterDoctor(List<AppointmentModelNew> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.confirmed_appointment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModelNew movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.tv_serial.setText(""+movie.getId());
        holder.tv_date.setText(movie.getDate());



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}