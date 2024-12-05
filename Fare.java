/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */
import java.util.Random;

public enum Fare {
    Full, Disc, Buddy;

    public static Fare randomFare(){
        Random rand = new Random();
        int randNumber = rand.nextInt(3);
        
        switch(randNumber){
            case 0:
                return Full;
            
            case 1:
                return Disc;
            
            case 2:
                return Buddy;

            default:
                throw new IllegalArgumentException("Invalid input: "+randNumber);
        }
    }
}
