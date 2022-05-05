package org.theproject.mockito.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    protected List<Customer> findAll() {
        return jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
    }

    protected Customer findWithFirstName(String firstName) {
        List<Customer> cs = jdbcTemplate.query(
                "select id, first_name, last_name from customers where first_name = ?",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")),
                firstName
        );
        return cs.get(0);
    }

}
