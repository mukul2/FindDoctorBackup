package com.mukul.finddoctor.api;


import com.mukul.finddoctor.model.AppointmentAddResponse;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentModelNew;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.BasicByDrResponse;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.BlogModel;
import com.mukul.finddoctor.model.CallHistoryPatient;
import com.mukul.finddoctor.model.DepartmentModel;
import com.mukul.finddoctor.model.DeptModel;
import com.mukul.finddoctor.model.DiseasesModel;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.DrChamberResponse;
import com.mukul.finddoctor.model.DrEduChInfoModel;
import com.mukul.finddoctor.model.DrServiceModel;
import com.mukul.finddoctor.model.EducationSkillModel;
import com.mukul.finddoctor.model.LoginResponse;
import com.mukul.finddoctor.model.MedicineModel;
import com.mukul.finddoctor.model.OnlineDoctorModel;
import com.mukul.finddoctor.model.PrescriptionModel;
import com.mukul.finddoctor.model.PrescriptionRequestModel;
import com.mukul.finddoctor.model.RecomentationModel;
import com.mukul.finddoctor.model.SearchDoctorModel;
import com.mukul.finddoctor.model.StatusId;
import com.mukul.finddoctor.model.StatusMessage;
import com.mukul.finddoctor.model.StatusResponse;
import com.mukul.finddoctor.model.TestList;
import com.mukul.finddoctor.model.TestRecomendationModel;
import com.mukul.finddoctor.model.TreatmentHistoryModel;
import com.mukul.finddoctor.model.UserProfileResponse;
import com.mukul.finddoctor.model.VideoCallModel;
import com.sinch.gson.JsonElement;

import java.util.List;

public class ApiListener {
    public interface doctorSearchListener {
        void onSearchSuccess(List<DoctorModel> list);
        void onSuccessFailed(String msg);
    }
    public interface PatientSignUPListener {
        void onPatientSignUPSuccess(StatusMessage list);
        void onPatientSignUPSuccessFailed(String msg);
    }
    public interface BlogDownloadListener {
        void onBlogDownloaSuccess(List<BlogModel> list);
        void onBlogDownloaSuccessFailed(String msg);
    }
    public interface DiseasesDownloadListener {
        void onDiseasesDownloadSuccess(List<DiseasesModel> list);
        void onDiseasesDownloadSuccessFailed(String msg);
    }
    public interface DocSearchListener {
        void onDocSearchSuccess(List<SearchDoctorModel> list);
        void onDocSearchFailed(String msg);
    }

    public interface doctorEduSkillDownloadListener {
        void ondoctorEduSkillDownloadSuccess(EducationSkillModel list);
        void ondoctorEduSkillDownloadSuccessFailed(String msg);
    }
    public interface CommonappointmentDownloadListener {
        void onAppointmentDownloadSuccess(List<AppointmentModel> list);
        void onAppointmentDownloadFailed(String msg);
    }
    public interface DrRecomentationDownloadListener {
        void onRecomendationDownloadSuccess(List<RecomentationModel> list);
        void onRecomendationFailed(String msg);
    }
    public interface patientTreatmentHistoryListener {
        void onpatientTreatmentHistorySearchSuccess(List<TreatmentHistoryModel> list);
        void onpatientTreatmentHistorySuccessFailed(String msg);
    }
    public interface servePostListener {
        void onServePostSuccess(StatusMessage response);
        void onServePostFailed(String msg);
    }
    public interface drServicePostListener {
        void ondrServicePostSuccess(StatusMessage response);
        void ondrServicePostFailed(String msg);
    }
    public interface patientCallLogListener {
        void onPatientCallLogSuccess(List<CallHistoryPatient> list);
        void onPatientCallLogFailed(String msg);
    }
    public interface doctorCallLogListener {
        void onDoctorCallLogSuccess(List<CallHistoryPatient> list);
        void onDoctorCallLogFailed(String msg);
    }

    public interface doctorOnlineStatusChangeListener {
        void ondoctorOnlineStatusChangeSuccess(StatusMessage statusMessage);
        void ondoctorOnlineStatusChangeFailed(String msg);
    }
    public interface PushCallLogListener {
        void onPushCallLogSuccess(StatusMessage statusMessage);
        void onPushCallLogFailed(String msg);
    }

    public interface PresCriptionDownloadListenerPatient {
        void onPrescriptionDownloadSuccess(List<PrescriptionModel> data);
        void onPrescriptionDownloadFailed(String msg);
    }

    public interface onlineDoctorListener {
        void onOnlineDoctorSearchSuccess(List<VideoCallModel> list);

        void onOnlineDoctorSearchFailed(String msg);
    }

    public interface TestDownloadListener {
        void onTestDownloadSuccess(List<TestList> list);
        void onTestDownloadFailed(String msg);
    }
    public interface OnlineDoctorsDownloadListener {
        void onOnlineDoctorsDownloadSuccess(List<OnlineDoctorModel> list);
        void onOnlineDoctorsDownloadFailed(String msg);
    }
    public interface DrServiceDownloadListener {
        void onDrServiceDownloadSuccess(List<DrServiceModel> list);
        void onDrServiceDownloadFailed(String msg);
    }

    public interface appointmentSearchListener {
        void onAppointmentSearchSuccess(List<AppointmentModel2> list);
        void onAppointmentSearchFailed(String msg);
    }
    public interface DeptDownloadListener {
        void onDepartmentDownloadSuccess(List<DeptModel> list);
        void onDepartmentDownloadFailed(String msg);
    }

    public interface chamberListDownloadListener {
        void onChamberListDownloadSuccess(List<DrChamberResponse> list);
        void onChamberListDownloadFailed(String msg);
    }
    public interface drChamberEduSkillDownloadListener {
        void onChamberEduSkillDownloadSuccess(DrEduChInfoModel list);
        void onChamberEduSkillDownloadFailed(String msg);
    }
    public interface departmentsDownloadListener {
        void onDepartmentsListDownloadSuccess(List<DepartmentModel> list);
        void onDepartmentsListDownloadFailed(String msg);
    }

    public interface basicInfoDownloadListener {
        void onBasicInfoDownloadSuccess(BasicInfoModel data);
        void onBasicInfoDownloadFailed(String msg);
    }
    public interface AppointmentPOstListener {
        void onAppointmentPOStSuccess(AppointmentAddResponse data);
        void onAppointmentPOStFailed(String msg);
    }
    public interface TestRecomDownloadListener {
        void onTestRecomDownloadSuccess(List<TestRecomendationModel> data);
        void onTestRecomDownloadFailed(String msg);
    }
    public interface MyPrescriptionRequestDownloadListener {
        void onMyPrescriptionRequestDownloadSuccess(List<PrescriptionRequestModel> data);
        void onMyPrescriptionRequestDownloadFailed(String msg);
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
    public interface PostEducationInfoListener {
        void onPostEducationInfoSuccess(StatusMessage status);
        void onPostEducationInfoFailed(String msg);
    }
    public interface DownloadMedicinesListInfoListener {
        void onDownloadMedicinesListInfoSuccess(List<MedicineModel> status);
        void onDownloadMedicinesListFailed(String msg);
    }
    public interface PostSkillInfoListener {
        void onPostSkillInfoSuccess(StatusMessage status);
        void onPostSkillInfoFailed(String msg);
    }
    public interface appoinetmentPOstListener {
        void onAppointmentPostSuccess(StatusResponse status);

        void onAppointmentPostFailed(String msg);
    }

    public interface appoinetmentsDownloadListener {
        void onAppointmentDownloadSuccess(List<AppointmentModelNew> status);
        void onAppointmentDownloadFailed(String msg);
    }

    public interface dataDownloadListener {
        void onDownloaded(List<AppointmentModel> status);
    }

    public interface patientAllDataDownloadListener {
        void onDownloaded(AppointmentResponse status);
    }

    public interface patientNotificationDataDownloadListener {
        void onNotificationDownloaded(List<RecomentationModel> status);
    }

    public interface appointmentStateChangeListener {
        void onAppointmentChangeSuccess(StatusMessage list);
        void onPppointmentChangeFailed(String msg);
    }

    public interface profileDownloadListener {
        void onprofileDownloadSuccess(UserProfileResponse list);

        void onprofileDownloadFailed(String msg);
    }

    public interface drprofileUpdateListener {
        void ondrprofileUpdateSuccess(StatusResponse list);

        void ondrprofileUpdateFailed(String msg);
    }

    public interface testNamesDownloadListener {
        void ontestNamesDownloadSuccess(BasicByDrResponse data);

        void ontestNamesDownloadFailed(String msg);
    }

    public interface recomendationTestPostListener {
        void onrecomendationTestPostSuccess(StatusResponse response);

        void onrecomendationTestPostFailed(String msg);
    }
}
