package com.mukul.finddoctor.adapter;

import android.content.Context;
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

import com.google.gson.JsonObject;
import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.Chamber;
import com.mukul.finddoctor.model.DaysTimeModel;
import com.mukul.finddoctor.model.DrChamberResponse;
import com.mukul.finddoctor.widgets.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class ChambersListAdapterDr extends RecyclerView.Adapter<ChambersListAdapterDr.MyViewHolder> {
    List<DrChamberResponse>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_location,tv_sittingCount,tv_fees;
        RecyclerView recycler_view;

        public MyViewHolder(View view) {
            super(view);
            tv_sittingCount = (TextView) view.findViewById(R.id.tv_sittingCount);
            tv_location = (TextView) view.findViewById(R.id.tv_location);
            tv_fees = (TextView) view.findViewById(R.id.tv_fees);
            recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);



        }
    }


    public ChambersListAdapterDr(List<DrChamberResponse > lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chambers_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DrChamberResponse  movie = list.get(position);
        context = holder.tv_location.getContext();
        holder.tv_location.setText(movie.getAddress());
        holder.tv_fees.setText("Fees : "+movie.getVisitFee()+" TK");
        if (movie.getSitingdays()!=null &&movie.getSitingdays().size()==0) {
            holder.tv_sittingCount.setText("No date is added");
        }else {
            List<DaysTimeModel>models=new ArrayList<>();
            for (int i=0;i<movie.getSitingdays().size();i++){
                models.add(new DaysTimeModel(movie.getSitingdays().get(i).getDay(),movie.getSitingdays().get(i).getStartTime(),movie.getSitingdays().get(i).getEndTime()));
            }
            ChamberDaysListAdapter mAdapter = new ChamberDaysListAdapter(models);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            holder.recycler_view.setLayoutManager(mLayoutManager);
            holder.recycler_view.setItemAnimator(new DefaultItemAnimator());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL,false);
            holder.recycler_view.setAdapter(mAdapter);
            holder.recycler_view.addItemDecoration(dividerItemDecoration);


//            holder.tv_sittingCount.setVisibility(View.VISIBLE);
//            String detail="";
//            for (int i=0;i<movie.getSitingdays().size();i++){
//                detail+=DataStore.convertToWeekDay(movie.getSitingdays().get(i).getDay())+" "+movie.getSitingdays().get(i).getStartTime()+"-"+movie.getSitingdays().get(i).getEndTime();
//                if (i!=movie.getSitingdays().size()-1){
//                    detail+="\n";
//                }
//            }
//            holder.tv_sittingCount.setText(detail);


        }



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}