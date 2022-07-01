package tennisClub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tennisClub.diagram.Court;

/**
 * @author Matus Jakab
 */

@Repository
public interface CourtRepo extends JpaRepository<Court, Long> {

    /**
     * finds court by specified id
     * @param id unique court id
     * @return court with given id
     */
    Court findAllById(Long id);
}
