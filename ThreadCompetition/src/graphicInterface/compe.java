/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicInterface;


import domain.*;
import java.awt.Component;
import java.awt.Panel;
import utility.Variables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.LogicCharacter;
import utility.VariablesInteface;

public class compe extends Application implements Runnable, VariablesInteface{

    private LogicCharacter logicCharacter = new LogicCharacter();   
    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    private Button btnCreat;
    private Button btnBarrier;
    private Button btnRevert;
    private Button btnSimulation;
    private Button btnInterrupt;
    private TextArea txtSpeed;
    private TextArea txtValues;
    private TextArea txtLanes;
    private Group root;
    private Avatar avatar;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Threads Competition");
        initComponents(primaryStage);
        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
    }

    @Override
    public void run() {
        
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000/fps;

        while(true){
            try {
                start=System.nanoTime();
                elapsed=System.nanoTime()-start;                    
                wait = time-elapsed/1000000;
                Thread.sleep(wait);
                GraphicsContext gc = this.canvas.getGraphicsContext2D();
                draw(gc);
            } 
            catch (InterruptedException ex) {}
        }
    }

    private void initComponents(Stage primaryStage) {
        try {
            
            this.root = new Group();
            this.pane = new Pane();
            this.scene = new Scene(this.pane, Variables.WIDTH, Variables.HEIGHT);
            this.canvas = new Canvas(Variables.WIDTH, Variables.HEIGHT);
            this.image = new Image(new FileInputStream("src/imgs/Track.JPG"));
            this.pane.getChildren().add(this.canvas);
            primaryStage.setScene(this.scene);            
            this.btnCreat = new Button("Create");
            this.btnBarrier = new Button("Barrier");
            this.btnRevert = new Button("Revert");
            this.btnSimulation = new Button("Simulation");
            this.btnInterrupt = new Button("Interrupt");
            this.txtLanes = new TextArea();
            this.txtSpeed = new TextArea();
            this.txtValues = new TextArea();
            
            setDetails(btnCreat, null,80, 600, 190,30);
            setDetails(btnBarrier, null, 300, 600, 100, 30);
            setDetails(btnRevert, null, 410, 600, 100, 30);
            setDetails(btnSimulation, null, 520, 600, 100, 30);
            setDetails(btnInterrupt, null, 630, 550, 100, 80);
            setDetails(null, txtSpeed, 70, 550, 100, 10);
            setDetails(null, txtValues, 180, 550, 100, 10);
            setDetails(null, txtLanes, 300, 550, 100, 10);
            
            root.getChildren().add(btnCreat);
            root.getChildren().add(btnBarrier);
            root.getChildren().add(btnRevert);
            root.getChildren().add(btnSimulation);
            root.getChildren().add(btnInterrupt);
            root.getChildren().add(txtSpeed);
            root.getChildren().add(txtValues);
            root.getChildren().add(txtLanes);
                        
            //scene.setRoot(root);
            
            btnCreat.getOnMouseClicked();
            btnCreat.setOnMouseClicked((event) -> {
                logicCharacter.makeAvatars(Integer.parseInt(txtSpeed.getText()),
                        Integer.parseInt(txtValues.getText()));
            });
            
            logicCharacter.makeAvatars(1,3);
            logicCharacter.makeAvatars(2,4);//esto es para prueba mientras se agrega los botones 
            logicCharacter.makeAvatars(3,4);
            
            this.thread = new Thread(this);
            this.thread.start();
        } 
        catch (FileNotFoundException ex){}
        catch (BufferOverflowException ex){}
    }

    private void draw(GraphicsContext gc){
        
        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
        gc.drawImage(this.image, 110, 10); 
        
        for (int i = 0; i < listAvatars.size(); i++) {
            System.out.println("i "+i);
            String img =  listAvatars.get(i).getFigure().getDirectionImage();
            try {
                listAvatars.get(i).getFigure().setImage(new Image(new FileInputStream(img)));
                Image imageAvatar = listAvatars.get(i).getFigure().getImage();
                gc.drawImage(imageAvatar, listAvatars.get(i).getLocation().getPosX(),listAvatars.get(i).getLocation().getPosY());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(compe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
    
    public void setDetails(Button button, TextArea txt, int x, int y, int width, int heigh){
        if (button != null) {
            button.setPrefHeight(heigh);
            button.setPrefWidth(width);
            button.setTranslateX(x);
            button.setTranslateY(y);
        }else{
            txt.setPrefHeight(heigh);
            txt.setPrefWidth(width);
            txt.setTranslateX(x);
            txt.setTranslateY(y);
        }
    }
}

