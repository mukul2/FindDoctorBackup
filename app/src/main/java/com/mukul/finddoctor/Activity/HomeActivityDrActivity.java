package com.mukul.finddoctor.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mukul.finddoctor.Fragments.AppointmentsFragment;
import com.mukul.finddoctor.Fragments.HomeFragment;
import com.mukul.finddoctor.Fragments.HomeFragmentDr;
import com.mukul.finddoctor.Fragments.HomeFragmentDrTwo;
import com.mukul.finddoctor.Fragments.MedicineFragment;
import com.mukul.finddoctor.Fragments.SettingFragmentDr;
import com.mukul.finddoctor.R;
import com.mukul.finddoctor.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mukul.finddoctor.Data.DataStore.USER_ID;

public class HomeActivityDrActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    Resources resources;
    int primaryClr, another;

    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.tv_three)
    TextView tv_three;
    int anotherColorText= Color.GRAY;

    @BindView(R.id.img_one)
    ImageView img_one;
    @BindView(R.id.img_two)
    ImageView img_two;
    @BindView(R.id.img_three)
    ImageView img_three;
    @BindView(R.id.linerhomebutton)
    LinearLayout linerHomeButton;
    @BindView(R.id.linerShopButton)
    LinearLayout linerShopButton;
    @BindView(R.id.linerAppointmentButton)
    LinearLayout linerAppointmentButton;
    LinearLayout.LayoutParams enable = new LinearLayout.LayoutParams(28, 28);
    LinearLayout.LayoutParams disbale = new LinearLayout.LayoutParams(20, 20);
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dr);
        setUpStatusbar();
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        USER_ID = sessionManager.getUserId();
        getColorManagement();
        linerHomeButton.setOnClickListener(this);
        linerShopButton.setOnClickListener(this);
        linerAppointmentButton.setOnClickListener(this);

        img_one.setLayoutParams(disbale);
        img_two.setLayoutParams(enable);
        img_three.setLayoutParams(disbale);
        initial_fragment();
    }
    private void initial_fragment() {
        Fragment selectedFragment = null;
        selectedFragment = HomeFragmentDr.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

    private void getColorManagement() {
        resources = context.getResources();
        primaryClr = resources.getColor(R.color.colorPrimary);
        another = Color.GRAY;
    }

    public void do_0_1_0() {


        //iv.setLayoutParams(layoutParams);
        img_one.setImageResource(R.drawable.medicine_gray);
        img_two.setImageResource(R.drawable.home_primary);
        img_three.setImageResource(R.drawable.setting_gray);

        tv_one.setTextColor(another);
        tv_two.setTextColor(primaryClr);
        tv_three.setTextColor(another);



        img_one.setLayoutParams(disbale);
        img_two.setLayoutParams(enable);
        img_three.setLayoutParams(disbale);



    }
    public void do_1_0_0() {


        img_one.setImageResource(R.drawable.medicine_primary);
        img_two.setImageResource(R.drawable.home_gray);
        img_three.setImageResource(R.drawable.setting_gray);

        tv_one.setTextColor(primaryClr);
        tv_two.setTextColor(another);
        tv_three.setTextColor(another);

        img_one.setLayoutParams(enable);
        img_two.setLayoutParams(disbale);
        img_three.setLayoutParams(disbale);

    }
    public void do_0_0_1() {

        img_one.setImageResource(R.drawable.medicine_gray);
        img_two.setImageResource(R.drawable.home_gray);
        img_three.setImageResource(R.drawable.setting_primary);


        tv_one.setTextColor(another);
        tv_two.setTextColor(another);
        tv_three.setTextColor(primaryClr);

        img_one.setLayoutParams(disbale);
        img_two.setLayoutParams(disbale);
        img_three.setLayoutParams(enable);


    }
    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        switch (view.getId()) {
            case R.id.linerhomebutton:
                selectedFragment = HomeFragmentDr.newInstance();
                do_0_1_0();
                break;
            case R.id.linerShopButton:
                selectedFragment = HomeFragmentDrTwo.newInstance();
                do_1_0_0();
                break;
            case R.id.linerAppointmentButton:
                selectedFragment = SettingFragmentDr.newInstance();
                do_0_0_1();
                break;

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();

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
}
