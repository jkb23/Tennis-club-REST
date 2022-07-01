package tennisClub.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tennisClub.diagram.Court;
import tennisClub.diagram.Surface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Matus Jakab
 */

@SpringBootTest
class CourtRepoTest {

    @Autowired
    private CourtRepo courtRepo;

    @Test
    public void saveCourt(){
        Court court = Court.builder().surface(Surface.CLAY).build();

        courtRepo.save(court);
    }

    @Test
    public void printAll(){
        List<Court> courts = courtRepo.findAll();

        System.out.println(courts);
    }
}