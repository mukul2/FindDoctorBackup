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
import com.mukul.finddoctor.model.TreatmentHistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class TreatmentHistoryAdapterDoctor extends RecyclerView.Adapter<TreatmentHistoryAdapterDoctor.MyViewHolder> {
    List<TreatmentHistoryModel>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_dr_name, tv_pa_name, tv_comment, tv_date;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_dr_name = (TextView) view.findViewById(R.id.tv_dr_name);
            tv_pa_name = (TextView) view.findViewById(R.id.tv_pa_name);
            tv_comment = (TextView) view.findViewById(R.id.tv_comment);
            tv_date = (TextView) view.findViewById(R.id.tv_date);


        }
    }


    public TreatmentHistoryAdapterDoctor(List<TreatmentHistoryModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.treatment_history_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final TreatmentHistoryModel movie = list.get(position);
        context = holder.tv_dr_name.getContext();
        holder.tv_dr_name.setText(movie.getDrName());
        holder.tv_pa_name.setText(movie.getPatientName());
        holder.tv_comment.setText(movie.getComment());
        holder.tv_date.setText(DataStore.changeDateformate(movie.getPosted()));
//        String time = "";
//        for (int i = 0; i < movie.getDays().size(); i++) {
//            time += movie.getDays().get(i).getDay() + "  " + movie.getDays().get(i).getTime() + "\n";
//        }
//        holder.tv_time.setText(time);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}