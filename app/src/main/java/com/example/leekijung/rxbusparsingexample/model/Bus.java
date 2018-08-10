package com.example.leekijung.rxbusparsingexample.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {

    @SerializedName("RESULT")
    @Expose
    private RESULT rESULT;
    @SerializedName("BUSSTOP_LIST")
    @Expose
    private List<BUSSTOPLIST> bUSSTOPLIST = null;
    @SerializedName("ROW_COUNT")
    @Expose
    private Integer rOWCOUNT;

    public RESULT getRESULT() {
        return rESULT;
    }

    public void setRESULT(RESULT rESULT) {
        this.rESULT = rESULT;
    }

    public List<BUSSTOPLIST> getBUSSTOPLIST() {
        return bUSSTOPLIST;
    }

    public void setBUSSTOPLIST(List<BUSSTOPLIST> bUSSTOPLIST) {
        this.bUSSTOPLIST = bUSSTOPLIST;
    }

    public Integer getROWCOUNT() {
        return rOWCOUNT;
    }

    public void setROWCOUNT(Integer rOWCOUNT) {
        this.rOWCOUNT = rOWCOUNT;
    }

}