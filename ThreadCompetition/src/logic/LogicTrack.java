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
     * This method make eleven lanes of the track 
     */
    public void makeLanes(){
        for (int i = 0; i < 11; i++) {
            Lane lane = new Lane();
            lane.setLineNumber(i + 1);
            listLanes.add(lane);
        }
    }
    
    /**
     * This method looks the correct lane of the panel
     * @return Lane
     */
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