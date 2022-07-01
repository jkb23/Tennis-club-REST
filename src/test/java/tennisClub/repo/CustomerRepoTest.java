package tennisClub.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tennisClub.diagram.Court;
import tennisClub.diagram.Customer;
import tennisClub.diagram.Surface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Matus Jakab
 */

@SpringBootTest
class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @Test
    public void saveCustomer(){
        Customer customer = Customer.builder().name("Marek").phoneNumber("+421127452589").build();

        customerRepo.save(customer);
    }

    @Test
    public void printAll(){
        List<Customer> customers = customerRepo.findAll();

        System.out.println(customers);
    }
}