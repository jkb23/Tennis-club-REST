package tennisClub.diagram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Matus Jakab
 */

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    private String phoneNumber;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }
}
