package ua.customermanager.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import ua.customermanager.backend.repository.CustomerRepository;
import ua.customermanager.common.model.Customer;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Implementation of {@link CustomerManagerService}
 */
@Stateless(name = "CustomerManagerService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class CustomerManagerServiceImpl implements CustomerManagerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
