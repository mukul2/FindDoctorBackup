package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mukul.finddoctor.Data.Data;
import com.mukul.finddoctor.Data.DataStore;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.MyProgressBar;
import com.mukul.finddoctor.adapter.DaysChamberOpenAdapter;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.AppointmentAddResponse;
import com.mukul.finddoctor.model.DaysChamberOpenModel;
import com.mukul.finddoctor.model.StatusMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.Data.CHAMBER_TO_BOOK;
import static com.mukul.finddoctor.Data.Data.PHOTO_BASE;
import static com.mukul.finddoctor.Data.DataStore.NOW_SHOWING_DOC;
import static com.mukul.finddoctor.Data.DataStore.TOKEN;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;

public class BookingActivityNew extends AppCompatActivity implements ApiListener.AppointmentPOstListener {
    @BindView(R.id.ed_problems)
    EditText ed_problems;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.tv_doctorName)
    TextView tv_doctorName;
    @BindView(R.id.spinnerMonth)
    Spinner spinnerMonth;
    @BindView(R.id.tv_fees)
    TextView tv_fees;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.image)
    ImageView image;
    Context context = this;
    int realMonth;
    int realToday;
    int selectedMonth;
    String selectedDate;

    Calendar calendar;
    public static List<DaysChamberOpenModel> allDays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_new);
        ButterKnife.bind(this);
        // setUpStatusbar();
        // CHAMBER_TO_BOOK;
        tv_doctorName.setText(NOW_SHOWING_DOC.getName());
        tv_fees.setText("৳  " + CHAMBER_TO_BOOK.getFee());
        Glide.with(context).load(PHOTO_BASE + NOW_SHOWING_DOC.getPhoto()).into(image);
        initSpinner();

    }

    private void initSpinner() {
        calendar = Calendar.getInstance();
        //calendar.getTime().getMonth()
        int month = calendar.getTime().getMonth();
        int realMonth = calendar.getTime().getMonth();
        int realToday = calendar.getTime().getDate();
        List<String> daysOnly = DataStore.monthArray();
        daysOnly = daysOnly.subList(month, daysOnly.size());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daysOnly);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(dataAdapter);

        // spinnerMonth.setSelection(month);
        //Toast.makeText(context, "" + calendar.getTime().getDate(), Toast.LENGTH_SHORT).show();
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "" + (month + i), Toast.LENGTH_SHORT).show();
                calendar.set(Calendar.MONTH, (month + i));
                selectedMonth = (month + i);
                if (realMonth == (month + i)) {
                    calendar.set(Calendar.DATE, realToday);
                }

                init_daysList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void init_daysList() {
        allDays.clear();

        int thisMonth = calendar.getTime().getMonth();
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        int today = calendar.getTime().getDate();
        //calendar.set(Calendar.DATE, 1);

        for (int day = today; day <= lastDay; day++) {

            if (true) {
                for (int i = 0; i < CHAMBER_TO_BOOK.getChamberDays().size(); i++) {
                    if (calendar.getTime().getDay() == CHAMBER_TO_BOOK.getChamberDays().get(i).getDay()) {
                        allDays.add(new DaysChamberOpenModel(false, calendar.getTime().getDate(), calendar.getTime().getDay()));


                    }
                }

            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        Toast.makeText(context, "" + allDays.size(), Toast.LENGTH_SHORT).show();
        initRecycler();
    }

    private void initRecycler() {

        DaysChamberOpenAdapter mAdapter = new DaysChamberOpenAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    private void setUpStatusbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void submit(View view) {
        //startActivity(new Intent(this, BookingConfirmActivity.class));
        for (int i = 0; i < allDays.size(); i++) {
            if (allDays.get(i).isSelected()) {
                Toast.makeText(context, "" + allDays.get(i).getDate(), Toast.LENGTH_SHORT).show();
                selectedDate = "" + allDays.get(i).getDate() + "/" + selectedMonth +"/"+ "2019";
                String name = ed_name.getText().toString().trim();
                String problem = ed_problems.getText().toString().trim();
                String phone = ed_phone.getText().toString().trim();
                if (selectedDate != null && selectedDate.length() > 0) {
                    if (name.length() > 0) {
                        if (problem.length() > 0) {
                            if (phone.length() > 0) {
                                MyProgressBar.with(BookingActivityNew.this);
                                Api.getInstance().addAppointmentInfo(TOKEN, USER_ID, "" + CHAMBER_TO_BOOK.getDrId(), problem, phone, name, "" + CHAMBER_TO_BOOK.getId(), selectedDate, "0", this);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAppointmentPOStSuccess(AppointmentAddResponse data) {
        MyProgressBar.dismiss();
        if (data.getStatus() == true) {
            Intent intent = new Intent(this, BookingConfirmActivity.class);
            intent.putExtra("refID", "" + data.getAppointmentId());
            intent.putExtra("name", ed_name.getText().toString().trim());
            intent.putExtra("date", selectedDate);
            startActivity(intent);
        }
    }

    @Override
    public void onAppointmentPOStFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
