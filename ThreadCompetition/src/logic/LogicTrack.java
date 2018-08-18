package logic;

import domain.Lane;
import java.util.*;
import utility.VariablesInteface;

/**
 * 
 */
public class LogicTrack implements VariablesInteface{

    /**
     * Default constructor
     */
    public LogicTrack() {
    }



    /**
     * @return
     */
    public void changeDirection() {
        // TODO implement here
      
    }

    /**
     * @return
     */
    public void interrupt() {
        // TODO implement here

    }

    /**
     * @param int [*] 
     * @return
     */
    public void makeBarrier(ArrayList<Integer> pBarriers) {
        // TODO implement here
    }

    
    public void makeLanes(){
        for (int i = 0; i < 11; i++) {
            Lane lane = new Lane();
            lane.setLineNumber(i+1);
            listLanes.add(lane);
        }
    }
    
    public Lane defineLane(){
        int totalAvatars = listAvatars.size();
                
        Lane lane = null;
        if ( totalAvatars < 11 && totalAvatars >= 0 ) {
            lane = listLanes.get(totalAvatars);
        }else{
            if (totalAvatars >= 11 && totalAvatars <=100) {
                int position = (int) totalAvatars/11;
                lane = listLanes.get(position);
            }else{
                
            }
        }
        return lane;
    }
    
    
}