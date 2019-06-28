package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mukul.finddoctor.Fragments.AppointmentsFragment;
import com.mukul.finddoctor.Fragments.BlogFragmentPatient;
import com.mukul.finddoctor.Fragments.HomeFragment;
import com.mukul.finddoctor.Fragments.MedicineFragment;
import com.mukul.finddoctor.Fragments.ProfileFragment;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.CustomDrawerButton;
import com.mukul.finddoctor.Utils.SessionManager;
import com.mukul.finddoctor.api.Api;
import com.mukul.finddoctor.api.ApiListener;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.SpecialistNameCount;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mukul.finddoctor.Data.Data.PHOTO_BASE;
import static com.mukul.finddoctor.Data.DataStore.TOKEN;
import static com.mukul.finddoctor.Data.DataStore.USER_ID;

public class PatientHomeActivity extends AppCompatActivity implements View.OnClickListener, ApiListener.basicInfoDownloadListener {
    Context context = this;
    Resources resources;
    int primaryClr, another;

    @BindView(R.id.profilePic)
    CircleImageView profilePic;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.tv_4)
    TextView tv_4;
    int anotherColorText = Color.GRAY;

    @BindView(R.id.img_1)
    ImageView img_1;
    @BindView(R.id.img_2)
    ImageView img_2;
    @BindView(R.id.img_3)
    ImageView img_3;
    @BindView(R.id.img_4)
    ImageView img_4;
    @BindView(R.id.linerhomebutton)
    LinearLayout linerHomeButton;
    @BindView(R.id.linerMedicineButton)
    LinearLayout linerMedicineButton;
    @BindView(R.id.linerAppointmentButton)
    LinearLayout linerAppointmentButton;
    @BindView(R.id.linerProfileButton)
    LinearLayout linerProfileButton;
    // LinearLayout.LayoutParams enable = new LinearLayout.LayoutParams(30, 30);
    //  LinearLayout.LayoutParams disbale = new LinearLayout.LayoutParams(25, 25);
    public static List<String> HOSPITALS = new ArrayList<>();
    public static List<SpecialistNameCount> SPECIALIST = new ArrayList<>();
    SessionManager sessionManager;

    @BindView(R.id.customDrawer)
    CustomDrawerButton customDrawerButton;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);

        TOKEN = sessionManager.getToken();
        USER_ID = sessionManager.getUserId();


        getColorManagement();
        linerHomeButton.setOnClickListener(this);
        linerMedicineButton.setOnClickListener(this);
        linerAppointmentButton.setOnClickListener(this);
        linerProfileButton.setOnClickListener(this);


        initial_fragment();
        Api.getInstance().downloadBasicInfo(this);
        setUpStatusbar();
        initIconDesnsity();

        customDrawerButton.setDrawerLayout(drawer_layout);
        customDrawerButton.getDrawerLayout().addDrawerListener(customDrawerButton);
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
        Glide.with(PatientHomeActivity.this).load(PHOTO_BASE+ sessionManager.get_userPhoto()).into(profilePic);
        Toast.makeText(context, sessionManager.get_userPhoto(), Toast.LENGTH_SHORT).show();
        //Glide.with(PatientHomeActivity.this).load("https://appointmentbd.com/frontend/user/1561548940.jpg").into(profilePic);
        tv_name.setText(sessionManager.getUserName());

    }

    private void initIconDesnsity() {
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // set your height here
        final ViewGroup.LayoutParams layoutParams = img_1.getLayoutParams();
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 19, displayMetrics);
        // set your width here
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 19, displayMetrics);
        img_1.setLayoutParams(layoutParams);
        img_2.setLayoutParams(layoutParams);
        img_3.setLayoutParams(layoutParams);
        img_4.setLayoutParams(layoutParams);
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

    @Override
    public void onBasicInfoDownloadSuccess(BasicInfoModel data) {
        if (data != null) {
            HOSPITALS = data.getHospitalList();
            SPECIALIST = data.getSpacialist();

        } else {
            // onBackPressed();
            Toast.makeText(this, "Something is not right.Try again later", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBasicInfoDownloadFailed(String msg) {
        //onBackPressed();
        Toast.makeText(this, "Something is not right.Try again later", Toast.LENGTH_SHORT).show();


    }

    private void initial_fragment() {
        Fragment selectedFragment = null;
        selectedFragment = HomeFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
        do_1_0_0_0();
    }

    private void getColorManagement() {
        resources = context.getResources();
        primaryClr = resources.getColor(R.color.colorPrimary);
        another = Color.GRAY;
    }

    public void do_0_1_0_0() {


        //iv.setLayoutParams(layoutParams);
        img_1.setImageResource(R.drawable.home_gray);
        img_2.setImageResource(R.drawable.profile_primary);
        img_3.setImageResource(R.drawable.calender_gray);
        img_4.setImageResource(R.drawable.blog);

        tv_1.setTextColor(another);
        tv_2.setTextColor(primaryClr);
        tv_3.setTextColor(another);
        tv_4.setTextColor(another);


//        img_1.setLayoutParams(disbale);
//        img_2.setLayoutParams(enable);
//        img_3.setLayoutParams(disbale);
//        img_4.setLayoutParams(disbale);


    }

    public void do_1_0_0_0() {


        img_1.setImageResource(R.drawable.home_primary);
        img_2.setImageResource(R.drawable.profile_gray);
        img_3.setImageResource(R.drawable.calender_gray);
        img_4.setImageResource(R.drawable.blog);

        tv_1.setTextColor(primaryClr);
        tv_2.setTextColor(another);
        tv_3.setTextColor(another);
        tv_4.setTextColor(another);

//        img_1.setLayoutParams(enable);
//        img_2.setLayoutParams(disbale);
//        img_3.setLayoutParams(disbale);
//        img_4.setLayoutParams(disbale);

    }

    public void do_0_0_1_0() {

        img_1.setImageResource(R.drawable.home_gray);
        img_2.setImageResource(R.drawable.profile_gray);
        img_3.setImageResource(R.drawable.calender_primary);
        img_4.setImageResource(R.drawable.blog);


        tv_1.setTextColor(another);
        tv_2.setTextColor(another);
        tv_3.setTextColor(primaryClr);
        tv_4.setTextColor(another);
//
//        img_1.setLayoutParams(disbale);
//        img_2.setLayoutParams(disbale);
//        img_3.setLayoutParams(enable);
//        img_4.setLayoutParams(disbale);


    }

    public void do_0_0_0_1() {

        img_1.setImageResource(R.drawable.home_gray);
        img_2.setImageResource(R.drawable.profile_gray);
        img_3.setImageResource(R.drawable.calender_gray);
        img_4.setImageResource(R.drawable.blog_primary);


        tv_1.setTextColor(another);
        tv_2.setTextColor(another);
        tv_3.setTextColor(another);
        tv_4.setTextColor(primaryClr);

//        img_1.setLayoutParams(disbale);
//        img_2.setLayoutParams(disbale);
//        img_3.setLayoutParams(disbale);
//        img_4.setLayoutParams(enable);


    }

    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        switch (view.getId()) {
            case R.id.linerhomebutton:
                selectedFragment = HomeFragment.newInstance();
                do_1_0_0_0();
                break;
            case R.id.linerMedicineButton:
                selectedFragment = BlogFragmentPatient.newInstance();
                do_0_0_0_1();
                break;
            case R.id.linerAppointmentButton:
                selectedFragment = AppointmentsFragment.newInstance();
                do_0_0_1_0();
                break;
            case R.id.linerProfileButton:
                selectedFragment = ProfileFragment.newInstance();
                do_0_1_0_0();
                break;

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();

    }
}
