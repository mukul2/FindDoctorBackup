package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Activity.ChamberDetailActivity;
import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.Day;
import com.mukul.finddoctor.model.DoctorModel;

import java.util.List;

import static com.mukul.finddoctor.Data.Data.days;
import static com.mukul.finddoctor.Data.Data.searchResult;
import static com.mukul.finddoctor.Data.Data.singleDrModel;

/**
 * Created by mukul on 3/10/2019.
 */


public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder> {

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tv_hospitalName, tv_time, tv_lastDegree, tv_epacialist,tv_address;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_name);
            tv_hospitalName = (TextView) view.findViewById(R.id.tv_hospitalName);
            // tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_lastDegree = (TextView) view.findViewById(R.id.tv_lastDegree);
            tv_epacialist = (TextView) view.findViewById(R.id.tv_epacialist);
            tv_address = (TextView) view.findViewById(R.id.tv_address);


        }
    }


    public SearchResultAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DoctorModel movie = searchResult.get(position);
        context = holder.title.getContext();
        holder.title.setText(movie.getDrName());
        holder.tv_hospitalName.setText(movie.getHospitalName());
        holder.tv_epacialist.setText(movie.getSpecialist()+" spacialist");
        holder.tv_address.setText(movie.getAddress());
//        String time = "";
//        for (int i = 0; i < movie.getDays().size(); i++) {
//            time += movie.getDays().get(i).getDay() + "  " + movie.getDays().get(i).getTime() + "\n";
//        }
//        holder.tv_time.setText(time);
        holder.tv_lastDegree.setText(movie.getLastDegree());
        holder.itemView.setOnClickListener((View v) -> detailView(movie.getDays(),movie));

    }

    private void detailView(List<Day> days, DoctorModel movie) {
        singleDrModel= movie;
       // Data.days.clear();
      //  Data.days.addAll(days);
        context.startActivity(new Intent(context, ChamberDetailActivity.class));
    }

    @Override
    public int getItemCount() {
        return searchResult.size();
    }
}