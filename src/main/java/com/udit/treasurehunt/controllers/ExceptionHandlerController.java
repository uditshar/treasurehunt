package com.udit.treasurehunt.controllers;

import com.udit.treasurehunt.exceptions.ApiError;
import com.udit.treasurehunt.exceptions.LoopDetectedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletResponse response) {
        ApiError apiErr = new ApiError("400");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String rejectVal = ((FieldError)error).getRejectedValue().toString();
            String errorMessage = error.getDefaultMessage();
            apiErr.addErr(new ApiError.ApiErrorMessage(fieldName,rejectVal, errorMessage));
        });
      return apiErr;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(LoopDetectedException.class)
    public ApiError handleLoopExceptions(
            LoopDetectedException ex) {
        ApiError apiErr = new ApiError("400");
        apiErr.addErr(new ApiError
                        .ApiErrorMessage("Treasure map matrix", "", "A loop was detected in the treasure map"));
        return apiErr;
    }
}
