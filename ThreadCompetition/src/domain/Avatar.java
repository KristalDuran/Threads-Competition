package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * 
 */
public class Avatar extends Thread{

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
        
        while(true) {
            try {
                while (location.getPosY() <= 410 || (this.isRevert && location.getPosY() > 0)) {
                    Thread.sleep(wait);
                    if (this.isRevert) {
                        location.setPosY(location.getPosY() + 1);
                        changeImagen();
                    }else{
                        location.setPosY(location.getPosY() - 1);
                        changeImagen();
                    }
                }
                this.finalize();
            } catch (InterruptedException ex) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Throwable ex) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    private void changeImagen(){
        if (figure.isIsForm()) {
            //poner imagen 
            figure.setImage(sprite.get(2));
        }else{
            if(this.isRevert){
                //set bien
                figure.setImage(sprite.get(0));
            }else{
                //set alverris
                figure.setImage(sprite.get(1));
            }
        }
            
    }
}