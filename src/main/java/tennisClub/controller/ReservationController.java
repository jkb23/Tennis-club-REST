package tennisClub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tennisClub.diagram.Court;
import tennisClub.diagram.Reservation;
import tennisClub.repo.CourtRepo;
import tennisClub.repo.CustomerRepo;
import tennisClub.repo.ReservationRepo;
import tennisClub.utils.Utils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Matus Jakab
 */

@RestController
public class ReservationController {

    final
    ReservationRepo reservationRepo;

    final
    CourtRepo courtRepo;

    final
    CustomerRepo customerRepo;

    public ReservationController(ReservationRepo reservationRepo, CourtRepo courtRepo, CustomerRepo customerRepo) {
        this.reservationRepo = reservationRepo;
        this.courtRepo = courtRepo;
        this.customerRepo = customerRepo;
    }

    /**
     * REST endpoint to see list of reservations of specific court
     * @param id id of court we want to find all reservations for
     * @return list of reservations of specific court
     */
    @RequestMapping(value = "/res_by_court_id", method = RequestMethod.GET)
    @ResponseBody
    public List<Reservation> getReservationsByCourtId(@RequestParam("courtId") Long id){
        return reservationRepo.findAllByCourtId(id);
    }

    /**
     * REST endpoint to see list of reservations of specific customer phone number
     * @param num customer phone number
     * @return list of reservations for specific phone number
     */
    @RequestMapping(value = "/res_by_customer_phone", method = RequestMethod.GET)
    @ResponseBody
    public List<Reservation> getReservationByCustomerPhone(@RequestParam("customerPhoneNumber") String num){
        return reservationRepo.findAllByCustomerPhoneNumber(num);
    }

    /**
     * REST endpoint to create new reservation
     * @param courtId id of court to play on
     * @param doubles true if the game is 2 versus 2, false if 1 versus 1
     * @param customerName name of customer making order
     * @param customerPhoneNumber phone number of customer making order
     * @param start start time of game in string, coverts to type Timestamp
     * @param end end time of game in string, coverts to type Timestamp
     *            both have to be in format yyyy-[m]m-[d]d hh:mm:ss[.f...]
     * @return price of reservation determined by time, surface and if the game is doubles
     */
    @RequestMapping(value = "/addReservation", method = RequestMethod.POST)
    @ResponseBody
    public double addReservation(Long courtId, boolean doubles, String customerName, String customerPhoneNumber,
                                 String start, String end){
        Timestamp startTime = Timestamp.valueOf(start);
        Timestamp endTime = Timestamp.valueOf(end);
        Court court = courtRepo.findAllById(courtId);

        if (!Utils.validatePhoneNumber(customerPhoneNumber) || !Utils.validateName(customerName)){
            return -1;
        }

        if (!Utils.validateInput(customerName, customerPhoneNumber, startTime, endTime, court,
                reservationRepo, customerRepo)){
            return -1;
        }

        Reservation reservation = Reservation.builder()
                .doubles(doubles)
                .courtId(courtId)
                .customerPhoneNumber(customerPhoneNumber)
                .startTime(startTime)
                .endTime(endTime).build();

        reservationRepo.save(reservation);

        return Utils.calculatePrice(startTime, endTime, doubles, court);
    }
}
