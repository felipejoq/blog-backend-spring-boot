package com.uncodigo.blogspringapi.payload;

import java.util.Date;
import java.util.List;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    private List<String> errors;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
