package domain;

import java.util.*;

/**
 * This class has the information important of the location of the avatar
 * @author Kristal and Jose
 */
public class Location {

    public Location() {
    }

    private int posX;
    private int posY;
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