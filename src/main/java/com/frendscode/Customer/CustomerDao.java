package com.frendscode.Customer;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer(Customer customer);
    boolean existPersonWithEmail(String email);
    boolean existsPersonWithId(Integer id);
    void deleteCustomerById(Integer customerId);
}
