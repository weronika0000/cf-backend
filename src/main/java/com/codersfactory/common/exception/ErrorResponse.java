package com.codersfactory.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    private Object data;

    private String stackTrace;

    public ErrorResponse() {
        this.timestamp = new Date();
    }

    public ErrorResponse(String status, String message) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
    }

}