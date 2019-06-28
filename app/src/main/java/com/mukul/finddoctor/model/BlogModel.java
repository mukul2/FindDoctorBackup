package com.mukul.finddoctor.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlogModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("dr_id")
@Expose
private Integer drId;
@SerializedName("body")
@Expose
private String body;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("photo_info")
@Expose
private List<PhotoInfo> photoInfo = null;
@SerializedName("dr_info")
@Expose
private DrInfo drInfo;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getDrId() {
return drId;
}

public void setDrId(Integer drId) {
this.drId = drId;
}

public String getBody() {
return body;
}

public void setBody(String body) {
this.body = body;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public List<PhotoInfo> getPhotoInfo() {
return photoInfo;
}

public void setPhotoInfo(List<PhotoInfo> photoInfo) {
this.photoInfo = photoInfo;
}

public DrInfo getDrInfo() {
return drInfo;
}

public void setDrInfo(DrInfo drInfo) {
this.drInfo = drInfo;
}

}