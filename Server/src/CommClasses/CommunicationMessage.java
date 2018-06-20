/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommClasses;

import SharedClasses.Game;
import SharedClasses.Player;


/**
 *
 * @author ToyMaker
 */
public class CommunicationMessage {
    
    CommunicationType type;
    
    Game game;
    Player player;
    String specificPlayer;
    
    int posx;
    int posy;
            
    
    public CommunicationMessage(){
        
    }
    
    public CommunicationMessage(CommunicationType type, Player p){
        this.player = player;
        this.type = type;
    }

    public CommunicationMessage(CommunicationType communicationType, Game g) {
        this.game = g;
        this.type = communicationType;
    }

    public CommunicationMessage(CommunicationType type, Player player, String specificPlayer) {
        this.type = type;
        this.player = player;
        this.specificPlayer = specificPlayer;
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

    public String getSpecificPlayer() {
        return specificPlayer;
    }

    public void setSpecificPlayer(String specificPlayer) {
        this.specificPlayer = specificPlayer;
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