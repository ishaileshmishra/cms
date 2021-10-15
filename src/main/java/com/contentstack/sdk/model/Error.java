package com.contentstack.sdk.model;

public class Error {

    String errorMessage = null;
    int errorCode       = 0;
    String errDetails = null;

    public Error() {
    }

    public Error(String errorMessage, int errorCode, String  errDetails) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errDetails = errDetails;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    protected void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    protected void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return this.errDetails;
    }

    protected void setErrorDetail(String errDetails) {
        this.errDetails = errDetails;
    }

}
