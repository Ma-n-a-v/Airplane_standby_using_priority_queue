/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */
import java.sql.Timestamp;


class Passenger {
    private String passportNumber;
    private Fare fareCode;
    private FlyerStatus flyerStatus;
    private Timestamp timestamp;

    public Passenger(String passportNumber, Fare fareCode, FlyerStatus flyerStatus, Timestamp timestamp) {
        this.passportNumber = passportNumber;
        this.fareCode = fareCode;
        this.flyerStatus = flyerStatus;
        this.timestamp = timestamp;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Fare getFareCode() {
        return fareCode;
    }

    public FlyerStatus getFlyerStatus() {
        return flyerStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String toString(){
        return "(Passport: "+ passportNumber +", Fare: "+ fareCode +", FlyerStatus: "+ flyerStatus +", Time: "+timestamp+ ")";
    }
}