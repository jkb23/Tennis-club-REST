package tennisClub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tennisClub.diagram.Court;
import tennisClub.diagram.Surface;
import tennisClub.repo.CourtRepo;

import java.util.List;

/**
 * @author Matus Jakab
 */

@RestController
public class CourtController {
    private final CourtRepo courtRepo;

    public CourtController(CourtRepo courtRepo) {
        this.courtRepo = courtRepo;
    }

    /**
     * REST endpoint to see list of courts
     * @return list of all courts
     */
    @RequestMapping(value = "/courts", method = RequestMethod.GET)
    @ResponseBody
    public List<Court> getCourts(){
        return courtRepo.findAll();
    }


    /**
     * REST endpoint to add court to database
     * price is determined by given surface
     * id is associated automatically
     * @param surface surface of new court in String, converts to type Surface
     */
    @RequestMapping(value = "/addCourt", method = RequestMethod.POST)
    public void addCourt(String surface){
        Court court = Court.builder().surface(Surface.valueOf(surface)).build();
        courtRepo.save(court);
    }

}
