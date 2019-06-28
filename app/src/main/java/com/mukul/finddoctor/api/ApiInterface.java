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

    @GET("all-blog-info")
    Call<List<BlogModel>> getAllBlog(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("patient-disease-record")
    Call<List<DiseasesModel>> getDiseasesRecord(@Header("Authorization") String token, @Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("add-disease-record")
    Call<List<DiseasesModel>> addDiseases(@Header("Authorization") String token,
                                          @Field("patient_id") String patient_id,
                                          @Field("disease_name") String disease_name,
                                          @Field("first_notice_date") String first_notice_date,
                                          @Field("status") String status);

    @FormUrlEncoded
    @POST("delete_service_by_dr.php")
    Call<StatusMessage> delete_service_by_dr(@Field("id") String id);

    @FormUrlEncoded
    @POST("get_dr_services.php")
    Call<List<DrServiceModel>> get_my_services_by_dr(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("post_dr_service_list.php")
    Call<StatusMessage> post_dr_service_list(@Field("dr_id") String dr_id, @Field("service_list_string") String service_list_string);

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

    @GET("get_all_departments.php")
    Call<List<DepartmentModel>> getAllDepartments();

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

    @GET("get_online_doctors_new.php")
    Call<List<OnlineDoctorModel>> getOnlineServiceDoctors();

    @GET("getAllTestNames.php")
    Call<BasicByDrResponse> getTestNames();


    @FormUrlEncoded
    @POST("getMyChambers.php")
    Call<List<DrChamberResponse>> getMyChambers(@Field("id") String id);

    @FormUrlEncoded
    @POST("doctor-education-chamber-info")
    Call<DrEduChInfoModel> getSkillChamberEdu(@Header("Authorization") String token,
                                              @Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("add-appointment-info")
    Call<AppointmentAddResponse> addAppointmentInfo(@Header("Authorization") String token,
                                                    @Field("patient_id") String patient_id,
                                                    @Field("dr_id") String dr_id,
                                                    @Field("problems") String problems,
                                                    @Field("phone") String phone,
                                                    @Field("name") String name,
                                                    @Field("chamber_id") String chamber_id,
                                                    @Field("date") String date,
                                                    @Field("status") String status);

    @FormUrlEncoded
    @POST("get-appointment-list")
    Call<List<AppointmentModelNew>> getAppointmentsList(@Header("Authorization") String token,
                                                        @Field("user_type") String user_type,
                                                        @Field("id") String id,
                                                        @Field("status") String status);

    @FormUrlEncoded
    @POST("test-recommendation-list")
    Call<List<TestRecomendationModel>> getMyTestRecomendation(@Header("Authorization") String token,
                                                              @Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("view-prescription-request")
    Call<List<PrescriptionRequestModel>> getmyPrescriptionRequest(@Header("Authorization") String token,
                                                                  @Field("id") String id,
                                                                  @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("get_dr_personal_info.php")
    Call<EducationSkillModel> getMyEducationSkill(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("get-prescription-info")
    Call<List<PrescriptionModel>> getMyPrescriptionsPatient(@Header("Authorization") String token,
                                                            @Field("id") String id,
                                                            @Field("user_type") String user_type);

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
    @POST("chamber-add")
    Call<StatusMessage> setDrSchedule(@Header("Authorization") String token,
                                      @Field("dr_id") String dr_id,
                                      @Field("chamber_name") String chamber_name,
                                      @Field("address") String address,
                                      @Field("fee") String fee,
                                      @Field("follow_up_fee") String follow_up_fee,
                                      @Field("days") String days);

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<StatusMessage> getDrInfo(@Header("Authorization") String token,
                                  @Field("dr_id") String dr_id);


    @GET("department-list")
    Call<List<DepartmentModel>> getDepartmentsList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("change-appointment-status")
    Call<StatusMessage> changeAppointmentStatus(@Header("Authorization") String token,
                                                @Field("appointment_id") String appointment_id,
                                                @Field("status") String status);

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
    @POST("login")
    Call<LoginResponse> login(@Field("email") String mobile,
                              @Field("password") String password);


    @FormUrlEncoded
    @POST("add-education-info")
    Call<StatusMessage> postEducationInfo(@Header("Authorization") String token,
                                          @Field("dr_id") String dr_id,
                                          @Field("title") String title,
                                          @Field("body") String body);

    @FormUrlEncoded
    @POST("add-skill-info")
    Call<StatusMessage> postSkillInfo(@Header("Authorization") String token,
                                      @Field("dr_id") String dr_id,
                                      @Field("body") String body);


    @GET("all-medicine-list")
    Call<List<MedicineModel>> getMedicine(@Header("Authorization") String token);

    @POST("department-list")
    Call<List<DeptModel>> getDepartments(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("doctor-search")
    Call<List<SearchDoctorModel>> searchDoctors(@Header("Authorization") String token,
                                                @Field("doctor_name") String doctor_name,
                                                @Field("department_id") String department_id);


    @Multipart
    @POST("sign-up")
    Call<StatusMessage> signUpPatient(@Part("name") RequestBody name,
                                      @Part("department") RequestBody department,
                                      @Part("user_type") RequestBody user_type,
                                      @Part("password") RequestBody password,
                                      @Part("email") RequestBody email,
                                      @Part("phone") RequestBody phone,
                                      @Part MultipartBody.Part image);
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