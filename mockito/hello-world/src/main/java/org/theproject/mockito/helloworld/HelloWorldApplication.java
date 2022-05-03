package org.theproject.mockito.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

    @Component
    public class HelloWorldCommandLineRunner implements CommandLineRunner {

        @Autowired
        CustomerService customerService;

        @Override
        public void run(String... args) {
            Optional<String> snapesFirstName = customerService.getSnapesFirstName();
            if (snapesFirstName.isPresent()) {
                System.out.println("Snape's first name is " + snapesFirstName.get());
            } else {
                System.out.println("There is no Snape");
            }
        }
    }

}

