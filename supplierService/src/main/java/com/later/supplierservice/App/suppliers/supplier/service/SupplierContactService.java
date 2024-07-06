package com.later.supplierservice.App.suppliers.supplier.service;

import com.later.supplierservice.App.suppliers.supplier.entity.Supplier;
import com.later.supplierservice.App.suppliers.supplier.entity.SupplierContact;
import com.later.supplierservice.App.suppliers.supplier.model.validation.SupplierContactCreationModel;
import com.later.supplierservice.App.suppliers.supplier.repository.SupplierContactRepo;
import com.later.supplierservice.App.suppliers.supplier.repository.SupplierRepo;
import com.later.supplierservice.CommonModules.addresses.entity.City;
import com.later.supplierservice.CommonModules.addresses.service.AddressService;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierContactService {
    private final SupplierRepo supplierRepo;
    private final SupplierContactRepo supplierContactRepo;
    private final AddressService addressService;


    public String create(SupplierContactCreationModel supplierContactCreationModel, Long supplierId) throws ApiException {
        SupplierContact supplierContact = validateCreationModel(supplierContactCreationModel,
                null, supplierId);
        supplierContactRepo.save(supplierContact);
        return "Created successfully";
    }

    public String update(SupplierContactCreationModel supplierContactCreationModel, Long id) throws ApiException {
        SupplierContact oldSupplierContact = supplierContactRepo.findById(id).orElse(null);
        if (oldSupplierContact == null) {
            throw new ApiException(404, "Supplier contact not found");
        }
        SupplierContact supplierContact = validateCreationModel(supplierContactCreationModel,
                oldSupplierContact, oldSupplierContact.getSupplier().getId());
        supplierContactRepo.save(supplierContact);
        return "Updated successfully";
    }

    private SupplierContact validateCreationModel(SupplierContactCreationModel supplierContactCreationModel,
                                                  SupplierContact oldSupplierContact, Long supplierId)
            throws ApiException {
        SupplierContact supplierContact = new SupplierContact();
        if (oldSupplierContact == null) {
            Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
            if (supplier == null) {
                throw new ApiException(404, "Supplier not found");
            }
            supplierContact.setSupplier(supplier);
            supplierContact.setPreferred(
                    supplierContactCreationModel.getPreferred() != null && supplierContactCreationModel.getPreferred() ?
                            supplierContactRepo.getCurrentPreferred() : (short) 0);
        } else {
            supplierContact.setSupplier(oldSupplierContact.getSupplier());
            supplierContact.setId(oldSupplierContact.getId());
            supplierContact.setPreferred(
                    supplierContactCreationModel.getPreferred() != null && supplierContactCreationModel.getPreferred() ?
                            oldSupplierContact.getPreferred() > 0 ? oldSupplierContact.getPreferred() :
                                    supplierContactRepo.getCurrentPreferred() : (short) 0);
        }
        if (supplierContactCreationModel.getContactCityId() != null) {
            City city = addressService.findCityById(supplierContactCreationModel.getContactCityId());
            if (city == null) {
                throw new ApiException(404, "City not found");
            }
            supplierContact.setCityId(city.getId());
            supplierContact.setCityName(city.getNameEn());
            supplierContact.setCityNameAr(city.getNameAr());
        }
        supplierContact.setContactEmail(supplierContactCreationModel.getContactEmail());
        supplierContact.setContactName(supplierContactCreationModel.getContactName());
        supplierContact.setContactNameAr(supplierContactCreationModel.getContactNameAr());
        supplierContact.setContactPhone(supplierContactCreationModel.getContactPhone());
        supplierContact.setContactJobTitle(supplierContactCreationModel.getContactJobTitle());
        supplierContact.setContactJobTitleAr(supplierContactCreationModel.getContactJobTitleAr());
        return supplierContact;
    }

    public String delete(Long id) throws ApiException {
        SupplierContact oldSupplierContact = supplierContactRepo.findById(id).orElse(null);
        if (oldSupplierContact == null) {
            throw new ApiException(404, "Supplier bank account not found");
        }
        if (oldSupplierContact.getSupplier().getStatus().equals(Status.APPROVED.code())) {
            Integer count = supplierContactRepo.countBySupplierId(oldSupplierContact.getSupplier().getId());
            if (count.equals(1)) {
                throw new ApiException(400, "No other contacts found, contact could not be deleted");
            }
        }
        supplierContactRepo.delete(oldSupplierContact);
        return "Deleted successfully";
    }

    public SupplierContact findByIdForOtherSystems(Long supplierContactId, Supplier supplier) throws ApiException {
        SupplierContact supplierContact = supplierContactRepo.findById(supplierContactId).orElse(null);
        if (supplierContact == null || !supplierContact.getSupplier().getId().equals(supplier.getId())) {
            throw new ApiException(404, "Supplier contact not found");
        }
        return supplierContact;
    }

    public List<SupplierContact> findAllBySupplierIdForOtherSystems(Long supplierId) {
        return supplierContactRepo.findAllBySupplier_Id(supplierId);
    }
}
