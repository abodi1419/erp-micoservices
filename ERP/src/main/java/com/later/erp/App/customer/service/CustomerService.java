package com.later.erp.App.customer.service;

import com.later.erp.App.customer.entity.Customer;
import com.later.erp.App.customer.entity.CustomerHistory;
import com.later.erp.App.customer.model.CustomerCreationModel;
import com.later.erp.App.customer.repository.CustomerHistoryRepo;
import com.later.erp.App.customer.repository.CustomerRepo;
import com.later.erp.App.customer.repository.LoyaltyProgramRepo;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.ResponseText;
import com.later.erp.constants.UserActions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerHistoryRepo customerHistoryRepo;
    private final LoyaltyProgramRepo loyaltyProgramRepo;

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer findById(Long id) throws ApiException {
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            throw new ApiException(404, "Customer not found");
        }
        return customer;
    }

    public String create(CustomerCreationModel customerCreationModel) throws ApiException {
//        List<LoyaltyProgram> loyaltyPrograms = null;
//        if (customerCreationModel.getLoyaltyPrograms() != null && !customerCreationModel.getLoyaltyPrograms().isEmpty()) {
//            loyaltyPrograms = loyaltyProgramRepo.findAllByIdIn(customerCreationModel.getLoyaltyPrograms());
//        }
        Customer customer = new Customer();
        customer.setName(customerCreationModel.getName());
        customer.setNameAr(customerCreationModel.getNameAr());
        customer.setAddress(customerCreationModel.getAddress());
        customer.setPhone(customerCreationModel.getPhone());
        customer.setEmail(customerCreationModel.getEmail());
//        customer.setLoyaltyPrograms(loyaltyPrograms);
        customerRepo.save(customer);
        return "Created Successfully";
    }

    public String update(Long id, CustomerCreationModel customerCreationModel) throws ApiException {
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            throw new ApiException(404, "Customer not found");
        }
//        List<LoyaltyProgram> loyaltyPrograms = null;
//        if (customerCreationModel.getLoyaltyPrograms() != null && !customerCreationModel.getLoyaltyPrograms().isEmpty()) {
//            loyaltyPrograms = loyaltyProgramRepo.findAllByIdIn(customerCreationModel.getLoyaltyPrograms());
//        }
        CustomerHistory customerHistory = createHistory(customer, UserActions.UPDATE.action(), 1L);
        customer.setName(customerCreationModel.getName());
        customer.setNameAr(customerCreationModel.getNameAr());
        customer.setAddress(customerCreationModel.getAddress());
        customer.setPhone(customerCreationModel.getPhone());
        customer.setEmail(customerCreationModel.getEmail());
        customer.setSerial(customerRepo.getCurrentSerial());
        customer.setRefCode(customer.getRefCode());
//        customer.setLoyaltyPrograms(loyaltyPrograms);
        customerRepo.save(customer);
        customerHistoryRepo.save(customerHistory);
        return ResponseText.UPDATED.text();
    }

    public CustomerHistory createHistory(Customer customer, String action, Long actionBy) throws ApiException {
        CustomerHistory customerHistory = new CustomerHistory();
        customerHistory.setRefCode(customer.getRefCode());
        customerHistory.setSerial(customer.getSerial());
        customerHistory.setName(customer.getName());
        customerHistory.setNameAr(customer.getNameAr());
        customerHistory.setAddress(customer.getAddress());
        customerHistory.setPhone(customer.getPhone());
        customerHistory.setEmail(customer.getEmail());
        customerHistory.setAction(action);
        customerHistory.setActionBy(actionBy);
        return customerHistory;
    }

}
