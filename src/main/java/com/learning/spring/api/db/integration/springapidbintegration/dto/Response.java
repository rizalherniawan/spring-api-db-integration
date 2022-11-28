package com.learning.spring.api.db.integration.springapidbintegration.dto;

public class Response<T> {
    private int status;
    private boolean success;
    private T data;


    public Response(int status, boolean success, T data) {
        this.status = status;
        this.success = success;
        this.data = data;
    }
    
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
