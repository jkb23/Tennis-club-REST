package tennisClub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tennisClub.diagram.Customer;

/**
 * @author Matus Jakab
 */

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

    /**
     * finds customer by specified phone number
     * @param phoneNumber unique phone number
     * @return customer with given phone number
     */
    Customer findAllByPhoneNumber(String phoneNumber);
}
