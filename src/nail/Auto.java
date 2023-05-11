package nail;

import java.time.Duration;
import java.util.*;

public class Auto {
    private AutoState autoState;
    private HashSet<String> registrationNumber = new HashSet<>();
    private Integer id;
    private Integer timeOnParking;
    private Integer paidParkingTime;
    private Integer parkingFee;
    private static Integer counter = -1;
    Parking parking = new Parking();

    public Auto() {
        autoState = AutoState.ON_THE_WAY;
        initiateRegistrationNumber();
        id = ++counter;
        timeOnParking = 0;
        paidParkingTime = 0;
        parkingFee = 0;
    }

    public void changeState() {
        int parkingChance = new Random().nextInt(100) + 1;
        int goFromParkingChance = new Random().nextInt(100) + 1;
        if (parkingChance <= 3) {
            if (parking.getParkingPlaces() > 0) {
                if (getAutoState() == AutoState.ON_THE_WAY) {
                    setAutoState(AutoState.IN_THE_PARKING);
                    parking.setParkingPlaces(parking.getParkingPlaces() - 1);
                } else if (getAutoState() == AutoState.IN_THE_PARKING) {
                    setAutoState(AutoState.ON_THE_WAY);
                    parking.setParkingPlaces(parking.getParkingPlaces() + 1);
                }
            }
        }
    }

    private void initiateRegistrationNumber() {
        this.registrationNumber.add(generateRandomNumber());
    }

    private String generateRandomNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }

    public List<Auto> makeListOfAutos() {
        List<Auto> autos = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            autos.add(new Auto());
        }
        return autos;
    }

    public void printAuto() {
        String format = "Id = %d, number = %s";
        System.out.println(String.format(format, id, registrationNumber));
    }

    public void setAutoState(AutoState autoState) {
        this.autoState = autoState;
    }

    public Integer getTimeOnParking() {
        return timeOnParking;
    }

    public void setTimeOnParking(Integer timeOnParking) {
        this.timeOnParking = timeOnParking;
    }

    public AutoState getAutoState() {
        return autoState;
    }

    public HashSet<String> getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(Integer parkingFee) {
        this.parkingFee = parkingFee;
    }

    public Integer getPaidParkingTime() {
        return paidParkingTime;
    }

    public void setPaidParkingTime(Integer paidParkingTime) {
        this.paidParkingTime = paidParkingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return autoState == auto.autoState && registrationNumber.equals(auto.registrationNumber) && id.equals(auto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoState, registrationNumber, id);
    }

    @Override
    public String toString() {
        return autoState + " " + registrationNumber + getTimeOnParking() + "\n";
    }
}
