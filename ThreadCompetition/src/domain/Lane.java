package domain;

import java.util.*;

/**
 * 
 */
public class Lane {

    /**
     * Default constructor
     */
    
    private boolean isBarrier = false;
    
    public Lane() {
    }

    /**
     * 
     */
    private int lineNumber;
    private ArrayList<Avatar> listAvatarsByLane = new ArrayList<>();
    
    
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