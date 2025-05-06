package se.yrgo.services.customers;

import se.yrgo.dataaccess.CustomerDao;
import se.yrgo.dataaccess.RecordNotFoundException;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;

import java.util.List;

public class CustomerManagementServiceProductionImpl implements CustomerManagementService {
    private final CustomerDao customerDao;

    public CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        customerDao.create(newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException {
        try {
            customerDao.update(changedCustomer);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + changedCustomer.getCustomerId(), e);
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws CustomerNotFoundException {
        try {
            customerDao.delete(customer);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customer.getCustomerId(), e);
        }
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId, e);
        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) throws CustomerNotFoundException {
        List<Customer> customers = customerDao.getByName(name);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found with name: " + name);
        }
        return customers;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getFullCustomerDetail(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId, e);
        }
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        try {
            customerDao.addCall(callDetails, customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId, e);
        }
    }
}
