package com.example.leekijung.rxbusparsingexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BUSSTOPLIST {

    @SerializedName("ARRIVE")
    @Expose
    private String aRRIVE;
    @SerializedName("REMAIN_MIN")
    @Expose
    private Integer rEMAINMIN;
    @SerializedName("ENG_BUSSTOP_NAME")
    @Expose
    private String eNGBUSSTOPNAME;
    @SerializedName("REMAIN_STOP")
    @Expose
    private Integer rEMAINSTOP;
    @SerializedName("DIR_START")
    @Expose
    private String dIRSTART;
    @SerializedName("BUS_ID")
    @Expose
    private String bUSID;
    @SerializedName("BUSSTOP_NAME")
    @Expose
    private String bUSSTOPNAME;
    @SerializedName("DIR_END")
    @Expose
    private String dIREND;
    @SerializedName("DIR_PASS")
    @Expose
    private String dIRPASS;
    @SerializedName("LOW_BUS")
    @Expose
    private Integer lOWBUS;
    @SerializedName("CURR_STOP_ID")
    @Expose
    private Integer cURRSTOPID;
    @SerializedName("ARRIVE_FLAG")
    @Expose
    private Integer aRRIVEFLAG;
    @SerializedName("LINE_ID")
    @Expose
    private Integer lINEID;
    @SerializedName("LINE_NAME")
    @Expose
    private String lINENAME;

    public String getARRIVE() {
        return aRRIVE;
    }

    public void setARRIVE(String aRRIVE) {
        this.aRRIVE = aRRIVE;
    }

    public Integer getREMAINMIN() {
        return rEMAINMIN;
    }

    public void setREMAINMIN(Integer rEMAINMIN) {
        this.rEMAINMIN = rEMAINMIN;
    }

    public String getENGBUSSTOPNAME() {
        return eNGBUSSTOPNAME;
    }

    public void setENGBUSSTOPNAME(String eNGBUSSTOPNAME) {
        this.eNGBUSSTOPNAME = eNGBUSSTOPNAME;
    }

    public Integer getREMAINSTOP() {
        return rEMAINSTOP;
    }

    public void setREMAINSTOP(Integer rEMAINSTOP) {
        this.rEMAINSTOP = rEMAINSTOP;
    }

    public String getDIRSTART() {
        return dIRSTART;
    }

    public void setDIRSTART(String dIRSTART) {
        this.dIRSTART = dIRSTART;
    }

    public String getBUSID() {
        return bUSID;
    }

    public void setBUSID(String bUSID) {
        this.bUSID = bUSID;
    }

    public String getBUSSTOPNAME() {
        return bUSSTOPNAME;
    }

    public void setBUSSTOPNAME(String bUSSTOPNAME) {
        this.bUSSTOPNAME = bUSSTOPNAME;
    }

    public String getDIREND() {
        return dIREND;
    }

    public void setDIREND(String dIREND) {
        this.dIREND = dIREND;
    }

    public String getDIRPASS() {
        return dIRPASS;
    }

    public void setDIRPASS(String dIRPASS) {
        this.dIRPASS = dIRPASS;
    }

    public Integer getLOWBUS() {
        return lOWBUS;
    }

    public void setLOWBUS(Integer lOWBUS) {
        this.lOWBUS = lOWBUS;
    }

    public Integer getCURRSTOPID() {
        return cURRSTOPID;
    }

    public void setCURRSTOPID(Integer cURRSTOPID) {
        this.cURRSTOPID = cURRSTOPID;
    }

    public Integer getARRIVEFLAG() {
        return aRRIVEFLAG;
    }

    public void setARRIVEFLAG(Integer aRRIVEFLAG) {
        this.aRRIVEFLAG = aRRIVEFLAG;
    }

    public Integer getLINEID() {
        return lINEID;
    }

    public void setLINEID(Integer lINEID) {
        this.lINEID = lINEID;
    }

    public String getLINENAME() {
        return lINENAME;
    }

    public void setLINENAME(String lINENAME) {
        this.lINENAME = lINENAME;
    }

}