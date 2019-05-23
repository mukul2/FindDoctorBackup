package com.mukul.finddoctor.api;

import android.support.annotation.NonNull;
import android.util.Log;


import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.CallHistoryPatient;
import com.mukul.finddoctor.model.Chamber;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.LoginResponse;
import com.mukul.finddoctor.model.RecomentationModel;
import com.mukul.finddoctor.model.StatusId;
import com.mukul.finddoctor.model.StatusMessage;
import com.mukul.finddoctor.model.StatusResponse;
import com.mukul.finddoctor.model.TestList;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.TreatmentHistoryModel;
import com.mukul.finddoctor.model.UserProfileResponse;
import com.mukul.finddoctor.model.VideoCallModel;

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

    public void setDrSchedule(String id, String days, String address, String visit_fee, String city, String specialist, String degree, String hospital, final ApiListener.drSchedulePostListener listener) {
        ApiClient.getApiInterface().setDrSchedule(id, days, address, visit_fee, city, specialist, degree, hospital).enqueue(new Callback<StatusMessage>() {
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

    public void getMyChambersList(String id, final ApiListener.chamberListDownloadListener listener) {
        ApiClient.getApiInterface().getMyChambers(id).enqueue(new Callback<List<Chamber>>() {
            @Override
            public void onResponse(Call<List<Chamber>> call, Response<List<Chamber>> response) {
                listener.onChamberListDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Chamber>> call, Throwable t) {
                listener.onChamberListDownloadFailed(t.getLocalizedMessage());

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

        ApiClient.getApiInterface().myAppointmentsbyDoctor(dr_id).enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentResponse> call, @NonNull Response<AppointmentResponse> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AppointmentResponse> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAppointmentsBypatient(String id, final ApiListener.appoinetmentsDownloadListener listener) {

        ApiClient.getApiInterface().myAppointmentsbyPatient(id).enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentResponse> call, @NonNull Response<AppointmentResponse> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AppointmentResponse> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void changeStatus(String id, String status, final ApiListener.appointmentStateChangeListener listener) {

        ApiClient.getApiInterface().changeAppointmentStatus(id, status).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onAppointmentChangeSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
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
        ApiClient.getApiInterface().getTestNames().enqueue(new Callback<List<TestModel>>() {
            @Override
            public void onResponse(Call<List<TestModel>> call, Response<List<TestModel>> response) {
                listener.ontestNamesDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<TestModel>> call, Throwable t) {
                listener.ontestNamesDownloadFailed(t.getLocalizedMessage());

            }
        });

    }
}
