package com.example.leekijung.rxbusparsingexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RESULT {

@SerializedName("RESULT_MSG")
@Expose
private String rESULTMSG;
@SerializedName("RESULT_CODE")
@Expose
private String rESULTCODE;

public String getRESULTMSG() {
return rESULTMSG;
}

public void setRESULTMSG(String rESULTMSG) {
this.rESULTMSG = rESULTMSG;
}

public String getRESULTCODE() {
return rESULTCODE;
}

public void setRESULTCODE(String rESULTCODE) {
this.rESULTCODE = rESULTCODE;
}

}
