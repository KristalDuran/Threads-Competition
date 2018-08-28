package logic;

import domain.Avatar;
import domain.Figure;
import domain.Lane;
import domain.Location;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
        for (int posAvatar = 0; posAvatar < listAvatars.size(); posAvatar++) {
            if(listAvatars.get(posAvatar).getFigure().isIsForm()){
                listAvatars.get(posAvatar).getFigure().setIsForm(false);
            }
            else{
                listAvatars.get(posAvatar).getFigure().setIsForm(true);
            }
        }
    }

    /**
     * @param Character 
     * @return
     */
    public void changeDirection() {
        // TODO implement here
        for (int posAvatar = 0; posAvatar < listAvatars.size(); posAvatar++) {
            if(!(listAvatars.get(posAvatar).getFigure().getImage() == null)){
                listAvatars.get(posAvatar).changeDirection();
                listAvatars.get(posAvatar).changeImagen();
            }
            else{
                listAvatars.remove(posAvatar);
            }
        }
    }
    
    public void invertArrays(){
        for(int i = listLanes.size() -1; i >= 0; i--){
            Collections.reverse(listLanes.get(i).getListAvatarsByLane());
        }
    }
    
    private int[] stringToIntArray(String ints){
         
        String[] inputNumber =  ints.split(",");
        
        int number[] = new int[11];
        
        for(int i=0; i<number.length;i++){
            if(i < inputNumber.length){
                number[i] = Integer.parseInt(inputNumber[i]);
            }
            else{
                number[i] = -1;
            }
        }
        
        return number;
        
    }
    
    public void setBarrier(String ints){
            
        if(ints.length() == 0){
            for(int i = listLanes.size() -1; i >= 0; i--){
                listLanes.get(i).setIsBarrier(!listLanes.get(i).isIsBarrier());
            }        }
        else{
            int number[] = stringToIntArray(ints);
            for(int i = number.length - 1; i >= 0; i--){
                if(number[i] != -1){
                    listLanes.get(number[i]).setIsBarrier(!listLanes.get(number[i]).isIsBarrier());
                }
            }
        }
    }

    /**
     * @param Character 
     * @return
     */
    public void changeSpeed(Character pCharacter) {
        // TODO implement here
        
    } 

    
    public void makeAvatars(int pSpeed, int pValues){ 
        System.out.println("Creando avatars");
        
        try {
            for (int cantAvatars = 0; cantAvatars < pValues; cantAvatars++) {            
                Avatar avatar;
                avatar = new Avatar();
                Figure figure = setForm(pSpeed);
                avatar.setDirection(false);
                avatar.setFigure(figure);
                avatar.setSpeed(pSpeed);
                avatar.setLocation(setLocation());
                avatar.getLocation().getLane().getListAvatarsByLane().add(0, avatar);
                avatar.setSprite();
                avatar.changeImagen();
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
                figure.setDirectionImageRevers("src/imgs/blueCarRever.png");
                figure.setDirectionForm("src/imgs/blueFigure.png");
            return figure;
            case 2:
                figure.setDirectionImage("src/imgs/yellowCar.png");
                figure.setDirectionImageRevers("src/imgs/yellowCarRever.png");
                figure.setDirectionForm("src/imgs/yellowFigure.png");
            return figure;
            case 3:
                figure.setDirectionImage("src/imgs/redCar.png");
                figure.setDirectionImageRevers("src/imgs/redCarRever.png");
                figure.setDirectionForm("src/imgs/redFigure.png");
            return figure;
        }
        return figure;
    }
    
    public Location setLocation(){
        Location location = new Location();
        location.setPosY(10);
        Lane lane = logicTrack.defineLane();
        location.setPosX(10 + (lane.getLineNumber()*54));
        location.setLane(lane);
        return location;
    }
    
    public int getInteger(String text){
        if(text != null || text != ""){
            return Integer.parseInt(text);
        }
        return 0;
    }
    
}