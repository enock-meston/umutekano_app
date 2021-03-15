package com.nigoote.umutekano;

public class CitizenPayment{
    private String cId;
    private String date;
    private String month;
    private String fine;
    private String idcard;

    public CitizenPayment(String cId, String date, String month, String fine, String idcard) {
        this.cId = cId;
        this.date = date;
        this.month = month;
        this.fine = fine;
        this.idcard = idcard;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getidcard() {
        return idcard;
    }

    public void setidcard(String idcard) {
        this.idcard = idcard;
    }
}
