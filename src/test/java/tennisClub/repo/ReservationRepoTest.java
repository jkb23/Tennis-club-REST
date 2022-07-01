package tennisClub.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tennisClub.diagram.Reservation;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Matus Jakab
 */

@SpringBootTest
class ReservationRepoTest {

    @Autowired
    private ReservationRepo repo;

    @Test
    public void saveReserv(){
        Reservation reservation = Reservation.builder()
                .doubles(false)
                .courtId((long)7)
                .customerPhoneNumber("+421022584247")
                .startTime(Timestamp.valueOf("2022-5-2 14:00:00"))
                .endTime(Timestamp.valueOf("2022-5-2 17:00:00")).build();

        repo.save(reservation);
    }

    @Test
    public void printAll(){
        List<Reservation> reservations = repo.findAll();

        System.out.println(reservations);
    }

    @Test
    public void printReservationsByCourtId() {

        List<Reservation> reservations = repo.findAllByCourtId((long)7);

        System.out.println(reservations);
    }

    @Test
    public void printReservationsByCustomerPhone() {

        List<Reservation> reservations = repo.findAllByCustomerPhoneNumber("+421022584248");

        System.out.println(reservations);
    }

    @Test
    public void checkTime() {
        Timestamp start = Timestamp.valueOf("2022-5-2 15:00:00");
        Timestamp end = Timestamp.valueOf("2022-5-2 16:00:00");
        System.out.println(repo.existsByEndTimeIsAfterAndIdEquals(start, (long)7));
        System.out.println(repo.existsByStartTimeIsBeforeAndIdEquals(end, (long)7));
    }
}