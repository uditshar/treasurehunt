package com.udit.treasurehunt.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {
    String status;
    List<ApiErrorMessage> errorMessages;
    public ApiError(String status){
        errorMessages = new ArrayList<>();
        this.status = status;
    }
    public void addErr(ApiErrorMessage apiErrorMessage){
        errorMessages.add(apiErrorMessage);
    }

    @Data
    @AllArgsConstructor
    public static class ApiErrorMessage {
        String fieldname, rejectedValue,message;
    }
}


