/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import graphicInterface.compe;
import javafx.application.Application;

/**
 *
 * @author Kris
 */
public class main {
    
    public static void main(String[] args){
        LogicTrack lo = new LogicTrack();
        lo.makeLanes();        
        Application.launch(compe.class, args);
    }
    
}
