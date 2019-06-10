package com.mukul.finddoctor.Data;



import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.Day;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.RecomentationModel;
import com.mukul.finddoctor.model.SpacialistModel;
import com.mukul.finddoctor.model.TestList;
import com.mukul.finddoctor.model.VideoCallModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkl on 2/14/2019.
 */

public class Data {

    public static String TYPE_DOCTOR="d";
    public static String TYPE_PATIENT="p";
    public static  List<SpacialistModel> spacialist = new ArrayList<>();
    public static  List<DoctorModel> searchResult = new ArrayList<>();
    public static  DoctorModel singleDrModel;
    public static RecomentationModel testList ;
    public static AppointmentModel appointmentModel ;
    public static AppointmentModel2 drServingModel ;
    public static VideoCallModel CurentCallDr ;

    public static  List<Day> days = new ArrayList<>();
    public static String USER_NAME;
    public static String TEMP_LINK;

    public static String BaseUrl="";
    public static String PHOTO_BASE="";
    public static String FACEBOOK_LINK="";
    public static int STATUS_PENDING=0;
    public static int STATUS_APPROVED=1;
    public static int STATUS_TEST_RECOMMENED=2;
    public static int STATUS_SERVED=3;
    public static int STATUS_CANCEL=4;
    public static int STATUS_DELETE=5;
    public  static String getColorCode(int pos){
        List<String>colors=new ArrayList<>();
       /* colors.add("#DC7633");
        colors.add("#2E4053");
        colors.add("#2ECC71");
        colors.add("#27AE60");
        colors.add("#48C9B0");
        colors.add("#2980B9");
        colors.add("#8E44AD");*/
       // colors.add("#E74C3C");
        colors.add("#3498DB");
        colors.add("#45B39D");
        colors.add("#1F618D");
        return colors.get( pos%colors.size());
    }

    public  static List<String> getDistricts(){
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("DHAKA");
        categories.add("FARIDPUR");
        categories.add("GAZIPUR");
        categories.add("GOPALGANJ");
        categories.add("JAMALPUR");
        categories.add("KISHOREGONJ");
        categories.add("MADARIPUR");
        categories.add("MANIKGANJ");
        categories.add("MUNSHIGANJ");
        categories.add("MYMENSINGH");
        categories.add("NARAYANGANJ");
        categories.add("NARSINGDI");
        categories.add("NETRAKONA");
        categories.add("RAJBARI");
        categories.add("SHARIATPUR");
        categories.add("SHERPUR");
        categories.add("TANGAIL");
        categories.add("BARGUNA");
        categories.add("BARISAL");
        categories.add("BHOLA");
        categories.add("JHALOKATI");
        categories.add("PATUAKHALI");
        categories.add("PIROJPUR ");
        categories.add("BANDARBAN");
        categories.add("BRAHMANBARIA");
        categories.add("CHANDPUR");
        categories.add("CHITTAGONG");
        categories.add("COMILLA");
        categories.add("COX'S BAZAR");
        categories.add("COX'S BAZAR");
        categories.add("KHAGRACHHARI");
        categories.add("LAKSHMIPUR");
        categories.add("NOAKHALI");
        categories.add("RANGAMATI ");
        categories.add("BAGERHAT");
        categories.add("CHUADANGA");
        categories.add("JESSORE");
        categories.add("JHENAIDAH");
        categories.add("KHULNA");
        categories.add("KUSHTIA");
        categories.add("MAGURA");
        categories.add("MEHERPUR");
        categories.add("NARAIL");
        categories.add("SATKHIRA");
        categories.add("BOGRA");
        categories.add("CHAPAINABABGANJ");
        categories.add("JOYPURHAT");
        categories.add("PABNA");
        categories.add("NAOGAON");
        categories.add("NATORE");
        categories.add("RAJSHAHI");
        categories.add("SIRAJGANJ");
        categories.add("DINAJPUR");
        categories.add("GAIBANDHA");
        categories.add("KURIGRAM");
        categories.add("LALMONIRHAT");
        categories.add("NILPHAMARI");
        categories.add("PANCHAGARH");
        categories.add("RANGPUR");
        categories.add("THAKURGAON");
        categories.add("HABIGANJ");
        categories.add("MAULVIBAZAR");
        categories.add("SUNAMGANJ");
        categories.add("SYLHET");
        return categories;
    }
    public  static List<String>getAllStatusTypes(){
        List<String>list=new ArrayList<>();
        list.add("Pending");
        list.add("Approved");
        list.add("Test Recommened");
        list.add("Served");
        list.add("Cancel");
        list.add("Delete");
        return list;
    }


}
