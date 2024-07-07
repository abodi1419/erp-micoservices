package com.later.commonservice.CommonModules.addresses.repository;

import com.later.commonservice.CommonModules.addresses.entity.City;
import com.later.commonservice.CommonModules.addresses.model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    @Query("select new com.later.commonservice.CommonModules.addresses.model.CityModel(c.id, c.nameEn,c.nameAr) " +
            " from City c where c.country.id=:countryId order by c.id ")
    List<CityModel> getAllByCountry_Id(Long countryId);

    @Query("select new com.later.commonservice.CommonModules.addresses.model.CityModel(c.id, c.nameEn,c.nameAr)" +
            " from City c order by c.id " )
    List<CityModel>getAll();

}
