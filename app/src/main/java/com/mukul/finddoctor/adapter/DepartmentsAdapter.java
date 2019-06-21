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
import com.mukul.finddoctor.Activity.DrListGridActivity;
import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.DepartmentModel;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.SpecialistNameCount;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.TYPE_OF_ACTIVITY;
import static com.mukul.finddoctor.Data.Data.searchResult;
import static com.mukul.finddoctor.Data.DataStore.CLICKED_TITLE;
import static com.mukul.finddoctor.Data.DataStore.downloadedDoctors;

/**
 * Created by mukul on 3/10/2019.
 */


public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.MyViewHolder> {
    List<DepartmentModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_count;
        CardView card;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_count = (TextView) view.findViewById(R.id.tv_count);
            card = (CardView) view.findViewById(R.id.card);


        }
    }


    public DepartmentsAdapter(List<DepartmentModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospitals_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DepartmentModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.tv_count.setText("0");
        holder.card.setCardBackgroundColor(Color.parseColor(Data.getColorCode(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MyProgressBar.with(context).show();
                // CLICKED_TITLE = movie.getName();
                if (TYPE_OF_ACTIVITY.equals("review")) {
                    context.startActivity(new Intent(context, DrListGridActivity.class));

                } else {

                    context.startActivity(new Intent(context, DrListActivity.class));

                }
//                Api.getInstance().searchDoctor("", "", movie.getName(), "", "", new ApiListener.doctorSearchListener() {
//                    @Override
//                    public void onSearchSuccess(List<DoctorModel> list) {
//                        MyProgressBar.dismiss();
//                        if (list!=null) {
//                            searchResult.clear();
//                            searchResult = list;
//                            context.startActivity(new Intent(context, DrListActivity.class));
//                        }else {
//                            Toast.makeText(context, "Network error.Please try again", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onSuccessFailed(String msg) {
//
//                    }
//                });

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}