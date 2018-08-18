package domain;

import java.util.*;

/**
 * 
 */
public class Location {

    /**
     * Default constructor
     */
    public Location() {
    }

    /**
     * 
     */
    private int posX;

    /**
     * 
     */
    private int posY;

    /**
     * 
     */
    private Lane lane;

    public Location(int posX, int posY, Lane lane) {
        this.posX = posX;
        this.posY = posY;
        this.lane = lane;
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    

}