package tennisClub.utils;

import tennisClub.diagram.Court;
import tennisClub.diagram.Customer;
import tennisClub.diagram.Reservation;
import tennisClub.repo.CustomerRepo;
import tennisClub.repo.ReservationRepo;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Matus Jakab
 */
public class Utils {

    /**
     * checks if start time is before end time
     * checks if court specified by id is in our database
     * checks if there is no time conflict in database
     * checks if the customer is in our database, if not saves him
     * checks if customer's number isn't associated with different name in database
     * @param customerName name of customer that made the order
     * @param customerPhoneNumber phone number of customer that made the order
     * @param startTime start time of the ordered game
     * @param endTime end time of the ordered game
     * @param court court the game will be played on
     * @param reservationRepo reservation repository
     * @param customerRepo customer repository
     * @return false if the reservation cannot be created, otherwise true
     */
    public static boolean validateInput(String customerName, String customerPhoneNumber,
                                 Timestamp startTime, Timestamp endTime, Court court,
                                 ReservationRepo reservationRepo, CustomerRepo customerRepo){
        Customer customer = customerRepo.findAllByPhoneNumber(customerPhoneNumber);

        if (startTime.after(endTime)){
            return false;
        }

        if (court == null){
            return false;
        }

        List<Reservation> reservations = reservationRepo.findAllByCourtId(court.getId());
        for (Reservation reservation : reservations){
            if (reservationRepo.existsByStartTimeIsBeforeAndIdEquals
                    (endTime, reservation.getId())
                    && reservationRepo.existsByEndTimeIsAfterAndIdEquals
                    (startTime, reservation.getId())){
                return false;
            }
        }

        if (customer == null){
            customer = Customer.builder().name(customerName).phoneNumber(customerPhoneNumber).build();
            customerRepo.save(customer);
        } else return customer.getName().equals(customerName);

        return true;
    }

    /**
     * validates customer's phone number
     * it has to start with country preset, that means it has to start with '+', followed by 12 digits
     * @param phoneNumber phone number customer provided
     * @return true if the phone number is correct, otherwise false
     */
    public static boolean validatePhoneNumber(String phoneNumber){
        if (phoneNumber.equals("")){
            return false;
        }

        if (phoneNumber.charAt(0) != '+'){
            return false;
        }

        return phoneNumber.substring(1).matches("\\d{12,}+");
    }

    /**
     * validates customer's name
     * it has to contain only letters and spaces and has to be 3 to 29 characters long
     * @param name name customer provided
     * @return true if the name is correct, otherwise false
     */
    public static boolean validateName(String name){
        return name.strip().matches("^[A-Za-z ]{3,29}$");
    }

    /**
     * calculates price of the game
     * @param startTime time of game start
     * @param endTime time of game end
     * @param doubles true if the game is 2 versus 2, false if 1 versus 1
     * @param court court on which the game will be played
     * @return price of reservation determined by time, surface and if the game is doubles
     */
    public static double calculatePrice(Timestamp startTime, Timestamp endTime, boolean doubles, Court court){
        long timeInMinutes = (endTime.getTime() - startTime.getTime())/60000;
        double pricePerMinute = court.getSurface().getPrice();
        double doublesPriceIncrease = 1;
        if (doubles){
            doublesPriceIncrease = 1.5;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        double finalPrice = timeInMinutes * pricePerMinute * doublesPriceIncrease;
        return Double.parseDouble(df.format(finalPrice));
    }
}
