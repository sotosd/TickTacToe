/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticktaktoe;

/**
 *
 * @author Dragoon
 */
public class Player {
    
    private int id;
    private String name;
    private int turn;
    private int score;
    private boolean playing;

    public Player() {
    }

    public Player(int id, String name, int turn, int score, boolean playing) {
        this.id = id;
        this.name = name;
        this.turn = turn;
        this.score = score;
        this.playing = playing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    
    
}
