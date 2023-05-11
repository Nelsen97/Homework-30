package nail;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Auto> autos = new Auto().makeListOfAutos();
    private Parking parking = new Parking();
    private final LocalTime beginOfWork = LocalTime.of(9, 0);
    private final LocalTime endOfWOrk = LocalTime.of(21, 0);
    private LocalTime currentTime = LocalTime.of(0, 0);

    public Simulation() {
        for (int i = 0; i < autos.size(); i++) {
            startSimulation(autos.get(i));
            if (autos.get(i).getPaidParkingTime() > 30) {
                printPaymentReceipt(autos.get(i));
            }
        }
        printResult();
    }

    public void printPaymentReceipt(Auto auto) {
        auto.setParkingFee((auto.getTimeOnParking() / 5) * 10);
        String format = "Машина %s за месяц провела на парковке %s минут. Оплата парковки составит %s";
        System.out.println(String.format(format, auto.getRegistrationNumber(), auto.getPaidParkingTime(), auto.getParkingFee()));
    }

    public void startSimulation(Auto auto) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 24; j++) {
                for (int k = 0; k < 60; k += 5) {
                    if (auto.getAutoState() == AutoState.IN_THE_PARKING) {
                        auto.setTimeOnParking(auto.getTimeOnParking() + 5);
                    }
                    if (currentTime.isBefore(endOfWOrk) && currentTime.isAfter(beginOfWork)
                            && auto.getAutoState() == AutoState.IN_THE_PARKING) {
                        auto.setPaidParkingTime(auto.getPaidParkingTime() + 5);
                    }
                    currentTime = currentTime.plusMinutes(5);
                    auto.changeState();
                }
            }
        }
    }

    public void printResult() {
        String format = "Машина: %s всего провела на парковке: %s минут.";
        for (int i = 0; i < autos.size(); i++) {
            System.out.println(String.format(format, autos.get(i).getRegistrationNumber(), autos.get(i).getTimeOnParking()));
        }
    }

//    public void paymentReceipt(Auto auto) {
//            if (auto.getTimeOnParking() > 30) {
//                auto.setParkingFee((auto.getTimeOnParking() / 5) * 10);
//        }
//    }

    public void checkFreeParkingPlace(Auto auto) {
        if (parking.getParkingPlaces() > 0) {
            if (auto.getAutoState() == AutoState.IN_THE_PARKING) {
                parking.setParkingPlaces(parking.getParkingPlaces() - 1);
            }
        } else auto.setAutoState(AutoState.ON_THE_WAY);
    }
}
