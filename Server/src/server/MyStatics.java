/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import SharedClasses.Game;
import SharedClasses.Player;
import java.util.ArrayList;


/**
 *
 * @author ToyMaker
 */
public class MyStatics {
  
    public static int port = 100;
    
    public static ArrayList<Player> players = new ArrayList<>(); 
    public static ArrayList<Game> games = new ArrayList<>(); 
    
    public static int playerCount = 0;
    public static int gamesCount = 0;
    
}
