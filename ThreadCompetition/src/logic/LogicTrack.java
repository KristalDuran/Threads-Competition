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
            lane.setLineNumber(i + 1);
            listLanes.add(lane);
        }
    }
    
    public Lane defineLane(){
        Lane lane = listLanes.get(0);
        
        for (int posLane = 1; posLane < listLanes.size(); posLane++) {
            if(lane.getListAvatarsByLane().size() > listLanes.get(posLane).getListAvatarsByLane().size()){
                lane = listLanes.get(posLane);
            }
        }
        
        return lane;
    }
    
    
}