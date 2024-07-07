package com.later.commonservice.CommonModules.addresses.controller;

import com.later.commonservice.CommonModules.addresses.service.AddressService;
import com.later.commonservice.Exception.ApiException;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("city/list")
    public ResponseEntity getAllCities(@RequestParam(required = false) Long cityId,
                                       @RequestParam(required = false) Long countryId) throws ApiException {
        if (cityId != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", addressService.findCityByIdOrElseNull(cityId))
            );
        } else if (countryId != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", addressService.findAllCitiesByCountryId(countryId))
            );
        }else {
            return ok().body(
                    new ApiResponse(true, 200, "Success", addressService.findAllCities())
            );
        }
    }

    @GetMapping("country/list")
    public ResponseEntity getCountries(@RequestParam(required = false) Long id) throws ApiException {
        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", addressService.findCountryByIdOrElseNull(id))
            );
        }else {
            return ok().body(
                    new ApiResponse(true, 200, "Success", addressService.findAllCountries())
            );
        }
    }

}
