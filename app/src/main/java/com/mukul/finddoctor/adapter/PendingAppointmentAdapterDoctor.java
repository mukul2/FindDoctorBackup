package com.mukul.finddoctor.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mukul.finddoctor.Activity.SendBookingActivity;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyDialog;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.StatusResponse;
import com.mukul.finddoctor.widgets.MyDialogList;

import java.util.ArrayList;
import java.util.List;

import static com.mukul.finddoctor.Data.DataStore.testModelList;
import static com.mukul.finddoctor.Data.lis.Confirmedlistener;
import static com.mukul.finddoctor.Data.lis.Pendinglistener;

/**
 * Created by mukul on 3/10/2019.
 */


public class PendingAppointmentAdapterDoctor extends RecyclerView.Adapter<PendingAppointmentAdapterDoctor.MyViewHolder>
        implements ApiListener.appointmentStateChangeListener,
        ApiListener.appoinetmentsDownloadListener {
    List<AppointmentModel> list = new ArrayList<>();

    Context context;
    int triggeredItem = 0;
    List<String> TestList = new ArrayList<>();
    int pos;

    @Override
    public void onAppointmentDownloadSuccess(AppointmentResponse status) {
        Confirmedlistener.onDownloaded(status.getConfirmed());
        Pendinglistener.onDownloaded(status.getNotConfirmed());
    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_problem, tv_date;
        ImageView circleImageView;
        RelativeLayout relative_container;
        TextView cardPrescribeTest,tv_serial;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_problem = (TextView) view.findViewById(R.id.tv_problem);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            cardPrescribeTest = (TextView) view.findViewById(R.id.cardPrescribeTest);
            tv_serial = (TextView) view.findViewById(R.id.tv_serial);


        }
    }


    public PendingAppointmentAdapterDoctor(List<AppointmentModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_appointment_dr, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getAppointmentFor());
        holder.tv_serial.setText(movie.getAppointment_id());
        holder.tv_problem.setText(movie.getProblems());
        holder.tv_date.setText(movie.getDate());

        holder.itemView.setOnClickListener((View v) -> changeState(movie.getAppointment_id(), position));
        holder.cardPrescribeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogList.getInstance().with((Activity) context).showTestList(new MyDialogList.testSelectedListener() {
                    @Override
                    public void onDialogCloased(List<String> selectedTest) {
                        TestList.clear();
                        TestList.addAll(selectedTest);

                        if (TestList.size() > 0) {
                            pos = position;
                            MyProgressBar.with(context).show();
                            addRecommendTest(movie.getAppointment_id(), TestList.get(0), 0);

                        }

                    }
                });
            }
        });

    }

    private void addRecommendTest(String appointment_id, String s, int index) {
        Api.getInstance().postRecommendationTest(appointment_id, s, new ApiListener.recomendationTestPostListener() {
            @Override
            public void onrecomendationTestPostSuccess(StatusResponse response) {

                int in = 1 + index;
                if (TestList.size() > in) {

                    addRecommendTest(appointment_id, TestList.get(in), in);
                } else {
                    MyProgressBar.dismiss();
                    changeToRecommended(appointment_id, pos);
                }


            }

            @Override
            public void onrecomendationTestPostFailed(String msg) {
                MyProgressBar.dismiss();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


            }
        });
    }


    public void changeState(String appointment_id, int pos) {
        MyProgressBar.with(context);
        triggeredItem = pos;
        Api.getInstance().changeStatus(appointment_id, "1", this);

    }

    public void changeToRecommended(String appointment_id, int pos) {
        MyProgressBar.with(context);
        triggeredItem = pos;
        Api.getInstance().changeStatus(appointment_id, "2", this);

    }

    @Override
    public void onAppointmentChangeSuccess(StatusResponse status) {
        MyProgressBar.dismiss();
        if (status.getStatus()) {
            MyDialog.getInstance().with((Activity) context)
                    .message("This appointment has been confirmed")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
            // list.remove(triggeredItem);
            if (removeItem(triggeredItem)) {
                notifyItemRemoved(triggeredItem);
                notifyItemRangeChanged(triggeredItem, getItemCount());
            }
           // Api.getInstance().getAppointmentsByDoctor(USER_ID, this);

        } else {
            MyDialog.getInstance().with((Activity) context)
                    .message("Failed")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }

    }

    public boolean removeItem(int position) {
        if (list.size() >= position + 1) {
            list.remove(position);
            return true;
        }
        return false;
    }

    @Override
    public void onPppointmentChangeFailed(String msg) {
        MyProgressBar.dismiss();
        MyDialog.getInstance().with((Activity) context)
                .message("Failed")
                .autoBack(false)
                .autoDismiss(false)
                .showMsgOnly();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}