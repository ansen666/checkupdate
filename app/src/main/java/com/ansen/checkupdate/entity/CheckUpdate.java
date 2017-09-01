package com.ansen.checkupdate.entity;

/**
 * Created by  ansen
 * Create Time 2017-09-01
 */
public class CheckUpdate {
    private String url;
    private String errorReason;//错误说明
    private int errorCode;//0正常

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
