/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedClasses;

/**
 *
 * @author Dragoon
 */


public class Game {

    
    

    
    private int id;
    private char[][] table = new char[][]{{'-','-','-'},{'-','-','-'},{'-','-','-'}};
    private Player p1;
    private Player p2;
    private GameStatus status = GameStatus.waiting_player_2_to_join;
    private Player winner ;
    
    int count=0;

    
    public Game() {
    }

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public boolean isEmptyByOne(){
        for( int i=0;i < 2;i++){
            for (int j=0;j<2;j++){
                if (table [i][j] != '-'){
                    count++;
                }
            }
        }
        return (count <= 1);
    }

    public char[][] getTable() {
        return table;
    }

    public void setTable(char[][] table) {
        this.table = table;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
        
    public void checkIfFinished(){
        // Prwth grammh
        if (table[0][0] == table[0][1] && table[0][0]==table[0][2] && table[0][0] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else 
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Deuterh grammh
        else if (table[1][0] == table[1][1] && table[1][0]==table[1][2] && table[1][0] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Trith grammh
        else if (table[2][0] == table[2][1] && table[2][0]==table[2][2] && table[2][0] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Prwth sthlh
        else if (table[0][0] == table[1][0] && table[0][0]==table[2][0] && table[0][0] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Deuterh sthlh
        else if (table[0][1] == table[1][1] && table[0][1]==table[2][1] && table[0][1] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Trith sthlh
        else if (table[0][2] == table[1][2] && table[0][2]==table[2][2] && table[0][2] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Isia diagwnios
        else if (table[0][0] == table[1][1] && table[0][0]==table[2][2] && table[0][0] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
        // Anapodh diagwnios
        else if (table[0][2] == table[1][1] && table[0][2]==table[2][0] && table[0][2] != '-'){
            if (table[0][0] == 'x'){
                p1.setScore(p1.getScore()+1);
                winner = p1;
            }
            else
            {
                p2.setScore(p2.getScore()+1);
                winner = p2;
            }
            this.status = GameStatus.result;
        }
    }
}
