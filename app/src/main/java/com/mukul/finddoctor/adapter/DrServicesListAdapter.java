package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.ServiceWithBoolean;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Activity.ServicesActivityDr.SERVICES_LIST;

/**
 * Created by mukul on 3/10/2019.
 */


public class DrServicesListAdapter extends RecyclerView.Adapter<DrServicesListAdapter.MyViewHolder> {

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        CheckBox checkbox;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            checkbox = (CheckBox) view.findViewById(R.id.checkbox);


        }
    }


    public DrServicesListAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_services_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ServiceWithBoolean data = SERVICES_LIST.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(data.getServiceName().getName());
        if (data.isSelected()){
            holder.checkbox.setChecked(true);
        }else {
            holder.checkbox.setChecked(false);

        }
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    SERVICES_LIST.get(position).setSelected(true);
                }else {
                    SERVICES_LIST.get(position).setSelected(false);

                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return SERVICES_LIST.size();
    }
}