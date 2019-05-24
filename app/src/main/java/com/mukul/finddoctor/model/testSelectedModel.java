package com.mukul.finddoctor.model;

/**
 * Created by mukul on 3/22/2019.
 */

public class testSelectedModel {
    boolean isSelected;
    TestName testModel;

    public testSelectedModel() {
    }

    public testSelectedModel(boolean isSelected, TestName testModel) {
        this.isSelected = isSelected;
        this.testModel = testModel;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public TestName getTestModel() {
        return testModel;
    }

    public void setTestModel(TestName testModel) {
        this.testModel = testModel;
    }
}
