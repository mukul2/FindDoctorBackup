package com.mukul.finddoctor.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mukul.finddoctor.Data.SinchCons;
import com.mukul.finddoctor.Fragments.VideoCallFragmenttFragmentDoctor;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.adapter.CallLogHistoryAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.CallHistoryPatient;
import com.mukul.finddoctor.model.StatusMessage;
import com.mukul.finddoctor.widgets.DividerItemDecoration;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoCallActivityDr extends AppCompatActivity implements  ApiListener.doctorCallLogListener{
    Context context=this;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.switch_online)
    Switch switch_online;
    SessionManager sessionManager;
    String USER_ID;
    ApiListener.doctorOnlineStatusChangeListener listener;
    SinchClient sinchClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_dr);
        sessionManager=new SessionManager(context);
        USER_ID=sessionManager.getUserId();

        ButterKnife.bind(this);
        // changeDrOnlineStatus
        switch_online.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // MyProgressBar.with(context);
                if (b){


                    Api.getInstance().changeDrOnlineStatus(USER_ID,"1",listener);
                }else {
                    Api.getInstance().changeDrOnlineStatus(USER_ID,"0",listener);
                }
            }
        });
        listener=new ApiListener.doctorOnlineStatusChangeListener() {
            @Override
            public void ondoctorOnlineStatusChangeSuccess(StatusMessage statusMessage) {
                // MyProgressBar.dismiss();
                Toast.makeText(context, statusMessage.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void ondoctorOnlineStatusChangeFailed(String msg) {
                // MyProgressBar.dismiss();

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        };
        askPermissions();
        Api.getInstance().downloadCallLogDoctor(USER_ID,this);
    }
    private void askPermissions() {
        Dexter.withActivity((Activity)context)
                .withPermissions(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.MODIFY_AUDIO_SETTINGS,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            //RECEIVER_ID
                            //CALLER_ID
                            sinchClient = Sinch.getSinchClientBuilder()
                                    .context(context)
                                    .userId(USER_ID)
                                    .applicationKey(SinchCons.applicationKey)
                                    .applicationSecret(SinchCons.applicationSecret)
                                    .environmentHost(SinchCons.environmentHost)
                                    .build();
                            sinchClient.startListeningOnActiveConnection();

                            sinchClient.setSupportCalling(true);
                            sinchClient.start();
                            sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
                            // sinchClient.getCallClient().addCallClientListener(new Video);




                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }
    @Override
    public void onDoctorCallLogSuccess(List<CallHistoryPatient> list) {
        CallLogHistoryAdapter mAdapter = new CallLogHistoryAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onDoctorCallLogFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            //Pick up the call!
            SinchCons.incomingCallInstance=incomingCall;
            SinchCons.callSnichClient=sinchClient;
            Toast.makeText(context, "Incomming call deteted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context, CallReceiveActivity.class));

        }
    }
    public void Back(View view) {
        onBackPressed();
    }
}
