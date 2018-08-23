/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import graphicInterface.Competition;
import javafx.application.Application;

/**
 *
 * @author Kris
 */
public class main {
    
    public static void main(String[] args){
        LogicTrack lo = new LogicTrack();
        lo.makeLanes();        
        Application.launch(Competition.class, args);
    }
    
}
