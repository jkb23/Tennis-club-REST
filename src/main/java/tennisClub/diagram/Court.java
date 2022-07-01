package tennisClub.diagram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Matus Jakab
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "court", uniqueConstraints = @UniqueConstraint(
        name = "court_id",
        columnNames = "id"
))

public class Court {
    @Id
    @SequenceGenerator(
            name = "court_sequence",
            sequenceName = "court_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "court_sequence"
    )
    private long id;
    @Column(name = "surface", nullable = false)
    @Enumerated(EnumType.STRING)
    private Surface surface;

    public Surface getSurface() {
        return surface;
    }

    public long getId() {
        return id;
    }
}
