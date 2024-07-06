package com.later.erp.CommonModules.addresses.service;

import com.later.erp.CommonModules.addresses.entity.City;
import com.later.erp.CommonModules.addresses.entity.Country;
import com.later.erp.CommonModules.addresses.model.CityModel;
import com.later.erp.CommonModules.addresses.repository.CityRepo;
import com.later.erp.CommonModules.addresses.repository.CountryRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;


    public List<City> findAllCities() {
        return cityRepo.findAll();
    }

    public List<CityModel> findAllCitiesModel() {
        return cityRepo.getAll();
    }

    public City findCityById(Long cityId) throws ApiException {
        City city = cityRepo.findById(cityId).orElse(null);
        if (city == null) {
            throw new ApiException(404, "City not found");
        }
        return city;
    }

    public List<Country> findAllCountries() {
        return countryRepo.findAll();
    }

    public Country findCountryById(Long countryId) throws ApiException {
        Country country = countryRepo.findById(countryId).orElse(null);
        if (country == null) {
            throw new ApiException(404, "Country not found");
        }
        return country;
    }

    public City findCityByIdOrElseNull(Long id) throws ApiException {
        return cityRepo.findById(id).orElse(null);
    }

    public Country findCountryByIdOrElseNull(Long id) throws ApiException {
        return countryRepo.findById(id).orElse(null);
    }

    public List<CityModel> findAllCitiesByCountryId(Long countryId) {
        return cityRepo.getAllByCountry_Id(countryId);
    }
}
