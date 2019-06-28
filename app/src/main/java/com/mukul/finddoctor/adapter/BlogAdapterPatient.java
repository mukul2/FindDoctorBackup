package com.mukul.finddoctor.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.BlogModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mukul.finddoctor.Data.Data.PHOTO_BASE;

/**
 * Created by mukul on 3/10/2019.
 */


public class BlogAdapterPatient extends RecyclerView.Adapter<BlogAdapterPatient.MyViewHolder> {
    List<BlogModel>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_drName, tv_department, tv_body;
        ImageView circleImageView;
        RelativeLayout relative_container;
        CircleImageView profile;
        RecyclerView recycler_view;


        public MyViewHolder(View view) {
            super(view);
            tv_drName = (TextView) view.findViewById(R.id.tv_drName);
            tv_department = (TextView) view.findViewById(R.id.tv_department);
            tv_body = (TextView) view.findViewById(R.id.tv_body);
            profile = (CircleImageView) view.findViewById(R.id.profile);
            recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);



        }
    }


    public BlogAdapterPatient(List<BlogModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final BlogModel movie = list.get(position);
        context = holder.tv_drName.getContext();
        holder.tv_drName.setText(movie.getDrInfo().getName());
        holder.tv_body.setText(movie.getBody());
        holder.tv_department.setText(movie.getDrInfo().getDepartment_info().getName());
        Glide.with(context).load(PHOTO_BASE+movie.getDrInfo().getPhoto()).into(holder.profile);

        GallaryAdapterOnline  mAdapter = new GallaryAdapterOnline(context, movie.getPhotoInfo());
        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        holder.recycler_view.setLayoutManager(_sGridLayoutManager);
        holder.recycler_view.setItemAnimator(new DefaultItemAnimator());
        holder.recycler_view.setAdapter(mAdapter);



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}