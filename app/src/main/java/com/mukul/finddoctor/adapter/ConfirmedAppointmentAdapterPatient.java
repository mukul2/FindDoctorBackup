package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mukul.finddoctor.Activity.ConfirmedAppointmentDetailActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.appointmentModel;

/**
 * Created by mukul on 3/10/2019.
 */


public class ConfirmedAppointmentAdapterPatient extends RecyclerView.Adapter<ConfirmedAppointmentAdapterPatient.MyViewHolder> {
    List<AppointmentModel>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_address, tv_appointmentfor, tv_date,tv_viewDetails;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_appointmentfor = (TextView) view.findViewById(R.id.tv_appointmentfor);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_viewDetails = (TextView) view.findViewById(R.id.tv_viewDetails);


        }
    }


    public ConfirmedAppointmentAdapterPatient(List<AppointmentModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_confirmed_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getDrName());
        holder.tv_address.setText(movie.getAddress());
        holder.tv_appointmentfor.setText(movie.getAppointmentFor());
        holder.tv_date.setText(changeDateformate(movie.getDate()));
        holder.tv_viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appointmentModel=movie;
                context.startActivity(new Intent(context, ConfirmedAppointmentDetailActivity.class));
            }
        });


    }

    private String changeDateformate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat("MMM dd");
        return targetFormat.format(sourceDate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}