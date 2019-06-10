package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Activity.DrListActivity;
import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.DrService;
import com.mukul.finddoctor.model.SpecialistNameCount;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.searchResult;
import static com.mukul.finddoctor.Data.DataStore.CLICKED_TITLE;

/**
 * Created by mukul on 3/10/2019.
 */


public class DrOnlineServicesAdapter extends RecyclerView.Adapter<DrOnlineServicesAdapter.MyViewHolder> {
    List<DrService>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;



        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);




        }
    }


    public DrOnlineServicesAdapter(List<DrService> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_single_service_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DrService movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}