package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.Day;
import com.mukul.finddoctor.model.TestList;
import com.mukul.finddoctor.model.TestName;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.Data.singleDrModel;

/**
 * Created by mukul on 3/10/2019.
 */


public class TestListTypeAdapter extends RecyclerView.Adapter<TestListTypeAdapter.MyViewHolder> {

    Context context;
    List<TestName>testLists=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day, tv_time,tv_sl;

        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_sl = (TextView) view.findViewById(R.id.tv_sl);


        }
    }


    public TestListTypeAdapter(List<TestName> testLists_ ) {
        this.testLists=testLists_;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_names_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final TestName movie = testLists.get(position);

        holder.tv_day.setText(movie.getName());
        holder.tv_sl.setText(""+(position+1)+".");
        holder.tv_time.setText(movie.getType());


    }

    @Override
    public int getItemCount() {
        return testLists.size();
    }
}