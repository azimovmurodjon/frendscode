package com.frendscode.Customer;

import com.frendscode.exception.DuplicateResourceException;
import com.frendscode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!customerDao.existPersonWithId(customerId)){
            throw new ResourceNotFoundException(
                    "Customer with id [%s] not found".formatted(customerId)
            );
        }
        customerDao.deleteCustomerById(customerId);

    }
}
