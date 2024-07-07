package com.later.supplierservice.App.suppliers.supplier.service;

import com.later.supplierservice.Security.Auth.entities.Employee;
import com.later.supplierservice.App.suppliers.ApprovalService.entity.SupplierActiveApproval;
import com.later.supplierservice.App.suppliers.ApprovalService.service.SupplierApprovalService;
import com.later.supplierservice.App.suppliers.supplier.entity.Supplier;
import com.later.supplierservice.App.suppliers.supplier.entity.SupplierCertificate;
import com.later.supplierservice.App.suppliers.supplier.model.SupplierListModel;
import com.later.supplierservice.App.suppliers.supplier.model.validation.SupplierCreationModel;
import com.later.supplierservice.App.suppliers.supplier.repository.SupplierRepo;
import com.later.supplierservice.Security.Auth.entities.LoginUser;
import com.later.supplierservice.CommonModules.addresses.entity.City;
import com.later.supplierservice.CommonModules.addresses.entity.Country;
import com.later.supplierservice.CommonModules.addresses.model.CityModel;
import com.later.supplierservice.CommonModules.addresses.service.AddressService;
import com.later.supplierservice.CommonModules.banks.entity.Bank;
import com.later.supplierservice.CommonModules.banks.service.BankService;
import com.later.supplierservice.CommonModules.businessTypes.entity.BusinessType;
import com.later.supplierservice.CommonModules.businessTypes.service.BusinessTypeService;
import com.later.supplierservice.CommonModules.certificateTypes.entity.CertificateType;
import com.later.supplierservice.CommonModules.certificateTypes.service.CertificateTypeService;
import com.later.supplierservice.CommonModules.currencies.entity.Currency;
import com.later.supplierservice.CommonModules.currencies.service.CurrencyService;
import com.later.supplierservice.CommonModules.industryTypes.entity.IndustryType;
import com.later.supplierservice.CommonModules.industryTypes.service.IndustryTypeService;
import com.later.supplierservice.CommonModules.termsTypes.entity.TermType;
import com.later.supplierservice.CommonModules.termsTypes.service.TermTypeService;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.RequestFilters;
import com.later.supplierservice.constants.ResponseText;
import com.later.supplierservice.constants.Status;
import com.later.supplierservice.constants.SystemCode;
import com.later.supplierservice.util.RefCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepo supplierRepo;
    private final SupplierApprovalService supplierApprovalService;

    private final BankService bankService;
    private final TermTypeService termTypeService;
    private final IndustryTypeService industryTypeService;
    private final BusinessTypeService businessTypeService;
    private final CurrencyService currencyService;
    private final CertificateTypeService certificateTypeService;
    private final AddressService addressService;

    public List<SupplierListModel> findAll(String filter, Employee employee) {
        if (filter.equals(RequestFilters.WAITING_MY_ACTION.filter())) {
            return supplierRepo.waitingMyAction(employee.getId());
        } else if (filter.equals(RequestFilters.ACTIVE.filter())) {
            return supplierRepo.active(employee.getId());
        } else if (filter.equals(RequestFilters.CLOSED.filter())) {
            return supplierRepo.closed(employee.getId());
        } else if (filter.equals(RequestFilters.MY_REQUESTS.filter())) {
            return supplierRepo.myRequests(employee.getId());
        } else {
            return supplierRepo.waitingMyAction(employee.getId());
        }
    }

    public Supplier findById(Long id) throws ApiException {
        Supplier supplier = supplierRepo.findById(id).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        return supplier;
    }

    public String create(SupplierCreationModel supplierCreationModel, Employee loginUser) throws ApiException {
        Supplier supplier = validateTempCreationModel(supplierCreationModel, null, loginUser);
        supplierRepo.save(supplier);
        return "Create successfully";
    }

    public String update(SupplierCreationModel supplierCreationModel, Long id, Employee loginUser) throws ApiException {
        Supplier oldSupplier = supplierRepo.findById(id).orElse(null);
        if (oldSupplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        Supplier supplier = validateTempCreationModel(supplierCreationModel, oldSupplier, loginUser);

        supplierRepo.save(supplier);
        return "Updated successfully";
    }

    private Supplier validateTempCreationModel(SupplierCreationModel supplierCreationModel, Supplier oldSupplier, Employee loginUser) throws ApiException {
        Supplier supplier = new Supplier();
        if (supplierCreationModel.getPreferredCurrency() != null) {
            Currency currency = currencyService.findByIdOrElseNull(supplierCreationModel.getPreferredCurrency());
            if (currency != null) {
                supplier.setCurrencyId(currency.getId());
                supplier.setCurrencyName(currency.getName());
                supplier.setCurrencyNameAr(currency.getNameAr());
                supplier.setCurrencyShortName(currency.getShortName());
                supplier.setCurrencyShortNameAr(currency.getShortNameAr());
            }
        }
        if (supplierCreationModel.getCityId() != null) {
            City city = addressService.findCityByIdOrElseNull(supplierCreationModel.getCityId());
            if (city != null) {
                supplier.setCityId(city.getId());
                supplier.setCityName(city.getNameEn());
                supplier.setCityNameAr(city.getNameAr());

            }
        }
        if (supplierCreationModel.getPrimaryIndustry() != null) {
            IndustryType industryType = industryTypeService.findByIdOrElseNull(supplierCreationModel.getPrimaryIndustry());
            if (industryType != null) {
                supplier.setIndustryTypeId(industryType.getId());
                supplier.setIndustryTypeName(industryType.getIndustry());
                supplier.setIndustryTypeNameAr(industryType.getIndustryAr());

            }
        }
        supplier.setAddress(supplierCreationModel.getAddress());
        supplier.setFax(supplierCreationModel.getFax());
        supplier.setEmail(supplierCreationModel.getEmail());
        supplier.setPhone(supplierCreationModel.getPhone());
        supplier.setCommercialRegister(supplierCreationModel.getCommercialRegister());
        supplier.setCompanyName(supplierCreationModel.getCompanyName());
        supplier.setCompanyNameAr(supplierCreationModel.getCompanyNameAr());
        supplier.setSize(supplierCreationModel.getSize());
        supplier.setIncorporationDate(supplierCreationModel.getIncorporationDate());
        supplier.setTradingName(supplierCreationModel.getTradingName());
        supplier.setTradingNameAr(supplierCreationModel.getTradingNameAr());
        supplier.setVatNumber(supplierCreationModel.getVatNumber());
        supplier.setTelephoneNumber(supplierCreationModel.getTelephoneNumber());
        supplier.setLocal(supplierCreationModel.getLocal());
        supplier.setWebsite(supplierCreationModel.getWebsite());
        supplier.setStatus((short) 0);
        supplier.setCreatedById(loginUser.getId());
        supplier.setCreatedByName(loginUser.getEmployeeFullName());
        supplier.setCreatedByNameAr(loginUser.getEmployeeFullNameAr());
        supplier.setCreatedByCompanyNumber(loginUser.getEmployeeCompanyNumber());
        if (oldSupplier == null) {
            supplier.setSerial(supplierRepo.getCurrentSerial());
            supplier.setRefCode(RefCode.generateTemp("TMP-SP", supplier.getSerial()));
        } else {
            supplier.setId(oldSupplier.getId());
            supplier.setSerial(oldSupplier.getSerial());
            supplier.setRefCode(oldSupplier.getRefCode());
        }
        return supplier;
    }

    public String approve(HashMap<String, String> map, Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }
        Employee employee = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        if (employee == null) {
            throw new ApiException(404, "Approver not found");
        }
        supplier = (Supplier) supplierApprovalService.approve(supplier, SystemCode.SUPPLIER.systemCode(), employee, map.getOrDefault("comment", null));
        if (supplier.getStatus().equals(Status.APPROVED.code())) {
            supplier.setActive(true);
        }
        supplierRepo.save(supplier);

        return "Approved successfully";
    }

    public String reject(Long supplierId, Employee loginUser, HashMap<String, String> map) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ApiException(404, "Supplier not found");
        }

        Supplier supplier1 = (Supplier) supplierApprovalService.reject(supplier, SystemCode.SUPPLIER.systemCode(), loginUser, map.getOrDefault("comment", null));
        supplierRepo.save(supplier1);

        return "Rejected successfully";
    }

    public List<Bank> getBanks() {
        return bankService.findAll();
    }

    public List<IndustryType> getIndustryTypes() {
        return industryTypeService.findAll();
    }

    public List<Currency> getCurrencies() {
        return currencyService.findAll();
    }

    public List<CertificateType> getCertificateTypes() {
        return certificateTypeService.findAll();
    }

    public List<TermType> getTermTypes() {
        return termTypeService.findAll();
    }

    public List<BusinessType> getBusinessTypes() {
        return businessTypeService.findAll();
    }

    public List<Country> getCountries() {
        return addressService.findAllCountries();
    }

    public List<CityModel> getCities(Long countryId) {
        return addressService.findAllCitiesByCountryId(countryId);
    }

    public String submit(Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null || !supplier.getStatus().equals(Status.DRAFT.code())) {
            throw new ApiException(404, "Supplier not found");
        }
        supplier.setRefCode(RefCode.generate(SystemCode.SUPPLIER.systemCode(), supplier.getSerial()));
        if (supplier.getSupplierBankAccounts().isEmpty()) {
            throw new ApiException(400, "At least one bank account must be provided");
        }
        if (supplier.getSupplierBusinessTypes().isEmpty()) {
            throw new ApiException(400, "At least one business type must be provided");
        }

        List<CertificateType> certificateTypes = certificateTypeService.findAllMandatory();
        if (supplier.getSupplierCertificates().isEmpty()) {
            String certificates = certificateTypes.stream().map(c -> c.getName() + " - " + c.getNameAr()).toList().toString();
            throw new ApiException(400, "At least " + certificates + " certificates must be provided");
        } else {
            List<Long> supplierCertificateIds = supplier.getSupplierCertificates().stream().map(SupplierCertificate::getCertificateTypeId)
                    .toList();
            certificateTypes = certificateTypes.stream().filter(c -> !supplierCertificateIds.contains(c.getId())).toList();
            if (!certificateTypes.isEmpty()) {
                String certificates = certificateTypes.stream().map(c -> c.getName() + " - " + c.getNameAr()).toList().toString();
                throw new ApiException(400, "At least " + certificates + " certificates must be provided");
            }
        }
        if (supplier.getSupplierContacts().isEmpty()) {
            throw new ApiException(400, "At least one contact must be provided");
        }

        List<SupplierActiveApproval> systemsActiveApprovals = supplierApprovalService.generateApprovers(supplier, SystemCode.SUPPLIER.systemCode());

        if (systemsActiveApprovals != null) {
            supplier.setStatus((short) 1);
        } else {
            supplier.setStatus(Status.APPROVED.code());
            supplier.setActive(true);
        }
        supplier = supplierRepo.save(supplier);
        if (systemsActiveApprovals != null) {
            supplierApprovalService.saveApproval(systemsActiveApprovals, supplier);
        }
        return ResponseText.SUBMITTED.text();
    }

    public Supplier findByIdForOtherSystems(Long supplierId) throws ApiException {
        Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
        if (supplier == null || !supplier.getActive()) {
            throw new ApiException(404, "Supplier not found");
        }
        return supplier;
    }

    public List<SupplierListModel> findAllForOtherSystems() {
        return supplierRepo.findAllActive();
    }
}
