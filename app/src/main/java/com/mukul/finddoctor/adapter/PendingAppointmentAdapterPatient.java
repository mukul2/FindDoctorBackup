package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.finddoctor.Activity.ConfirmedAppointmentDetailActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModelNew;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.appointmentModel;

/**
 * Created by mukul on 3/10/2019.
 */


public class PendingAppointmentAdapterPatient extends RecyclerView.Adapter<PendingAppointmentAdapterPatient.MyViewHolder> {
    List<AppointmentModelNew> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_address, tv_appointmentfor, tv_date, tv_viewDetails, tv_serial;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_appointmentfor = (TextView) view.findViewById(R.id.tv_appointmentfor);
            tv_date = (TextView) view.findViewById(R.id.tv_date);

            tv_viewDetails = (TextView) view.findViewById(R.id.tv_viewDetails);

        }
    }


    public PendingAppointmentAdapterPatient(List<AppointmentModelNew> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_pending_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModelNew movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText("" + movie.getDrId());
        //  holder.tv_address.setText(movie.get());
        holder.tv_appointmentfor.setText(movie.getName());
        holder.tv_date.setText(movie.getDate());
        holder.tv_viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // appointmentModel=movie;
                context.startActivity(new Intent(context, ConfirmedAppointmentDetailActivity.class));
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}