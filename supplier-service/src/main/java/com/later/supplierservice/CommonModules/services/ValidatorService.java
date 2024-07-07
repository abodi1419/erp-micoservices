package com.later.supplierservice.CommonModules.services;


import com.later.supplierservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidatorService {
    private final Validator validator;

    public void validate(Object object) throws ApiException {
        Errors errors = validator.validateObject(object);
        if (errors.hasErrors()) {
            List<String> stringErrors = errors.getFieldErrors().stream()
                    .map(er -> er.getField() + " " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            checkErrors(stringErrors);
        }
    }

    public void validateSpecific(Object object, List<String> specificFields) throws ApiException {
        Errors errors = validator.validateObject(object);
        if (errors.hasErrors()) {
            List<String> stringErrors = errors.getFieldErrors().stream()
                    .filter(e -> specificFields.contains(e.getField()))
                    .map(er -> er.getField() + " " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            checkErrors(stringErrors);
        }
    }

    public void validateExcept(Object object, List<String> exceptFields) throws ApiException {
        Errors errors = validator.validateObject(object);
        if (errors.hasErrors()) {
            List<String> stringErrors = errors.getFieldErrors().stream()
                    .filter(e -> !exceptFields.contains(e.getField()))
                    .map(er -> er.getField() + " " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            checkErrors(stringErrors);
        }
    }

    private void checkErrors(List<String> stringErrors) throws ApiException {
        if (stringErrors.size() > 0) {
            stringErrors.add(0, "Check the following fields:");
            throw new ApiException(400, stringErrors.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "\n"));
        }
    }

}
