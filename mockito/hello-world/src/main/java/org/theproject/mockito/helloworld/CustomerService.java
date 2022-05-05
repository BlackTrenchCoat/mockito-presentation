package org.theproject.mockito.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public Optional<String> getSnapesFirstName() {
        List<Customer> customers = customerDao.findAll();
        List<String> results = customers.stream()
                .filter(x -> "Snape".equals(x.getLastName()))
                .map(x -> x.getFirstName()).collect(Collectors.toList());
        if (results.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    public Optional<Customer> getCustomerWithFirstName(String firstName) {
        Customer customer = customerDao.findWithFirstName(firstName);
        return Optional.of(customer);
    }
}
