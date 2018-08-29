package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import utility.VariablesInteface;

/**
 * This class has information about the avatar and also extends Thread and it has important run method
 * @author Kristal and Jose
 */
public class Avatar extends Thread implements VariablesInteface{

    public Avatar(){
    }

    private int speed;
    private Location location;
    private int direction = 1;
    private boolean isRevert;
    private Figure figure;
    private Image image;
    private ArrayList<Image> sprite = new ArrayList<>();
    private boolean stopAvatar = false;
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean getDirection() {
        return isRevert;
    }

    public void setDirection(boolean direction) {
        this.isRevert = direction;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
    
    public boolean isStopAvatar() {
        return stopAvatar;
    }

    public void setStopAvatar(boolean stopAvatar) {
        this.stopAvatar = stopAvatar;
    }
    
    public ArrayList<Image> getSprite() {
        return sprite;
    }
    
    public void changeDirection(){
        this.direction = this.direction * -1;
    }
    
    public void setSprite() throws FileNotFoundException{
        this.sprite.add(new Image(new FileInputStream(this.figure.getDirectionImage())));
        this.sprite.add(new Image(new FileInputStream(this.figure.getDirectionImageRevers())));
        this.sprite.add(new Image(new FileInputStream(this.figure.getDirectionForm())));
    }
    
    @Override
    public void run(){
        
        int wait = 100/speed;
        int lineNumber = getLocation().getLane().getLineNumber() - 1;
        int indexInLine;
                
        while(true) {
            try {                
                while(this.getLocation().getPosY() <= 410 && this.getLocation().getPosY() >= 9){
                    Thread.sleep(wait);
                    indexInLine = getIndexInLane();
                    if(!stopAvatar){
                        if((listLanes.get(lineNumber).getListAvatarsByLane().size() - 1) > indexInLine){
                            if(Math.abs(listLanes.get(lineNumber).getListAvatarsByLane().get(indexInLine + 1).getLocation().getPosY() - this.getLocation().getPosY()) < 83){
                                continue;
                            }
                        }
                        if(listLanes.get(lineNumber).isIsBarrier() && Math.abs((this.location.getPosY() + this.direction) - 205) <= 0){
                            continue;
                        }
//                        this.getLocation().setPosY( this.getLocation().getPosY() + this.direction);
                    Thread.sleep(wait);
                        if (this.isRevert == true) {
                            location.setPosY(location.getPosY() - 1);
                            changeImagen();
                        }else{
                            location.setPosY(location.getPosY() + 1);
                            changeImagen();
                        }
                    }else{
                        Thread.sleep(wait);
                    }
                }
                this.location.getLane().getListAvatarsByLane().remove(getIndexInLane());
                this.figure.setImage(null);
                break;
            } catch (InterruptedException ex) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Throwable ex) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    /**
     * This method change the imagen betown car and form
     */
    public void changeImagen(){
        if (figure.isIsForm()) {
            figure.setImage(sprite.get(2));
        }else{
            if(this.isRevert == true){
                figure.setImage(sprite.get(1));
            }else{
                figure.setImage(sprite.get(0));
            }
        }
    }
    
    /**
     * This method return the index of the lane in the array 
     * @return int
     */
    private int getIndexInLane(){
        int myListIndex = this.getLocation().getLane().getLineNumber() - 1;
        for(int i = listLanes.get(myListIndex).getListAvatarsByLane().size() -1; i >= 0; i--){
            if(listLanes.get(myListIndex).getListAvatarsByLane().get(i).getLocation().getPosX() == getLocation().getPosX() 
                    && listLanes.get(myListIndex).getListAvatarsByLane().get(i).getLocation().getPosY() == getLocation().getPosY()){
                return i;
            }
        }
        return -1;
    }
    
}