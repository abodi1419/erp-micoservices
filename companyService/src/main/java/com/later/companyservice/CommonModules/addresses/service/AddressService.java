package com.later.companyservice.CommonModules.addresses.service;


import com.later.companyservice.CommonModules.addresses.entity.City;
import com.later.companyservice.CommonModules.addresses.entity.Country;
import com.later.companyservice.CommonModules.addresses.model.CityModel;
import com.later.companyservice.CommonModules.banks.entity.Bank;
import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final RestTemplate restTemplate;

    public List<City> findAllCities() {

        ResponseEntity<ApiResponse<List<City>>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<City>>>() {}
        );
        return response.getBody().getData();
    }


    public City findCityById(Long cityId) throws ApiException {
        ResponseEntity<ApiResponse<City>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list?cityId=" + cityId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<City>>() {}
        );
        if(response.getBody().getData()==null){
            throw new ApiException(404, "City not found");
        }
        return response.getBody().getData();

    }

    public List<Country> findAllCountries() {
        ResponseEntity<ApiResponse<List<Country>>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Country>>>() {}
        );
        return response.getBody().getData();
    }

    public Country findCountryById(Long countryId) throws ApiException {
        ResponseEntity<ApiResponse<Country>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list?id="+countryId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Country>>() {}
        );
        if (response.getBody().getData()==null){
            throw new ApiException(404, "Country not found");
        }
        return response.getBody().getData();
    }

    public City findCityByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<City>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list?cityId=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<City>>() {}
        );
        return response.getBody().getData();
    }

    public Country findCountryByIdOrElseNull(Long id) throws ApiException {
        ResponseEntity<ApiResponse<Country>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Country>>() {}
        );

        return response.getBody().getData();
    }

    public List<CityModel> findAllCitiesByCountryId(Long countryId) {
        ResponseEntity<ApiResponse<List<CityModel>>> response = restTemplate.exchange(
                "http://commonService/api/v1/address/common/city/list?countryId="+countryId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<CityModel>>>() {}
        );
        return response.getBody().getData();
    }
}
