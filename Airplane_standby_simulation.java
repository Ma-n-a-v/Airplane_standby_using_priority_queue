/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */
import java.sql.Timestamp;
import java.util.Scanner;

public class Airplane_standby_simulation {
    public static void main(String[] args) {
        PassengerComparator comp = new PassengerComparator();
        HeapPriorityQueue<Passenger, String> standbyList = new HeapPriorityQueue<>(comp);
    
        Scanner kb = new Scanner(System.in);

        //Adding 10 passengers as asked.
        System.out.println("Adding 10 passengers: ");
        for(int i=0; i<10; i++){
            System.out.println("Enter passport no of new Passenger: ");
            String inputPassport = kb.next();
            Fare FareCode = Fare.randomFare();
            FlyerStatus status = FlyerStatus.randomFlyerStatus();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            Passenger p = new Passenger(inputPassport, FareCode, status, time);
            standbyList.insert(p, inputPassport);
            System.out.println(p.toString());
            System.out.println(standbyList.toString());
        }

        //Removing 5 passangers out of priority.
        System.out.println("-----------------------------------------------");
        System.out.println("Removing 5 passengers based on their priority:");
        for (int i=0; i<5; i++){
            Entry<Passenger, String> secondList = standbyList.removeMin();
            if (secondList != null) {
                System.out.println(secondList.getKey().toString());
                System.out.println(standbyList.toString());
            }
        }

        //Adding 5 more Passengers:
        System.out.println("-----------------------------------------------");
        System.out.println("Adding 5 passengers: ");
        for(int i=0; i<5; i++){
            System.out.println("Enter passport no of new Passenger: ");
            String inputPassport = kb.next();
            Fare FareCode = Fare.randomFare();
            FlyerStatus status = FlyerStatus.randomFlyerStatus();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            Passenger p = new Passenger(inputPassport, FareCode, status, time);
            standbyList.insert(p, inputPassport);
            System.out.println(p.toString());
            System.out.println(standbyList.toString());
        }

        //Removing all elements:
        System.out.println("-----------------------------------------------");
        System.out.println("Removing all elements: ");
        while(!(standbyList.isEmpty())){
            Entry<Passenger, String> thirdList = standbyList.removeMin();
            if (thirdList != null) {
                System.out.println("Removed Passenger: " + thirdList.toString());
            }
        }
    }   
}
