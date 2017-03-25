package com.salesmanager.admin.data.response;

/**
 * Created by umesh on 3/25/17.
 */
public class ResponseData {

    public ResponseData() {
    }

    public ResponseData(int status) {
        this.status = status;
    }

    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
