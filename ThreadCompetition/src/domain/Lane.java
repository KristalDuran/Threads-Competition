package domain;

import java.util.*;

/**
 * This class has information about the lane of the track
 * @author Kristal and Jose
 */
public class Lane {

    private boolean isBarrier = false; //this atribut show if this lane has a barrir 
    private int lineNumber; //this atribut show the number of the lane
    private ArrayList<Avatar> listAvatarsByLane = new ArrayList<>(); //this array has all avatars that are here
    
    public Lane() {
    }

    public Lane(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ArrayList<Avatar> getListAvatarsByLane() {
        return listAvatarsByLane;
    }

    public boolean isIsBarrier() {
        return isBarrier;
    }

    public void setIsBarrier(boolean isBarrier) {
        this.isBarrier = isBarrier;
    }  

}