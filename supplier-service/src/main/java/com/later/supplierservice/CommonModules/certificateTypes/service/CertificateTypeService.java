package com.later.supplierservice.CommonModules.certificateTypes.service;


import com.later.supplierservice.CommonModules.banks.entity.Bank;
import com.later.supplierservice.CommonModules.certificateTypes.entity.CertificateType;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateTypeService {

final private RestTemplate restTemplate;
    public List<CertificateType> findAll() {
        ResponseEntity<ApiResponse<List<CertificateType>>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/certificate-types/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<CertificateType>>>() {}
        );
        return response.getBody().getData();    }

    public CertificateType findById(Long id) throws ApiException {
        ResponseEntity<ApiResponse<CertificateType>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/certificate-types/list?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<CertificateType>>() {}
        );
        if(response.getBody().getData()==null){
            throw new ApiException(404, "Certificate Type not found");
        }
        return response.getBody().getData();    }

    public List<CertificateType> findAllMandatory() {
        ResponseEntity<ApiResponse<List<CertificateType>>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/certificate-types/list?mandatory=true",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<CertificateType>>>() {}
        );
        return response.getBody().getData();
    }

    public CertificateType findByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<CertificateType>> response = restTemplate.exchange(
                "http://commonService/api/v1/common/certificate-types/list?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<CertificateType>>() {}
        );
        return response.getBody().getData();    }
}
