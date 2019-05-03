package com.mukul.finddoctor.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfoModel {

@SerializedName("hospitalList")
@Expose
private List<String> hospitalList = null;
@SerializedName("spacialist")
@Expose
private List<String> spacialist = null;

public List<String> getHospitalList() {
return hospitalList;
}

public void setHospitalList(List<String> hospitalList) {
this.hospitalList = hospitalList;
}

public List<String> getSpacialist() {
return spacialist;
}

public void setSpacialist(List<String> spacialist) {
this.spacialist = spacialist;
}

}