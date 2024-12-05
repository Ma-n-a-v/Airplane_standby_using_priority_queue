/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */
import java.util.Comparator;

public class PassengerComparator implements Comparator<Passenger>{
    public int compare(Passenger p1, Passenger p2){
        if(p1.getFareCode() != p2.getFareCode()){
            return p1.getFareCode().compareTo(p2.getFareCode());
        }
        else if(p1.getFlyerStatus() != p2.getFlyerStatus()){
            return p1.getFlyerStatus().compareTo(p2.getFlyerStatus());
        }
        else{
            return p1.getTimestamp().compareTo(p2.getTimestamp());
        }
    }
}
