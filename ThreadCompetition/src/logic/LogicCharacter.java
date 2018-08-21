package logic;

import domain.Avatar;
import domain.Direction;
import domain.Figure;
import domain.Lane;
import domain.Location;
import domain.Speed;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.VariablesInteface;

/**
 * 
 */
public class LogicCharacter implements VariablesInteface{
    LogicTrack logicTrack = new LogicTrack();
    /**
     * Default constructor
     */
    public LogicCharacter() {
    }


    /**
     * @param Character 
     * @return
     */
    public void changeAvatar(Character pCharacter) {
        // TODO implement here
    }

    /**
     * @param Character 
     * @return
     */
    public void changeDirection(Character pCharacter) {
        // TODO implement here
      
    }

    /**
     * @param Character 
     * @return
     */
    public void changeSpeed(Character pCharacter) {
        // TODO implement here
        
    }

    /**
     * @param Character 
     * @return
     */
    public void changeLocation(Character pCharacter) {
        // TODO implement here

    }

    
    public void makeAvatars(int pSpeed, int pValues){ 
        System.out.println("Creando avatars");
        try {
            for (int cantAvatars = 0; cantAvatars < pValues; cantAvatars++) {            
                Avatar avatar;
                avatar = new Avatar();
                Figure figure = setForm(pSpeed);
                avatar.setDirection(new Direction());
                avatar.setFigure(figure);
                avatar.setSpeed(new Speed(pSpeed));
                avatar.setLocation(setLocation());
                avatar.getLocation().getLane().getListAvatarsByLane().add(avatar);
                avatar.setSprite();
                avatar.start();
                listAvatars.add(avatar);
            }
        } catch (FileNotFoundException ex) {
                Logger.getLogger(LogicCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public Figure setForm(int pSpeed){
        Figure figure = new Figure();
        switch (pSpeed) {
            case 1:
                figure.setDirectionImage("src/imgs/blueCar.png");
                figure.setDirectionImageRevers("src/imgs/blueCarRevers.png");
                figure.setDirectionForm("src/imgs/blueFigure.png");
            return figure;
            case 2:
                figure.setDirectionImage("src/imgs/yellowCar.png");
                figure.setDirectionImageRevers("src/imgs/yellowCarRever.png");
                figure.setDirectionForm("src/imgs/yellowFigure.png");
            return figure;
            case 3:
                figure.setDirectionImage("src/imgs/redCar.png");
                figure.setDirectionImageRevers("src/imgs/redCarRevers.png");
                figure.setDirectionForm("src/imgs/redFigure.png");
            return figure;
        }
        return figure;
    }
    
    public Location setLocation(){
        Location location = new Location();
        location.setPosY(0);
        Lane lane = logicTrack.defineLane();

        location.setPosX(10 + (lane.getLineNumber()*40) + (lane.getLineNumber()*12+(listAvatars.size()*2)));
        location.setLane(lane);
        return location;
    }
}