package com.mukul.finddoctor.api;


import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.Chamber;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.LoginResponse;
import com.mukul.finddoctor.model.RecomentationModel;
import com.mukul.finddoctor.model.StatusId;
import com.mukul.finddoctor.model.StatusMessage;
import com.mukul.finddoctor.model.StatusResponse;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.UserProfileResponse;

import java.util.List;

public class ApiListener {
    public interface doctorSearchListener {
        void onSearchSuccess(List<DoctorModel> list);
        void onSuccessFailed(String msg);
    }
    public interface appointmentSearchListener {
        void onAppointmentSearchSuccess(List<AppointmentModel2> list);
        void onAppointmentSearchFailed(String msg);
    }
    public interface chamberListDownloadListener {
        void onChamberListDownloadSuccess(List<Chamber> list);
        void onChamberListDownloadFailed(String msg);
    }
    public interface basicInfoDownloadListener {
        void onBasicInfoDownloadSuccess(BasicInfoModel data);

        void onBasicInfoDownloadFailed(String msg);
    }

    public interface drBasicInfoPostListener {
        void onBasicInfoPostSuccess(StatusId data);
        void onBasicInfoPostFailed(String msg);
    }
    public interface drSchedulePostListener {
        void ondrSchedulePostSuccess(StatusMessage data);
        void ondrSchedulePostFailed(String msg);
    }
    public interface CheckMobileListener {
        void onMobileCheckSuccess(StatusResponse status);

        void onMobileCheckFailed(String msg);
    }

    public interface LoginUserListener {
        void onUserLoginSuccess(LoginResponse status);

        void onUserLoginFailed(String msg);
    }

    public interface appoinetmentPOstListener {
        void onAppointmentPostSuccess(StatusResponse status);

        void onAppointmentPostFailed(String msg);
    }

    public interface appoinetmentsDownloadListener {
        void onAppointmentDownloadSuccess(AppointmentResponse status);

        void onAppointmentDownloadFailed(String msg);
    }

    public interface dataDownloadListener {
        void onDownloaded(List<AppointmentModel> status);
    }

    public interface patientAllDataDownloadListener {
        void onDownloaded(AppointmentResponse status);
    }
    public interface patientNotificationDataDownloadListener {
        void onNotificationDownloaded( List<RecomentationModel> status);
    }

    public interface appointmentStateChangeListener {
        void onAppointmentChangeSuccess(StatusResponse list);
        void onPppointmentChangeFailed(String msg);
    }
    public interface profileDownloadListener {
        void onprofileDownloadSuccess(UserProfileResponse list);
        void onprofileDownloadFailed(String msg);
    }

    public interface testNamesDownloadListener {
        void ontestNamesDownloadSuccess(List<TestModel> data);
        void ontestNamesDownloadFailed(String msg);
    }

    public interface recomendationTestPostListener {
        void onrecomendationTestPostSuccess(StatusResponse response);
        void onrecomendationTestPostFailed(String msg);
    }
}
