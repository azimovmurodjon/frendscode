package com.frendscode.Customer;

import com.frendscode.exception.DuplicateResourceException;
import com.frendscode.exception.RequestValidationException;
import com.frendscode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    public final CustomerDao customerDao;


    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer with id [%s] not found".formatted(id)
                ));
    }

    public void addCustomer(
            CustomerRegistrationRequest customerRegistrationequest) {
        //check if email is exists
        String email = customerRegistrationequest.email();
        if (customerDao.existPersonWithEmail(email)) {
            throw new DuplicateResourceException(
                    "Email already existed"
            );
        }
        // Add
        Customer customer = new Customer(
                customerRegistrationequest.name(),
                customerRegistrationequest.email(),
                customerRegistrationequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer customerId) {
        if (!customerDao.existsPersonWithId(customerId)) {
            throw new ResourceNotFoundException(
                    "customer with id [%s] not found".formatted(customerId)
            );
        }

        customerDao.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId,
                               CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(customerId);
        boolean changes = false;

        if (updateRequest.name() != null &&
                !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.age() != null &&
                !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if (updateRequest.email() != null &&
                !updateRequest.email().equals(customer.getEmail())) {
            if (customerDao.existPersonWithEmail(updateRequest.email())) {
                throw new DuplicateResourceException(
                        "This Email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if (!changes){
            throw new RequestValidationException(
                    "No Data Changes Found"
            );
        }
        customerDao.updateCustomer(customer);
    }

}
