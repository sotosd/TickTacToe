/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommClasses;

import ticktaktoe.Game;
import ticktaktoe.Player;


/**
 *
 * @author ToyMaker
 */
public class CommunicationMessage {
    
    CommunicationType type;
    
    Player player;
    Game game;
    String specificPlayer;
    int posx;
    int posy;

    public CommunicationMessage(CommunicationType type, Player player, String specificPlayer) {
        this.type = type;
        this.player = player;
        this.specificPlayer = specificPlayer;
    }
    
    
    public CommunicationMessage(CommunicationType type, Player player) {
        this.type = type;
        this.player = player;
    }
    
    public CommunicationMessage(){
   
        
    }

    public CommunicationMessage(CommunicationType type, Game game) {
        this.type = type;
        this.game = game;
    }

    public CommunicationMessage(CommunicationType type, Player player, int posx, int posy) {
        this.type = type;
        this.player = player;
        this.posx = posx;
        this.posy = posy;
    }
    
    
    
    
    public CommunicationType getType() {
        return type;
    }

    public void setType(CommunicationType type) {
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }
  
    
    
}