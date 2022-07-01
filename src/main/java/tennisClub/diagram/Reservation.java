package tennisClub.diagram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.sql.Timestamp;

/**
 * @author Matus Jakab
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation", uniqueConstraints = {
        @UniqueConstraint(name = "reservationId", columnNames = "id"),
        @UniqueConstraint(name = "uniqueForEveryReservation", columnNames = {"court_id",
                "start_time", "end_time"})
})
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private long id;

    @Column(nullable = false, name = "court_id")
    private Long courtId;

    @Column(nullable = false, name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(nullable = false, name = "start_time")
    private Timestamp startTime;

    @Column(nullable = false, name = "end_time")
    private Timestamp endTime;

    @Column(nullable = false)
    private boolean doubles;

    public long getId() {
        return id;
    }

    public Long getCourtId() {
        return courtId;
    }
}
