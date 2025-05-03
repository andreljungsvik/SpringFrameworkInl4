package se.yrgo.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;

public class CustomerManagementMockImpl implements CustomerManagementService {
    private HashMap<String, Customer> customerMap;

    public CustomerManagementMockImpl() {
        customerMap = new HashMap<String, Customer>();
        customerMap.put("OB74", new Customer("OB74", "Fargo Ltd", "some notes"));
        customerMap.put("NV10", new Customer("NV10", "North Ltd", "some other notes"));
        customerMap.put("RM210", new Customer("RM210", "River Ltd", "some more notes"));
    }


    @Override
    public void newCustomer(Customer newCustomer) {
        customerMap.put(newCustomer.getCustomerId(), newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        String id = changedCustomer.getCustomerId();

        if (customerMap.containsKey(id)) {
            customerMap.put(id, changedCustomer);
        }
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        String customerId = oldCustomer.getCustomerId();
        customerMap.remove(customerId);
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        List<Customer> matchingCustomers = new ArrayList<>();
        for (Customer customer : customerMap.values()) {
            if (customer.getCompanyName().toLowerCase().contains(name.toLowerCase())) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        customer.addCall(callDetails);
    }
}
