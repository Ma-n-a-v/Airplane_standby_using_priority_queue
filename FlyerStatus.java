import java.util.Random;

/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */

public enum FlyerStatus {
    Gold, Silver, Bronze, None;

    public static FlyerStatus randomFlyerStatus(){
        Random rand = new Random();
        int randNumber = rand.nextInt(4);

        switch(randNumber){
            case 0:
                return Gold;
            
            case 1:
                return Silver;
            
            case 2:
                return Bronze;

            case 3:
                return None;
                
            default:
                throw new IllegalArgumentException("Invalid input: "+randNumber);
        }
    }
}
