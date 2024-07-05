package com.later.itemservice.Exception;

import com.later.itemservice.constants.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        if (e instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException e1 = (HttpMessageNotReadableException) e;
            e1.printStackTrace();
            return ResponseEntity.badRequest().body(clientError("Error", "BAD REQUEST"));
        } else if (e instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().body(clientError("Error", "BAD REQUEST"));
        } else if (e instanceof NullPointerException) {
            NullPointerException e1 = (NullPointerException) e;
            e1.printStackTrace();
            return ResponseEntity.badRequest().body(serverError("Error", "Something went wrong"));
        } else if (e instanceof MethodArgumentNotValidException) {
            List<String> errors = ((MethodArgumentNotValidException) e).getFieldErrors().stream()
                    .map(er -> er.getField() + " " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            errors.add(0, "Check the following fields:");
            return ResponseEntity.badRequest().body(
                    clientError("Error",
                            errors.toString()
                                    .replace("[", "")
                                    .replace("]", "")
                                    .replace(",", "\n")
                    )
            );
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException e1 = (ConstraintViolationException) e;
            ResponseEntity.badRequest().body(
                    clientError("Error", e1.getLocalizedMessage())
            );
        } else if (e instanceof DataIntegrityViolationException) {
            DataIntegrityViolationException e1 = (DataIntegrityViolationException) e;
            return ResponseEntity.badRequest().body(
                    clientError("Error", "Duplicate entry for '" + getDuplicateDataFromMessage(e1.getMessage()) + "'")
            );
        } else if (e instanceof ApiException) {
            ApiException e1 = (ApiException) e;
            return ResponseEntity.badRequest().body(clientError(e1.getCode(), e.getMessage(), e1.getDescription()));

        }

        return ResponseEntity.badRequest().body(clientError("Error", e.getLocalizedMessage()));
    }


    private ApiResponse clientError(String message, String description) {
        return new ApiResponse(false, 400, message, description);
    }

    private ApiResponse clientError(int code, String message, String description) {
        return new ApiResponse(false, code, message, description);
    }

    private ApiResponse serverError(String message, String description) {
        return new ApiResponse(false, 500, message, description);
    }

    private static String getDuplicateDataFromMessage(String message) {
        // This is a simplified example, you may need to adjust the regex pattern
        // to match the specific format of the exception message
        Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
