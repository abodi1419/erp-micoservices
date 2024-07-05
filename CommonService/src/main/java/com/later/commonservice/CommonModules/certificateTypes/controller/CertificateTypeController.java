package com.later.commonservice.CommonModules.certificateTypes.controller;


import com.later.commonservice.CommonModules.certificateTypes.service.CertificateTypeService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/certificate-types")
@RequiredArgsConstructor
public class CertificateTypeController {

    private final CertificateTypeService certificateTypeService;

    @GetMapping("list")
    public ResponseEntity listCertificateTypes(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) Boolean mandatory) {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", certificateTypeService
                            .findByIdOrElseNull(id))
            );
        }

        if(mandatory != null && mandatory){
            return ok().body(
                    new ApiResponse(true, 200, "Success", certificateTypeService
                            .findAllMandatory())
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", certificateTypeService.findAll())
        );
    }
}
