package tennisClub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tennisClub.diagram.Reservation;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Matus Jakab
 */

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    /**
     * finds all reservations played on specific court
     * @param id court id on which we want to find reservations
     * @return list of all reservations played on specific court
     */
    List<Reservation> findAllByCourtId(Long id);

    /**
     * finds all reservations ordered by specific customer
     * @param phoneNumber phone number of customer we want to find reservations
     * @return List of all reservations ordered by specific customer
     */
    List<Reservation> findAllByCustomerPhoneNumber(String phoneNumber);

    /**
     * checks if given Timestamp (start of new game) isn't in database
     * before already saved end time of other reservation specified by id
     * @param newStart start time of new reservation
     * @param id id of reservation we are comparing to
     * @return true if there is a conflict in database, false if not
     */
    boolean existsByEndTimeIsAfterAndIdEquals(Timestamp newStart,  Long id);

    /**
     * checks if given Timestamp (end of new game) isn't in database
     * after already saved start time of other reservation specified by id
     * @param newEnd end time of new reservation
     * @param id id of reservation we are comparing to
     * @return true if there is a conflict in database, false if not
     */
    boolean existsByStartTimeIsBeforeAndIdEquals(Timestamp newEnd, Long id);
}
