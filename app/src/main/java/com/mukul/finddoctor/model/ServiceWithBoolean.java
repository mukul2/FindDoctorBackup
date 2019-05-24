package com.mukul.finddoctor.model;

/**
 * Created by mukul on 5/25/2019.
 */

public class ServiceWithBoolean {
    boolean isSelected;
    ServiceName serviceName;

    public ServiceWithBoolean() {
    }

    public ServiceWithBoolean(boolean isSelected, ServiceName serviceName) {
        this.isSelected = isSelected;
        this.serviceName = serviceName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }
}
