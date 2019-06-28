package com.mukul.finddoctor.api;

import android.support.annotation.NonNull;
import android.util.Log;


import com.mukul.finddoctor.model.AppointmentAddResponse;
import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentModelNew;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.BasicByDrResponse;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.BlogModel;
import com.mukul.finddoctor.model.CallHistoryPatient;
import com.mukul.finddoctor.model.Chamber;
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
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.TestRecomendationModel;
import com.mukul.finddoctor.model.TreatmentHistoryModel;
import com.mukul.finddoctor.model.UserProfileResponse;
import com.mukul.finddoctor.model.VideoCallModel;
import com.sinch.gson.JsonElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created By TAOHID on 9/6/18.
 */
public class Api {

    private static Api dataManager = null;

    public static Api getInstance() {

        if (dataManager == null) {
            dataManager = new Api();
        }

        return dataManager;
    }

    public void loginUser(String mobile, String password, final ApiListener.LoginUserListener loginUserListener) {

        ApiClient.getApiInterface().login(mobile, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response != null) {
                    loginUserListener.onUserLoginSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                loginUserListener.onUserLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postEducationInfo(String KEY, String dr_id, String title, String body, final ApiListener.PostEducationInfoListener listener) {

        ApiClient.getApiInterface().postEducationInfo(KEY, dr_id, title, body).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPostEducationInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPostEducationInfoFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMedicinesList(String KEY, final ApiListener.DownloadMedicinesListInfoListener listener) {

        ApiClient.getApiInterface().getMedicine(KEY).enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineModel>> call, @NonNull Response<List<MedicineModel>> response) {
                if (response != null) {
                    listener.onDownloadMedicinesListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineModel>> call, @NonNull Throwable t) {
                listener.onDownloadMedicinesListFailed(t.getLocalizedMessage());
            }
        });
    }
    public void blogsDownload(String KEY, final ApiListener.BlogDownloadListener listener) {

        ApiClient.getApiInterface().getAllBlog(KEY).enqueue(new Callback<List<BlogModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<BlogModel>> call, @NonNull Response<List<BlogModel>> response) {
                if (response != null) {
                    listener.onBlogDownloaSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<BlogModel>> call, @NonNull Throwable t) {
                listener.onBlogDownloaSuccessFailed(t.getLocalizedMessage());
            }
        });
    }
    public void DiseasesDownload(String KEY, String user_ID, final ApiListener.DiseasesDownloadListener listener) {

        ApiClient.getApiInterface().getDiseasesRecord(KEY,user_ID).enqueue(new Callback<List<DiseasesModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DiseasesModel>> call, @NonNull Response<List<DiseasesModel>> response) {
                if (response != null) {
                    listener.onDiseasesDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DiseasesModel>> call, @NonNull Throwable t) {
                listener.onDiseasesDownloadSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDepList(String KEY, final ApiListener.DeptDownloadListener listener) {

        ApiClient.getApiInterface().getDepartments(KEY).enqueue(new Callback<List<DeptModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeptModel>> call, @NonNull Response<List<DeptModel>> response) {
                if (response != null) {
                    listener.onDepartmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DeptModel>> call, @NonNull Throwable t) {
                listener.onDepartmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchDoctors(String KEY, String name, String id, final ApiListener.DocSearchListener listener) {

        ApiClient.getApiInterface().searchDoctors(KEY, name, id).enqueue(new Callback<List<SearchDoctorModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SearchDoctorModel>> call, @NonNull Response<List<SearchDoctorModel>> response) {
                if (response != null) {
                    listener.onDocSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<SearchDoctorModel>> call, @NonNull Throwable t) {
                listener.onDocSearchFailed(t.getLocalizedMessage());
            }
        });
    }

    public void patientSignUp(RequestBody name, RequestBody department, RequestBody user_type, RequestBody password, RequestBody email, RequestBody phone, MultipartBody.Part photo, final ApiListener.PatientSignUPListener listener) {

        ApiClient.getApiInterface().signUpPatient(name, department, user_type, password, email, phone, photo).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPatientSignUPSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPatientSignUPSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postSkillInfo(String KEY, String dr_id, String body, final ApiListener.PostSkillInfoListener listener) {

        ApiClient.getApiInterface().postSkillInfo(KEY, dr_id, body).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPostSkillInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPostSkillInfoFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodRecomendedLits(String id, final ApiListener.TestDownloadListener listener) {

        ApiClient.getApiInterface().getTestList(id).enqueue(new Callback<List<TestList>>() {
            @Override
            public void onResponse(@NonNull Call<List<TestList>> call, @NonNull Response<List<TestList>> response) {
                if (response != null) {
                    listener.onTestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TestList>> call, @NonNull Throwable t) {
                listener.onTestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void doctorEduSkillDownload(String id, final ApiListener.doctorEduSkillDownloadListener listener) {

        ApiClient.getApiInterface().getMyEducationSkill(id).enqueue(new Callback<EducationSkillModel>() {
            @Override
            public void onResponse(@NonNull Call<EducationSkillModel> call, @NonNull Response<EducationSkillModel> response) {
                if (response != null) {
                    listener.ondoctorEduSkillDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<EducationSkillModel> call, @NonNull Throwable t) {
                listener.ondoctorEduSkillDownloadSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodOnlineDoctorsLits(final ApiListener.OnlineDoctorsDownloadListener listener) {

        ApiClient.getApiInterface().getOnlineServiceDoctors().enqueue(new Callback<List<OnlineDoctorModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<OnlineDoctorModel>> call, @NonNull Response<List<OnlineDoctorModel>> response) {
                if (response != null) {
                    listener.onOnlineDoctorsDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<OnlineDoctorModel>> call, @NonNull Throwable t) {
                listener.onOnlineDoctorsDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodDrPending(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().dr_pending(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodDepartmentsList(final ApiListener.departmentsDownloadListener listener) {

        ApiClient.getApiInterface().getAllDepartments().enqueue(new Callback<List<DepartmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DepartmentModel>> call, @NonNull Response<List<DepartmentModel>> response) {
                if (response != null) {
                    listener.onDepartmentsListDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DepartmentModel>> call, @NonNull Throwable t) {
                listener.onDepartmentsListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodDrConfirmed(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().dr_confirmed(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodPaConfirmed(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().getPatientAllConfirmed(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodPaPending(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().getPatientAllPending(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodPaRecomendation(String id, final ApiListener.DrRecomentationDownloadListener listener) {

        ApiClient.getApiInterface().getpatientRecomentation(id).enqueue(new Callback<List<RecomentationModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<RecomentationModel>> call, @NonNull Response<List<RecomentationModel>> response) {
                if (response != null) {
                    listener.onRecomendationDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<RecomentationModel>> call, @NonNull Throwable t) {
                listener.onRecomendationFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadCallLog(String patient_id, final ApiListener.patientCallLogListener listener) {

        ApiClient.getApiInterface().getCallListBypatient(patient_id).enqueue(new Callback<List<CallHistoryPatient>>() {
            @Override
            public void onResponse(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Response<List<CallHistoryPatient>> response) {
                if (response != null) {
                    listener.onPatientCallLogSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Throwable t) {
                listener.onPatientCallLogFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadCallLogDoctor(String doctor_id, final ApiListener.doctorCallLogListener listener) {

        ApiClient.getApiInterface().getCallListByDoctor(doctor_id).enqueue(new Callback<List<CallHistoryPatient>>() {
            @Override
            public void onResponse(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Response<List<CallHistoryPatient>> response) {
                if (response != null) {
                    listener.onDoctorCallLogSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Throwable t) {
                listener.onDoctorCallLogFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadOnlineDoctors(final ApiListener.onlineDoctorListener listener) {

        ApiClient.getApiInterface().getOnlineDoctors().enqueue(new Callback<List<VideoCallModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<VideoCallModel>> call, @NonNull Response<List<VideoCallModel>> response) {
                if (response != null) {
                    listener.onOnlineDoctorSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<VideoCallModel>> call, @NonNull Throwable t) {
                listener.onOnlineDoctorSearchFailed(t.getLocalizedMessage());
            }
        });
    }

    public void changeDrOnlineStatus(String drID, String isOnline, final ApiListener.doctorOnlineStatusChangeListener listener) {

        ApiClient.getApiInterface().changeOnlineStatus(drID, isOnline).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.ondoctorOnlineStatusChangeSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.ondoctorOnlineStatusChangeFailed(t.getLocalizedMessage());
            }
        });
    }

    public void CallLogPostStatus(String patient_id, String dr_id, String call_time, String duration, final ApiListener.PushCallLogListener listener) {

        ApiClient.getApiInterface().pushCallResponse(patient_id, dr_id, call_time, duration).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPushCallLogSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPushCallLogFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchDoctor(String dr_name, String hospital_name, String specialist, String city, String day, final ApiListener.doctorSearchListener doctorSearchListener) {
        ApiClient.getApiInterface().searchChamber(dr_name, hospital_name, specialist, city, day).enqueue(new Callback<List<DoctorModel>>() {
            @Override
            public void onResponse(Call<List<DoctorModel>> call, Response<List<DoctorModel>> response) {
                doctorSearchListener.onSearchSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<DoctorModel>> call, Throwable t) {
                doctorSearchListener.onSuccessFailed(t.getLocalizedMessage());

            }
        });

    }

    public void entryGeneralInfoDoctor(String dr_name, String email, String mobile, String password, String type, String last_degree, String currentHospital, final ApiListener.drBasicInfoPostListener listener) {
        ApiClient.getApiInterface().drGeneralEntry(dr_name, email, mobile, password, type, last_degree, currentHospital).enqueue(new Callback<StatusId>() {
            @Override
            public void onResponse(Call<StatusId> call, Response<StatusId> response) {
                listener.onBasicInfoPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusId> call, Throwable t) {
                listener.onBasicInfoPostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void setDrSchedule(String token, String id, String chamberName, String address, String visit_fee, String followUpfees, String days, final ApiListener.drSchedulePostListener listener) {
        ApiClient.getApiInterface().setDrSchedule(token, id, chamberName, address, visit_fee, followUpfees, days).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.ondrSchedulePostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.ondrSchedulePostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void downloadBasicInfo(final ApiListener.basicInfoDownloadListener listener) {
        ApiClient.getApiInterface().getBasicInfo().enqueue(new Callback<BasicInfoModel>() {
            @Override
            public void onResponse(Call<BasicInfoModel> call, Response<BasicInfoModel> response) {
                listener.onBasicInfoDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<BasicInfoModel> call, Throwable t) {
                listener.onBasicInfoDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void addAppointmentInfo(String token, String p_id, String dr_id, String problems, String phone, String name, String chamber_id, String date, String status, final ApiListener.AppointmentPOstListener listener) {
        ApiClient.getApiInterface().addAppointmentInfo(token, p_id, dr_id, problems, phone, name, chamber_id, date, status).enqueue(new Callback<AppointmentAddResponse>() {
            @Override
            public void onResponse(Call<AppointmentAddResponse> call, Response<AppointmentAddResponse> response) {
                listener.onAppointmentPOStSuccess(response.body());

            }

            @Override
            public void onFailure(Call<AppointmentAddResponse> call, Throwable t) {
                listener.onAppointmentPOStFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getMyChambersList(String id, final ApiListener.chamberListDownloadListener listener) {
        ApiClient.getApiInterface().getMyChambers(id).enqueue(new Callback<List<DrChamberResponse>>() {
            @Override
            public void onResponse(Call<List<DrChamberResponse>> call, Response<List<DrChamberResponse>> response) {
                listener.onChamberListDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<DrChamberResponse>> call, Throwable t) {
                listener.onChamberListDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getEduSKillChamber(String key, String id, final ApiListener.drChamberEduSkillDownloadListener listener) {
        ApiClient.getApiInterface().getSkillChamberEdu(key, id).enqueue(new Callback<DrEduChInfoModel>() {
            @Override
            public void onResponse(Call<DrEduChInfoModel> call, Response<DrEduChInfoModel> response) {
                listener.onChamberEduSkillDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<DrEduChInfoModel> call, Throwable t) {
                listener.onChamberEduSkillDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void checkMobile(String mobile, final ApiListener.CheckMobileListener checkMobileListener) {

        ApiClient.getApiInterface().checkMobile(mobile).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    checkMobileListener.onMobileCheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                checkMobileListener.onMobileCheckFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postAppointment(String patient_id, String chamber_id, String dr_id, String appointment_for, String phone, String problems, String date, final ApiListener.appoinetmentPOstListener listener) {

        ApiClient.getApiInterface().postAppointment(patient_id, chamber_id, dr_id, appointment_for, phone, problems, date).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onAppointmentPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onAppointmentPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postRecommendationTest(String appointment_id, String test_id, final ApiListener.recomendationTestPostListener listener) {

        ApiClient.getApiInterface().postRecommenTest(test_id, appointment_id).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onrecomendationTestPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onrecomendationTestPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchAppointment(String id, String dr_id, String appointment_for, final ApiListener.appointmentSearchListener listener) {

        ApiClient.getApiInterface().searchAppointemntByDoctor(id, dr_id, appointment_for).enqueue(new Callback<List<AppointmentModel2>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel2>> call, @NonNull Response<List<AppointmentModel2>> response) {
                if (response != null) {
                    listener.onAppointmentSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel2>> call, @NonNull Throwable t) {
                listener.onAppointmentSearchFailed(t.getLocalizedMessage());
            }
        });
    }


    public void getAppointmentsByDoctor(String dr_id, final ApiListener.appoinetmentsDownloadListener listener) {


    }

    public void getAppointments(String token, String type, String userID, String status, final ApiListener.appoinetmentsDownloadListener listener) {

        ApiClient.getApiInterface().getAppointmentsList(token, type, userID, status).enqueue(new Callback<List<AppointmentModelNew>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModelNew>> call, @NonNull Response<List<AppointmentModelNew>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModelNew>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }
    public void getMyTestRecomendations(String token,  String userID,  final ApiListener.TestRecomDownloadListener listener) {

        ApiClient.getApiInterface().getMyTestRecomendation(token,  userID).enqueue(new Callback<List<TestRecomendationModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TestRecomendationModel>> call, @NonNull Response<List<TestRecomendationModel>> response) {
                if (response != null) {
                    listener.onTestRecomDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TestRecomendationModel>> call, @NonNull Throwable t) {
                listener.onTestRecomDownloadFailed(t.getLocalizedMessage());
            }
        });
    }
    public void getMyPrescriptionRequest(String token,  String userID, String userType, final ApiListener.MyPrescriptionRequestDownloadListener listener) {

        ApiClient.getApiInterface().getmyPrescriptionRequest(token,  userID,userType).enqueue(new Callback<List<PrescriptionRequestModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionRequestModel>> call, @NonNull Response<List<PrescriptionRequestModel>> response) {
                if (response != null) {
                    listener.onMyPrescriptionRequestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionRequestModel>> call, @NonNull Throwable t) {
                listener.onMyPrescriptionRequestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getPresCriptionsByPatient(String token, String pa_id, String userType, final ApiListener.PresCriptionDownloadListenerPatient listener) {

        ApiClient.getApiInterface().getMyPrescriptionsPatient(token, pa_id, userType).enqueue(new Callback<List<PrescriptionModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionModel>> call, @NonNull Response<List<PrescriptionModel>> response) {
                if (response != null) {
                    listener.onPrescriptionDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionModel>> call, @NonNull Throwable t) {
                listener.onPrescriptionDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAppointmentsBypatient(String id, final ApiListener.appoinetmentsDownloadListener listener) {


    }

    public void changeStatus(String token, String id, String status, final ApiListener.appointmentStateChangeListener listener) {

        ApiClient.getApiInterface().changeAppointmentStatus(token, id, status).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onAppointmentChangeSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPppointmentChangeFailed(t.getLocalizedMessage());
            }
        });
    }

    public void patientTreatmentHistory(String patientid, final ApiListener.patientTreatmentHistoryListener listener) {

        ApiClient.getApiInterface().treatmentHistoryByPatient(patientid).enqueue(new Callback<List<TreatmentHistoryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TreatmentHistoryModel>> call, @NonNull Response<List<TreatmentHistoryModel>> response) {
                if (response != null) {
                    listener.onpatientTreatmentHistorySearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TreatmentHistoryModel>> call, @NonNull Throwable t) {
                listener.onpatientTreatmentHistorySuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void servePost(String appointment_id,
                          String dr_id,
                          String p_id,
                          String dr_name,
                          String p_name,
                          String comment,
                          String fees,
                          String chamber_id,
                          final ApiListener.servePostListener listener) {

        ApiClient.getApiInterface().postServeInfo(appointment_id, dr_id, p_id, dr_name, p_name, comment, fees, chamber_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onServePostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onServePostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getThisPfofile(String id, final ApiListener.profileDownloadListener listener) {

        ApiClient.getApiInterface().getThisProfile(id).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserProfileResponse> call, @NonNull Response<UserProfileResponse> response) {
                if (response != null) {
                    listener.onprofileDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<UserProfileResponse> call, @NonNull Throwable t) {
                listener.onprofileDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadDrServiceList(String id, final ApiListener.DrServiceDownloadListener listener) {

        ApiClient.getApiInterface().get_my_services_by_dr(id).enqueue(new Callback<List<DrServiceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DrServiceModel>> call, @NonNull Response<List<DrServiceModel>> response) {
                if (response != null) {
                    listener.onDrServiceDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DrServiceModel>> call, @NonNull Throwable t) {
                listener.onDrServiceDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postDrServices(String dr_id, String service_detail, final ApiListener.drServicePostListener listener) {

        ApiClient.getApiInterface().post_dr_service_list(dr_id, service_detail).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.ondrServicePostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.ondrServicePostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void updateDrInfo(String id, String hospital, String lastDegree, String name, final ApiListener.drprofileUpdateListener listener) {

        ApiClient.getApiInterface().updateDrBasicInfo(id, hospital, lastDegree, name).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.ondrprofileUpdateSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.ondrprofileUpdateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadTestNames(final ApiListener.testNamesDownloadListener listener) {
        ApiClient.getApiInterface().getTestNames().enqueue(new Callback<BasicByDrResponse>() {
            @Override
            public void onResponse(Call<BasicByDrResponse> call, Response<BasicByDrResponse> response) {
                listener.ontestNamesDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<BasicByDrResponse> call, Throwable t) {
                listener.ontestNamesDownloadFailed(t.getLocalizedMessage());

            }
        });

    }
}
