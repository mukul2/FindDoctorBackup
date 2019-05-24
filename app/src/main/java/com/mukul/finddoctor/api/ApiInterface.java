package com.mukul.finddoctor.api;


import com.mukul.finddoctor.model.AppointmentModel;
import com.mukul.finddoctor.model.AppointmentModel2;
import com.mukul.finddoctor.model.AppointmentResponse;
import com.mukul.finddoctor.model.BasicByDrResponse;
import com.mukul.finddoctor.model.BasicInfoModel;
import com.mukul.finddoctor.model.CallHistoryPatient;
import com.mukul.finddoctor.model.Chamber;
import com.mukul.finddoctor.model.DoctorModel;
import com.mukul.finddoctor.model.DrServiceModel;
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

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("searchDoctor.php")
    Call<List<DoctorModel>> searchChamber(@Field("dr_name") String dr_name,
                                          @Field("hospital_name") String hospital_name,
                                          @Field("specialist") String specialist,
                                          @Field("city") String city,
                                          @Field("day") String day);

    @FormUrlEncoded
    @POST("patient_all_confirmed.php")
    Call<List<AppointmentModel>> getPatientAllConfirmed(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("delete_service_by_dr.php")
    Call<StatusMessage> delete_service_by_dr(@Field("id") String id);

    @FormUrlEncoded
    @POST("get_dr_services.php")
    Call<List<DrServiceModel>> get_my_services_by_dr(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("post_dr_service_list.php")
    Call<StatusMessage> post_dr_service_list(@Field("dr_id") String dr_id,@Field("service_list_string") String service_list_string);

    @FormUrlEncoded
    @POST("patient_all_pending.php")
    Call<List<AppointmentModel>> getPatientAllPending(@Field("patient_id") String patient_id);
    @FormUrlEncoded
    @POST("patient_recomendation_list.php")
    Call<List<RecomentationModel>> getpatientRecomentation(@Field("patient_id") String patient_id);
    @FormUrlEncoded
    @POST("post_serve.php")
    Call<StatusMessage> postServeInfo(@Field("appointment_id") String appointment_id,
                                          @Field("dr_id") String dr_id,
                                          @Field("p_id") String p_id,
                                          @Field("dr_name") String dr_name,
                                          @Field("p_name") String p_name,
                                          @Field("comment") String comment,
                                          @Field("fees") String fees,
                                          @Field("chamber_id") String chamber_id);

    @GET("getOnlineDoctors.php")
    Call<List<VideoCallModel>> getOnlineDoctors();

    @FormUrlEncoded
    @POST("updateProfileDr.php")
    Call<StatusResponse> updateDrBasicInfo(@Field("id") String id,
                                           @Field("hospital_name") String hospital_name,
                                           @Field("last_education_degree") String last_education_degree,
                                           @Field("dr_name") String dr_name);
    @FormUrlEncoded
    @POST("getPatientTreatmentHistory.php")
    Call<List<TreatmentHistoryModel>> treatmentHistoryByPatient(@Field("patient_id") String patient_id);


    @FormUrlEncoded
    @POST("changeOnlineStatus.php")
    Call<StatusMessage> changeOnlineStatus(@Field("id") String id,
                                           @Field("isOnLine") String isOnLine);

    @FormUrlEncoded
    @POST("push_call_history.php")
    Call<StatusMessage> pushCallResponse(@Field("patient_id") String patient_id,
                                         @Field("dr_id") String dr_id,
                                         @Field("call_time") String call_time,
                                         @Field("duration") String duration);

    @FormUrlEncoded
    @POST("searchMyAppointmentDoctor.php")
    Call<List<AppointmentModel2>> searchAppointemntByDoctor(@Field("id") String id,
                                                            @Field("dr_id") String dr_id,
                                                            @Field("appointment_for") String appointment_for);

    @FormUrlEncoded
    @POST("getMyCallLog.php")
    Call<List<CallHistoryPatient>> getCallListBypatient(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("getMyCallLogDoctor.php")
    Call<List<CallHistoryPatient>> getCallListByDoctor(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("getRecomendationList.php")
    Call<List<TestList>> getTestList(@Field("appointment_id") String appointment_id);

    @FormUrlEncoded
    @POST("getThisProfile.php")
    Call<UserProfileResponse> getThisProfile(@Field("id") String id);

    @FormUrlEncoded
    @POST("getMyAppointmentsDoctor.php")
    Call<AppointmentResponse> myAppointmentsbyDoctor(@Field("dr_id") String dr_id);
    @FormUrlEncoded
    @POST("dr_all_pending.php")
    Call<List<AppointmentModel>> dr_pending(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("dr_all_confirmed.php")
    Call<List<AppointmentModel>> dr_confirmed(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("getMyAppointments.php")
    Call<AppointmentResponse> myAppointmentsbyPatient(@Field("patient_id") String patient_id);


    @FormUrlEncoded
    @POST("postRecommenTest.php")
    Call<StatusResponse> postRecommenTest(@Field("test_id") String test_id, @Field("appointment_id") String appointment_id);

    @GET("getBasicInfo.php")
    Call<BasicInfoModel> getBasicInfo();

    @GET("getAllTestNames.php")
    Call<BasicByDrResponse> getTestNames();


    @FormUrlEncoded
    @POST("getMyChambers.php")
    Call<List<Chamber>> getMyChambers(@Field("id") String id);

    @FormUrlEncoded
    @POST("GeneralEntry.php")
    Call<StatusId> drGeneralEntry(@Field("dr_name") String dr_name,
                                  @Field("email") String email,
                                  @Field("mobile") String mobile,
                                  @Field("password") String password,
                                  @Field("type") String type,
                                  @Field("last_education_degree") String last_education_degree,
                                  @Field("hospital_name") String hospital_name);

    @FormUrlEncoded
    @POST("addSchedule.php")
    Call<StatusMessage> setDrSchedule(@Field("id") String id,
                                      @Field("days") String days,
                                      @Field("address") String address,
                                      @Field("visit_fee") String visit_fee,
                                      @Field("city") String city,
                                      @Field("specialist") String specialist,
                                      @Field("last_education_degree") String last_education_degree,
                                      @Field("hospital_name") String hospital_name);

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<StatusMessage> updateDrInfo(@Field("id") String id,
                                     @Field("last_education_degree") String last_education_degree,
                                     @Field("hospital_name") String hospital_name);

    @FormUrlEncoded
    @POST("postAppointments.php")
    Call<StatusResponse> postAppointment(@Field("patient_id") String patient_id,
                                         @Field("chamber_id") String chamber_id,
                                         @Field("dr_id") String dr_id,
                                         @Field("appointment_for") String appointment_for,
                                         @Field("phone") String phon,
                                         @Field("problems") String problems,
                                         @Field("date") String date);

    @FormUrlEncoded
    @POST("checkMobile.php")
    Call<StatusResponse> checkMobile(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("mobile") String mobile,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("changeAppointmentStatus.php")
    Call<StatusResponse> changeAppointmentStatus(@Field("id") String id,
                                                 @Field("status") String status);
/*
    @POST("saveFile")
    Call<FileUploadResponse> sendFileToServer(@Header("Authorization") String auth, @Query("type") String type, @Query("directoryPath") String directoryPath, @Body RequestBody multipartFile);

    @Multipart
    @POST("saveFile")
    Call<FileUploadResponse> sendBigFileToServer(@Header("Authorization") String auth, @Query("type") String type, @Query("directoryPath") String directoryPath, @Part MultipartBody.Part multipartFile);

    @POST("saveFile")
    Call<FileUploadResponse> getFileFromServer(@Header("Authorization") String auth, @Query("type") String type);

    @POST("register")
    Call<RegistrationResponse> registration(@Body RegistrationDataModel registrationDataModel);

    @POST("login")
    Call<LoginResponse> login(@Body LoginDataModel loginDataModel);

    @POST("details")
    Call<ProfileResponse> getProfile(@Header("Authorization") String auth);

    @POST("editUser")
    Call<CommonResponse> editUser(@Header("Authorization") String auth, @Body EditUserModel editUserModel);

    @POST("contactList")
    Call<ContactPostResponse> postContactList(@Header("Authorization") String auth, @Body ContactPostModel contactPostModel);

    @POST("callLog")
    Call<CallLogPostResponse> postCallLog(@Header("Authorization") String auth, @Body CallLogPostModel callLogPostModel);

    @POST("smsHistory")
    Call<SmsHistoryPostResponse> postSmsHistory(@Header("Authorization") String auth, @Body SmsHistoryPostModel smsHistoryPostModel);

    @POST("logout")
    Call<CommonResponse> logout(@Header("Authorization") String auth);

    @POST("passwordChange")
    Call<CommonResponse> passwordChange(@Header("Authorization") String auth, @Body ChangePasswordModel changePasswordModel);

    @POST("folderList")
    Call<FolderListResponse> folderList(@Header("Authorization") String auth, @Query("folderCategory") String folderCategory);

    @POST("noteSend")
    Call<NoteResponse> noteSend(@Header("Authorization") String auth, @Body NoteModel noteModel);

    @POST("noteSoftDeleted")
    Call<NoteDeleteResponse> noteDelete(@Header("Authorization") String auth, @Query("id") String id);

    @POST("todoSend")
    Call<TodoResponse> todoSend(@Header("Authorization") String auth, @Body TodoModel todoModel);

    @POST("todoSoftDeleted")
    Call<TodoDeleteResponse> todoDelete(@Header("Authorization") String auth, @Query("id") String id);

    @POST("UserFolderList")
    Call<UserFileFolderListResponse> userFileFolderList(@Header("Authorization") String auth, @Query("type") String type);

    @Multipart
    @POST("deleteFile")
    Call<FileDeleteResponse> fileDelete(@Header("Authorization") String auth, @Part("type") RequestBody type, @Part("id") RequestBody id);
*/
}