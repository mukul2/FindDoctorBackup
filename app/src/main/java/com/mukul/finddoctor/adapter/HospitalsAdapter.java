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
import android.widget.Toast;

import com.mukul.finddoctor.Activity.DrListActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.DoctorModel;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.DataStore.CLICKED_TITLE;
import static com.mukul.finddoctor.Data.DataStore.downloadedDoctors;

/**
 * Created by mukul on 3/10/2019.
 */


public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.MyViewHolder> {
    List<String>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;



        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);



        }
    }


    public HospitalsAdapter(List<String> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospitals_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProgressBar.with(context).show();
                CLICKED_TITLE=movie;
                Api.getInstance().searchDoctor("", movie, "", "", "", new ApiListener.doctorSearchListener() {
                    @Override
                    public void onSearchSuccess(List<DoctorModel> list) {
                        MyProgressBar.dismiss();
                        if (list!=null) {
                            downloadedDoctors = list;
                            context.startActivity(new Intent(context, DrListActivity.class));
                        }else {
                            Toast.makeText(context, "Network error.Please try again", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onSuccessFailed(String msg) {

                    }
                });

            }
        });



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}