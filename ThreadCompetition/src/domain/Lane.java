package domain;

import java.util.*;

/**
 * 
 */
public class Lane {

    /**
     * Default constructor
     */
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

}