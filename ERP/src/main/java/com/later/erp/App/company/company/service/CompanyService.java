package com.later.erp.App.company.company.service;

import com.later.erp.App.company.company.entity.Company;
import com.later.erp.App.company.company.model.CompanyCreationModel;
import com.later.erp.App.company.company.repository.CompanyHistoryRepo;
import com.later.erp.App.company.company.repository.CompanyRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final CompanyHistoryRepo companyHistoryRepo;

    public Company getCompany() throws ApiException {
        List<Company> companies = companyRepo.findAll();
        if (companies == null || companies.size() <= 0) {
            throw new ApiException(404, "Company information not found");
        }
        return companies.get(0);
    }

    public String create(CompanyCreationModel companyCreationModel) throws ApiException {
        List<Company> companies = companyRepo.findAll();
        if (companies != null && companies.size() > 0) {
            throw new ApiException(400, "Company information already added");
        }
        Company company = new Company();
        company.setName(companyCreationModel.getName());
        company.setNameAr(companyCreationModel.getArabicName());
        company.setDescription(companyCreationModel.getDescription());
        company.setDescriptionAr(companyCreationModel.getDescriptionAr());
        company.setLogo(companyCreationModel.getLogo());
        company.setWebsite(companyCreationModel.getWebsite());
        company.setCommercialRegister(companyCreationModel.getCommercialRegister());
        company.setSize(companyCreationModel.getSize());
        company.setHqAddress(companyCreationModel.getHqAddress());
        company.setHqAddressAr(companyCreationModel.getArabicHqAddress());
        company.setPhone(companyCreationModel.getPhone());
        company.setVATNumber(companyCreationModel.getVatNumber());
        companyRepo.save(company);
        return "Created Successfully";
    }
}
