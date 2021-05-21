package com.example.safemed;

public class ModelMember {

    public String title, value, medicinetype;


    public ModelMember() {
    }

    public ModelMember(String title, String value, String medicinetype) {
        this.title = title;
        this.value = value;
        this.medicinetype = medicinetype;
    }

    public String getMedicinetype() {
        return medicinetype;
    }

    public void setMedicinetype(String medicinetype) {
        this.medicinetype = medicinetype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
