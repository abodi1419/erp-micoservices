package com.later.procurement.CommonModules.addresses.service;


import com.later.procurement.CommonModules.addresses.entity.City;
import com.later.procurement.CommonModules.addresses.entity.Country;
import com.later.procurement.CommonModules.addresses.model.CityModel;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final RestTemplate restTemplate;

    public List<City> findAllCities() {

        return List.of(new City());
    }

    public List<CityModel> findAllCitiesModel() {
        restTemplate.getForObject("http://localhost:8080/city", List.class);
        return List.of(new CityModel());
    }

    public City findCityById(Long cityId) throws ApiException {

        return null;
    }

    public List<Country> findAllCountries() {
        return List.of(new Country());
    }

    public Country findCountryById(Long countryId) throws ApiException {
        return null;
    }

    public City findCityByIdOrElseNull(Long id) throws ApiException {
        return null;
    }

    public Country findCountryByIdOrElseNull(Long id) throws ApiException {
        return null;
    }

    public List<CityModel> findAllCitiesByCountryId(Long countryId) {
        return List.of(new CityModel());
    }
}
