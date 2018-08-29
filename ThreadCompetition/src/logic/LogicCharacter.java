package logic;

import domain.Avatar;
import domain.Figure;
import domain.Lane;
import domain.Location;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.VariablesInteface;

/**
 * 
 */
public class LogicCharacter implements VariablesInteface{
    LogicTrack logicTrack = new LogicTrack();
    private boolean isForm = false;
    private boolean isRevert = false;
    private boolean stop = false;
    
    /**
     * Default constructor
     */
    public LogicCharacter() {
    }


    /**
     * With this method the Imagen of the avatar can change, it can is figure or imagen 
     * @param isImage 
     */
    public void changeAvatar(boolean isImage) {
        this.isForm = isImage;
        // TODO implement here
        for (int posAvatar = 0; posAvatar < listAvatars.size(); posAvatar++) {
            listAvatars.get(posAvatar).getFigure().setIsForm(isImage);
            //listAvatars.get(posAvatar).changeDirection();
        }
    }


    /**
     * @param Character 
     * @return
     */
    /*public void changeDirection() {
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
    }*/
    
    /**
     * This method does a change or direction of the avatar
     */
    public void changeDirection() {
        // TODO implement here
        for (int posAvatar = 0; posAvatar < listAvatars.size(); posAvatar++) {
            if(listAvatars.get(posAvatar).getDirection() == true){
                listAvatars.get(posAvatar).setDirection(false);
                isRevert = false;
            }
            else{
                listAvatars.get(posAvatar).setDirection(true);
                isRevert = true;
            }
        }
    }
    
    /**
     * This method stop all avatar that are running
     */
    public void interrup() {
        
        for (int i = 0; i < listAvatars.size(); i++) {
            if (listAvatars.get(i).isStopAvatar()) {
                stop = false;
                listAvatars.get(i).setStopAvatar(false);
            }else{
                listAvatars.get(i).setStopAvatar(true);
                stop = true;
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
                avatar.setStopAvatar(false);
                avatar.setDirection(isRevert);
                avatar.start();
                listAvatars.add(avatar);
            }
        } catch (FileNotFoundException ex) {
                Logger.getLogger(LogicCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * This method set the form of the avatar depending of the case
     * @param pSpeed
     * @return Figure
     */
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
    
    /**
     * This method set the correct location of the avatar 
     * @return 
     */
    public Location setLocation(){
        Location location = new Location();
        Lane lane = logicTrack.defineLane();
        location.setPosX(7 + (lane.getLineNumber()*54));
        if(isRevert){
            location.setPosY(410);
        }else
            location.setPosY(10);
        
        location.setLane(lane);
        return location;
    }
    
    /**
     * This method parse a string to integer
     * @param text
     * @return int
     */
    public int getInteger(String text){
        if(text != null || text != ""){
            return Integer.parseInt(text);
        }
        return 0;
    }
    
    /**
     * This method make the avatars with random speed and account
     */
    public void makeSimulation(){
        int speed = (int) (Math.random() * 3) + 1;
        int value = (int) (Math.random() * 11) + 1;
        makeAvatars(speed, value);
    }
    
    /**
     * This method control the time with the purpose of make many avatar in different times
     */
    public void simulation(){
        
        makeSimulation();
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable simulation = new Runnable() {
            public void run() { 
                if(stop == false){
                    makeSimulation();
                } 
            } 
        };
        
        ScheduledFuture<?> simulationHandle = scheduler.scheduleAtFixedRate(simulation, 10, 10, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() { simulationHandle.cancel(true); }
        }, 60 * 60, SECONDS);
    }
    
}