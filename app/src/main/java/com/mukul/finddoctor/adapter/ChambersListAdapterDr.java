package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.Chamber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class ChambersListAdapterDr extends RecyclerView.Adapter<ChambersListAdapterDr.MyViewHolder> {
    List<Chamber>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_location, tv_details;

        public MyViewHolder(View view) {
            super(view);
            tv_location = (TextView) view.findViewById(R.id.tv_location);
            tv_details = (TextView) view.findViewById(R.id.tv_details);



        }
    }


    public ChambersListAdapterDr(List<Chamber> lists ) {
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
        final Chamber movie = list.get(position);
        context = holder.tv_location.getContext();
        holder.tv_location.setText("Chamber Address : "+movie.getAddress());
        String details="";
        try {
            JSONArray array=new JSONArray(movie.getDays());
            for (int i=0;i<array.length();i++){
                JSONObject object= array.getJSONObject(i);
                details+= DataStore.convertToWeekDay( object.getString("day"))+"  "+object.getString("time")+"\n";
            }
            holder.tv_details.setText(details);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}