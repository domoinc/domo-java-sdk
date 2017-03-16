package com.domo.sdk.request;

public class ErrorResponse {

    private int status;
    private String statusReason;
    private String message;
    private String toe;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToe() {
        return toe;
    }

    public void setToe(String toe) {
        this.toe = toe;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", statusReason='" + statusReason + '\'' +
                ", message='" + message + '\'' +
                ", toe='" + toe + '\'' +
                '}';
    }

    public String toErrorString() {
        return statusReason+" ("+status+"): "+message+". toe='"+toe+"'";
    }



}
