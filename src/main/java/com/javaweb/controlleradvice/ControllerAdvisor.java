package com.javaweb.controlleradvice;

import com.javaweb.exception.ValidateDataBuildingException;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ValidateDataBuildingException.class)
    public ResponseEntity<?> handleValidateDataBuildingException(ValidateDataBuildingException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Validation Failed");
        responseDTO.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .distinct()
                .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Validation Failed");
        responseDTO.setData(errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Internal Server Error");
        responseDTO.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }
}
