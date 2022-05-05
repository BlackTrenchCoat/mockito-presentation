package org.theproject.mockito.helloworld;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

// Mock the DAO to test the service without accessing the database
@ExtendWith(SpringExtension.class) // In JUnit 5, this has a similar effect to @RunWith(MockitoJUnitRunner.class) in JUnit 4
class T01SimulateDBAccess {

    private static final String FIRST_NAME = "Barney";

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerDao mockedCustomerDao;

    List<Customer> customers;

    @Test
    public void snapesFirstNameTest() {

        customers = new ArrayList<>();
        Customer snape = new Customer(0, FIRST_NAME, "Snape");
        customers.add(snape);

        when(mockedCustomerDao.findAll()).thenReturn(customers);

        Optional<String> actual = customerService.getSnapesFirstName();

        assertThat(actual.isPresent());
        assertThat(actual.get()).isEqualTo(FIRST_NAME);
    }

}
