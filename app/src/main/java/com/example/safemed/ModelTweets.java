package com.example.safemed;

public class ModelTweets {

    public String phone, _id, upi, comment, entity_name, date_added;


    public ModelTweets() {
    }


    public ModelTweets(String phone, String _id, String upi, String comment, String entity_name, String date_added) {
        this.phone = phone;
        this._id = _id;
        this.upi = upi;
        this.comment = comment;
        this.entity_name = entity_name;
        this.date_added = date_added;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}
