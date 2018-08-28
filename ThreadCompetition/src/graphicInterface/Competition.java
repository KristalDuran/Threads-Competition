/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicInterface;

import domain.Avatar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.LogicCharacter;
import utility.Variables;
import utility.VariablesInteface;
import static utility.VariablesInteface.listAvatars;

/**
 *
 * @author Kris
 */
public class Competition extends Application implements Runnable, VariablesInteface{

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
    private TextField txtSpeed;
    private TextField txtValues;
    private TextField txtLanes;
    private Group root;
    private Avatar avatar;

    GraphicsContext gc;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Threads Competition");
        initComponents(primaryStage);
        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
    }

    @Override
    public void run() {
        
        long start = System.nanoTime();
        long elapsed = System.nanoTime()-start;
        int fps = 60;
        long time = 1000 / fps;
        long wait = time - elapsed / 1000000;
        
        while(true){
            try {
                Thread.sleep(wait);
                draw(gc);
            } 
            catch (InterruptedException ex) {}
        }
    }

    private void initComponents(Stage primaryStage) throws FileNotFoundException {
        try {
            //pintar{
            canvas = new Canvas(703, 600);
            gc = canvas.getGraphicsContext2D();
            //}
            
            //paneles{
            StackPane pane = new StackPane();
            Scene scene = new Scene(pane, 703, 600);
            GridPane grid = new GridPane();
            //}
            
            //botones{
            this.btnCreat = new Button("Create");
            this.btnBarrier = new Button("Barrier");
            this.btnRevert = new Button("Revert");
            this.btnSimulation = new Button("Simulation");
            this.btnInterrupt = new Button("Interrupt");
            //}
            
            //txt{
            this.txtLanes = new TextField();
            this.txtSpeed = new TextField();
            this.txtValues = new TextField();
            
            this.txtLanes.setPromptText("carriles");
            this.txtSpeed.setPromptText("velocidad");
            this.txtValues.setPromptText("cantidad");
            //}

            grid.addRow(0, txtSpeed, txtValues, btnBarrier, btnRevert, btnInterrupt);
            grid.addRow(1, btnCreat, txtLanes, btnSimulation);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10, 10, 10, 10));

            grid.setAlignment(Pos.BOTTOM_CENTER);

            this.btnCreat.setOnMouseClicked((event)->{
                int speed = logicCharacter.getInteger(txtSpeed.getText());
                int values = logicCharacter.getInteger(txtValues.getText());
                logicCharacter.makeAvatars(speed, values);
            });
            
            this.btnBarrier.setOnMouseClicked((event)->{
                //colocar barrera
                //lo que se ingrese seria una lista de numeros, manejar eso bien
                //logicCharacter.
            });
            
            this.btnRevert.setOnMouseClicked((event)->{
                logicCharacter.changeDirection();
                logicCharacter.invertArrays();
            });
            
            this.btnInterrupt.setOnMouseClicked((event)->{
                
            });
            
            this.btnSimulation.setOnMouseClicked((event)->{
                //llamar a metodo simulacion 
            });
            
            this.image = new Image(new FileInputStream("src/imgs/Track.JPG"));
            gc.drawImage(this.image , 50, 0);
            
            pane.getChildren().addAll(canvas, grid);
            
            
            primaryStage.setScene(scene);    
            
            this.thread = new Thread(this);
            this.thread.start();
            
        } 
        catch (BufferOverflowException ex){}
    }

    private void draw(GraphicsContext gc){
        
        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
        gc.drawImage(this.image, 50, 10); 
        Avatar current;
        for (int i = 0; i < listAvatars.size(); i++) {
            //System.out.println("i "+i);
            current = listAvatars.get(i);
            Image img =  current.getFigure().getImage();
            current.getFigure().setImage(img);
            gc.drawImage(img, current.getLocation().getPosX(), current.getLocation().getPosY());
        }
    }
    
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
    
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };

}




/*
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
*/
