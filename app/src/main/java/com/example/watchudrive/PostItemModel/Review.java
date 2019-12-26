package com.example.watchudrive.PostItemModel;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("licsence_plate")
    private  String licsence_plate;
    @SerializedName("violation_type")
    private  String violation_type;
    @SerializedName("reporter_id")
    private  String reporter_id;
    @SerializedName("date")
    private  String date;

    public Review(String licsence_plate, String violation_type, String reporter_id, String date) {
        this.licsence_plate = licsence_plate;
        this.violation_type = violation_type;
        this.reporter_id = reporter_id;
        this.date = date;
    }

    public String getLicsence_plate() {
        return licsence_plate;
    }

    public void setLicsence_plate(String licsence_plate) {
        this.licsence_plate = licsence_plate;
    }

    public String getViolation_type() {
        return violation_type;
    }

    public void setViolation_type(String violation_type) {
        this.violation_type = violation_type;
    }

    public String getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(String reporter_id) {
        this.reporter_id = reporter_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
