package org.theproject.mockito.helloworld;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Verify that the service calls the (mocked) DAO an expected number of times
@ExtendWith(SpringExtension.class)
class T02VerifyBehavior {

    private static final String FIRST_NAME = "Luna";

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerDao mockedCustomerDao;

    List<Customer> customers;

    @Test
    public void findHermioneTest() {

        Customer luna = new Customer(0, FIRST_NAME, "Lovegood");

        when(mockedCustomerDao.findWithFirstName(eq(FIRST_NAME))).thenReturn(luna);

        Optional<Customer> actual = customerService.getCustomerWithFirstName(FIRST_NAME);

        // The thing we are testing here is that calling the service method causes the (mocked) DAO method to get called.
        verify(mockedCustomerDao).findWithFirstName(any());  // that it got called at least once with any argument
        verify(mockedCustomerDao, times(1)).findWithFirstName(any());  // that it got called exactly once with any argument
        verify(mockedCustomerDao, times(1)).findWithFirstName(anyString());  // that it got called once with any string
        verify(mockedCustomerDao, times(1)).findWithFirstName(eq(FIRST_NAME));  // that it got called once with "Luna"
    }

}
