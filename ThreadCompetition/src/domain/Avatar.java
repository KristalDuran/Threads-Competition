package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import utility.VariablesInteface;

/**
 * 
 */
public class Avatar extends Thread implements VariablesInteface{

    public Avatar(){
    }

    private Speed speed;
    private Location location;
    private boolean isRevert;
    private Figure figure;
    private Image image;
    private ArrayList<Image> sprite = new ArrayList<>();
    
    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
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
    
    public ArrayList<Image> getSprite() {
        return sprite;
    }
    
    public void setSprite() throws FileNotFoundException{
        this.sprite.add(new Image(new FileInputStream(this.figure.getDirectionImage())));
        this.sprite.add(new Image(new FileInputStream(this.figure.getDirectionImageRevers())));
        this.sprite.add(new Image(new FileInputStream(this.figure.getDirectionForm())));
    }
    
    @Override
    public void run(){
        
        int wait = 100/speed.getSpeed();
        int lineNumber = getLocation().getLane().getLineNumber() - 1;
        int indexInLine = getIndexInLane();
        
        System.out.println("my line number: " + lineNumber + "  my index: " + indexInLine);
        
        while(true) {
            try {
                while ((location.getPosY() <= 410 && location.getPosY() >= 9) || (this.isRevert == true && location.getPosY() >= 10)) {
                    System.out.println("y "+location.getPosY());
                    Thread.sleep(wait);
<<<<<<< HEAD
                    indexInLine = getIndexInLane();
                    if((listLanes.get(lineNumber).getListAvatarsByLane().size() - 1) > indexInLine){
                        if(Math.abs(listLanes.get(lineNumber).getListAvatarsByLane().get(indexInLine + 1).getLocation().getPosY() - this.getLocation().getPosY()) < 15){
                            System.out.println("compara velocidad: " + this.getSpeed().getSpeed() + " con velocidad: " + listLanes.get(lineNumber).getListAvatarsByLane().get(indexInLine + 1).getSpeed().getSpeed());
                            continue;
                        }
                    }
                    
                    
                    if (this.isRevert) {
                        location.setPosY(location.getPosY() + 1);
                        //changeImagen();
                    }else{
                        location.setPosY(location.getPosY() - 1);
                        //changeImagen();
=======
                    if (this.isRevert == true) {
                        location.setPosY(location.getPosY() - 1);
                        changeImagen();
                    }else{
                        location.setPosY(location.getPosY() + 1);
                        changeImagen();
>>>>>>> 0b8a8c7a3b60b5524491546f254b8317d696ea01
                    }
                }
                this.figure.setImage(null);
                this.finalize();
            } catch (InterruptedException ex) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Throwable ex) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    
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
    
    private void changeImagen(){
        if (figure.isIsForm()) {
            //poner imagen 
            figure.setImage(sprite.get(2));
        }else{
            if(this.isRevert == true){
                //set bien
                figure.setImage(sprite.get(1));
            }else{
                //set alverris
                figure.setImage(sprite.get(0));
            }
        }
            
    }
}