/* This class contains the Source Code of the TicTacToe 's Game Panel
 * The TicTacToe 's panel is described by a 2 dimensional matrix (3x3).
 * Each cell is implamentated by a jButton, in which there are two options that can be drawn . 
 * Either the cell will get the X value (Player 1) or it will get the O value (Player 2).
 * For the Xs and Os I used images that I found on web, and when a player clicks on a specific cell, the image changes.
 * The Game, also gives you some options on how you will start playing, as soon as you have loged in. 
 * First Option : Create a new game, in which you are ready to start playing and you have to wait for the opponent to login at your game.
 * Second Option : Enter Random game, in which you get randomly in game that someone has been waiting for an opponent.
 * Third Option : Enter Specific game, in which you get to connect to a specific game. In order this to happen, you will be asked to 
 * give information about this game. The information that is being needed is the player's username (*the player's, who created this game). 
 */
package frames;

import CommClasses.CommunicationMessage;
import CommClasses.CommunicationType;
import CommClasses.PollingThread;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.Action.LARGE_ICON_KEY;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import ticktaktoe.Game;
import ticktaktoe.GameStatus;
import ticktaktoe.MyStatics;

/**
 *
 * @author Dragoon
 */
public class GamePanel extends javax.swing.JFrame {
//Creation of Action Listener for each jButton
    ActionListener t11, t12, t13;
    ActionListener t21, t22, t23;
    ActionListener t31, t32, t33;

    Game game; //Object game created,in order to know in which game the player belongs
    Thread poller; // Use the logic of Polling, so the server would be able to link the creator of the game with his opponent. 

    /**
     * Creates new form GamePanel
     */
    public GamePanel() {
        initComponents(); //Function that initializes the graphic enviroment of the game panel (jButtons,jLabels etc).
        custom_init();
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /* Custom_init() is a function in wich the initialization of the jButton images takes place.
     * Moreover, all the jButtons are being disabled, as the game hasn't started yet. There will be enabled when an opponent connects at the table. 
     */
    private void custom_init() {
        // Buttons are initialized with White coloured image. 
        ImageIcon image_init = new ImageIcon(("images/blank.png"));
        table1_1.setIcon(image_init);
        table1_2.setIcon(image_init);
        table1_3.setIcon(image_init);
        table2_1.setIcon(image_init);
        table2_2.setIcon(image_init);
        table2_3.setIcon(image_init);
        table3_1.setIcon(image_init);
        table3_2.setIcon(image_init);
        table3_3.setIcon(image_init);
        //Buttons are disabled
        table1_1.setEnabled(false);
        table1_2.setEnabled(false);
        table1_3.setEnabled(false);
        table2_1.setEnabled(false);
        table2_2.setEnabled(false);
        table2_3.setEnabled(false);
        table3_1.setEnabled(false);
        table3_2.setEnabled(false);
        table3_3.setEnabled(false);
        
        //For each jButton is being used an action listener when an event occurs
        t11 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                table1_1ActionPerformed();
            }
        };
        table1_1.addActionListener(t11);

        t12 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table1_2ActionPerformed();
            }
        };
        table1_2.addActionListener(t12);

        t13 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table1_3ActionPerformed();
            }
        };
        table1_3.addActionListener(t13);

        t21 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table2_1ActionPerformed();
            }
        };
        table2_1.addActionListener(t21);

        t22 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table2_2ActionPerformed();
            }
        };
        table2_2.addActionListener(t22);

        t23 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table2_3ActionPerformed();
            }
        };
        table2_3.addActionListener(t23);

        t31 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table3_1ActionPerformed();
            }
        };
        table3_1.addActionListener(t31);

        t32 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table3_2ActionPerformed();
            }
        };
        table3_2.addActionListener(t32);

        t33 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table3_3ActionPerformed();
            }
        };
        table3_3.addActionListener(t33);

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table1_1 = new javax.swing.JButton();
        table1_2 = new javax.swing.JButton();
        table1_3 = new javax.swing.JButton();
        table2_1 = new javax.swing.JButton();
        table2_2 = new javax.swing.JButton();
        table2_3 = new javax.swing.JButton();
        table3_1 = new javax.swing.JButton();
        table3_2 = new javax.swing.JButton();
        table3_3 = new javax.swing.JButton();
        Player1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Player2 = new javax.swing.JLabel();
        Score2 = new javax.swing.JLabel();
        Score1 = new javax.swing.JLabel();
        PlayAgain = new javax.swing.JButton();
        LogOff = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Player1.setText("Player 1:");

        jLabel2.setText("Score");

        Player2.setText("Player 2:");

        Score2.setText("-");

        Score1.setText("-");

        PlayAgain.setText("Play Again");
        PlayAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayAgainActionPerformed(evt);
            }
        });

        LogOff.setText("Log Off");
        LogOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOffActionPerformed(evt);
            }
        });

        jMenu1.setText("New");

        jMenuItem1.setText("Create Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Enter Random Game");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Enter Specific Game");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(table1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(table1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(table1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(table2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(table2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(table2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Player1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Player2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Score1)
                                    .addComponent(Score2)))
                            .addComponent(PlayAgain, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(87, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(table3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(table3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(table3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(LogOff)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(table1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(table2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(table3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Score1)
                            .addComponent(Player1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Player2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Score2))
                        .addGap(18, 18, 18)
                        .addComponent(PlayAgain)
                        .addGap(37, 37, 37)
                        .addComponent(LogOff))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /* This section describes for each jButton ("cell") the Action that will be performed when the jButton gets clicked.
     * The prosedure will be described only for one jButton, as the rest jButtons have exactly the same logic implemented. 
    */
    private void table1_1ActionPerformed() {
        System.out.println("Game[1][1] = " + game.getTable()[0][0]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));
        /*Checking if the cell is empty and if it is my turn to play. If the condition is true, then        */
        if (game.getTable()[0][0] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {
            /*An Communication Type variable, ct, gets created and initialized.
             *ct will get the value of "added_x" if player 1 makes a move, or "added_o" if the player 2 makes a move. */
            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {
                //System.out.println(ct.toString());
                //Then a Communication message gets created with information that descreibes the Communication type and the player.
                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                //Setting the possition of the cell in the main matrix
                cm.setPosx(0); 
                cm.setPosy(0);
                //creation of the domain that the message will be sent
                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                //Obeject of Gson Library for serialization
                Gson gson = new Gson();
                //Creation of the Http Client
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                System.out.println("Sending " + gson.toJson(cm));
                //the communication message gets serialized
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                //Posting the comminication message to the spesific domain, where the server is uploaded.
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                //Response message from the server 
                HttpResponse response = httpClient.execute(post);
                //If the response message has a status code 401 that means that the request we applied is Unauthorised. 
                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }
                //Getting the response's message
                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);
                //Unserializing the respose message
                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                /*Selection of the icon that the jButton will get.
                 *The image of "X" the move was made by the player 1, image of "O" if the player 2 played.
                */
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                //Settin the icon
                table1_1.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table1_2ActionPerformed() {
        System.out.println("Game[1][2] = " + game.getTable()[0][1]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));

        if (game.getTable()[0][1] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(0);
                cm.setPosy(1);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                System.out.println("Sending " + gson.toJson(cm));
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table1_2.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table1_3ActionPerformed() {
        System.out.println("Game[1][3] = " + game.getTable()[0][2]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));


        if (game.getTable()[0][2] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(0);
                cm.setPosy(2);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table1_3.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table2_1ActionPerformed() {
        System.out.println("Game[2][1] = " + game.getTable()[1][0]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));


        if (game.getTable()[1][0] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(1);
                cm.setPosy(0);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table2_1.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table2_2ActionPerformed() {
        System.out.println("Game[2][2] = " + game.getTable()[1][1]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));


        if (game.getTable()[1][1] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(1);
                cm.setPosy(1);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table2_2.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table2_3ActionPerformed() {
        System.out.println("Game[2][3] = " + game.getTable()[1][2]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));

        if (game.getTable()[1][2] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(1);
                cm.setPosy(2);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table2_3.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table3_1ActionPerformed() {
        System.out.println("Game[3][1] = " + game.getTable()[2][0]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));


        if (game.getTable()[2][0] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(2);
                cm.setPosy(0);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table3_1.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table3_2ActionPerformed() {
        System.out.println("Game[3][2] = " + game.getTable()[2][1]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));
        
        
        if (game.getTable()[2][1] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {

            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me);
                cm.setPosx(2);
                cm.setPosy(1);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table3_2.setIcon(image_after_action);
                 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void table3_3ActionPerformed() {
        System.out.println("Game[3][3] = " + game.getTable()[2][2]);
        System.out.println("I'm player 2 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName())));
        System.out.println("I'm player 1 and im playing now? " + (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())));
        if (game.getTable()[2][2] == '-' && ((game.getStatus() == GameStatus.waiting_for_player_2_to_play && MyStatics.me.getName().equals(game.getP2().getName()))
                || (game.getStatus() == GameStatus.waiting_for_player_1_to_play && MyStatics.me.getName().equals(game.getP1().getName())))) {
            
            CommunicationType ct = MyStatics.me.getName().equals(this.game.getP1().getName()) ? CommunicationType.added_x : CommunicationType.added_o;
            try {

                CommunicationMessage cm = new CommunicationMessage(ct, MyStatics.me, 2, 2);

                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                System.out.println("STRING SENT " + postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);

                if (response.getStatusLine().getStatusCode() == 401) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                }

                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.game = cm.getGame();
                ImageIcon image_after_action = MyStatics.me.getName().equals(this.game.getP1().getName()) ? new ImageIcon(("images/X.jpg")) : new ImageIcon(("images/O.jpg"));
                table3_3.setIcon(image_after_action);
                
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* First Option : Create Game - Action Performed
     * Client creates a cm (Communiction Message) to Server, with communiction type :"create_game" and his identity. 
     * Then, an object for serialization gets created. (google Gson library)
     * We serialize the cm and then post it to the server. After then, we are waiting for the server's response
     * When the response arrives, we check if the player is allready playing in a game, if the request we aplied is unauthorised
     * If the previous conditions are not met, then we are free to start the game with the oppenent we wished.
     * Also,player's name and score gets revealed in game panel.
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            //Creation of communication message with communication type "create_game" and the player's identity 
            CommunicationMessage cm = new CommunicationMessage(CommunicationType.create_game, MyStatics.me);
            //Setting of the domain that the cm will be sent 
            String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/register/game";// put in your url
            //Object to serialize the cm
            Gson gson = new Gson();
            //creation of the client
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
            //Posting the serialized message
            post.setEntity(postingString);
            post.setHeader("Content-type", "text");
            //Response message from the server
            HttpResponse response = httpClient.execute(post);
            HttpEntity ans = response.getEntity();
            String responseString = EntityUtils.toString(ans, "UTF-8");
            System.out.println(responseString);
            //Unserializing the responce message
            cm = gson.fromJson(responseString, CommunicationMessage.class);
            //If the responce's message status code is 401 then
            //we have to check if the user is already in a game,if yes that means he cannot play with someone else at the same time, so the system returns
            //otherwise the request we aplied is unauthorised.
            if (response.getStatusLine().getStatusCode() == 401) {
                if (cm.getType() == CommunicationType.already_joined_game) {
                    JOptionPane.showMessageDialog(null, "You have already joined a game!");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                    return;
                }

            }
            //Differently, if the communication type of the response message is "game_created"
            //Name and score of the player gets revealed and we set the game for this insance.
            //We are ,also, using Polling, so the game's thread would ask continiously from server if an opponents has joined his game
            if (cm.getType() == CommunicationType.game_created) {
                Player1.setText(cm.getGame().getP1().getName());
                Score1.setText(cm.getGame().getP1().getScore() + "");
                this.game = cm.getGame();
                // Creation of this game's thread 
                poller = new Thread(new PollingThread(this));
                // firing thread
                poller.start();
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /* Third Option : Enter Specific Game - Action Performed
     * Client is being asked to give his opponent username, who has already created a game
     * Client creates a cm (Communiction Message) to Server, with communiction type :"enter_specific_game",his identity and his opponent identity. 
     * Then, an object for serialization gets created. (google Gson library)
     * We serialize the cm and then post it to the server. After then, we are waiting for the server's response
     * When the response arrives, we check if the player is allready playing with someone else, if the request we aplied is unauthorised
     * If the previous conditions are not met, then we are free to start the game with the oppenent we wished.
     * Also,both of player's names and scores get revealed in both game panels. 
     * Then tha game begins, with player 1 to be the firt who makes a move.
     */
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        String player = JOptionPane.showInputDialog("Enter your oponent's username: ");
        try {
            //Creation of communication message with communication type "enter_specific_game",player's and opponent's identities 
            CommunicationMessage cm = new CommunicationMessage(CommunicationType.enter_specific_game, MyStatics.me, player);
            //Setting of the domain that the cm will be sent 
            String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/join/specific";// put in your url
            //Object to serialize the cm
            Gson gson = new Gson();
            //creation of the client
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
            //Posting the serialized message
            post.setEntity(postingString);
            post.setHeader("Content-type", "text");
            //Response message from the server
            HttpResponse response = httpClient.execute(post);
            HttpEntity ans = response.getEntity();
            String responseString = EntityUtils.toString(ans, "UTF-8");
            System.out.println(responseString);
            //Unserializing the responce message
            cm = gson.fromJson(responseString, CommunicationMessage.class);
            //If the response's Status Code is 201, then it means that the opponent we asked for is already in a game
            //System returns
            if (response.getStatusLine().getStatusCode() == 201) {
                if (cm.getType() == CommunicationType.busy) {
                    JOptionPane.showMessageDialog(null, "The user: " + player + " is already in a game.");
                    return;
                }
            }
            //If the response's Status Code is 401, then if the communication type is "login_refuse", the request we aplied for
            //is unauthorised, or if the communication type is "player_not_exists",the the opponent hasn't logged created a game yet.
            //System returns
            if (response.getStatusLine().getStatusCode() == 401) {
                if (cm.getType() == CommunicationType.login_refuse) {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                } else if (cm.getType() == CommunicationType.player_not_exists) {
                    JOptionPane.showMessageDialog(null, "There is no player with this username.");
                }
                return;
            }
            //If the previous conditions are don't met, then the game starts.
            //Names and Scores of each player gets revealed in both game panels.
            if (cm.getType() == CommunicationType.game_accepted) {
                Player1.setText(cm.getGame().getP1().getName());
                Score1.setText(cm.getGame().getP1().getScore() + "");

                Player2.setText(cm.getGame().getP2().getName());
                Score2.setText(cm.getGame().getP2().getScore() + "");
                this.game = cm.getGame();

                poller = new Thread(new PollingThread(this));
                poller.start();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    //Button - Play Again - Action Performed
    private void PlayAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayAgainActionPerformed
        // TODO add your handling code here:
        try {
            //Creation of communication message with communication type "enter_specific_game",player's and opponent's identities 
            CommunicationMessage cm = new CommunicationMessage(CommunicationType.restart, MyStatics.me);
            //Setting of the domain that the cm will be sent 
            String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/"+game.getId();// put in your url
            //Object to serialize the cm
            Gson gson = new Gson();
            //creation of the client
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
            //Posting the serialized message
            post.setEntity(postingString);
            post.setHeader("Content-type", "text");
            //Response message from the server
            HttpResponse response = httpClient.execute(post);
            HttpEntity ans = response.getEntity();
            String responseString = EntityUtils.toString(ans, "UTF-8");
            System.out.println(responseString);
            //Unserializing the responce message
            cm = gson.fromJson(responseString, CommunicationMessage.class);
            //If the response's Status Code is 201, then it means that the opponent we asked for is already in a game
            //System returns
            if (response.getStatusLine().getStatusCode() == 201) {
                this.game = cm.getGame();
                ImageIcon image_init = new ImageIcon(("images/blank.png"));
                table1_1.setIcon(image_init);
                table1_2.setIcon(image_init);
                table1_3.setIcon(image_init);
                table2_1.setIcon(image_init);
                table2_2.setIcon(image_init);
                table2_3.setIcon(image_init);
                table3_1.setIcon(image_init);
                table3_2.setIcon(image_init);
                table3_3.setIcon(image_init);
                
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PlayAgainActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try {
            //Creation of communication message with communication type "enter_specific_game",player's and opponent's identities 
            CommunicationMessage cm = new CommunicationMessage(CommunicationType.enter_random_game, MyStatics.me);
            //Setting of the domain that the cm will be sent 
            String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/join/random";// put in your url
            //Object to serialize the cm
            Gson gson = new Gson();
            //creation of the client
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
            //Posting the serialized message
            post.setEntity(postingString);
            post.setHeader("Content-type", "text");
            //Response message from the server
            HttpResponse response = httpClient.execute(post);
            HttpEntity ans = response.getEntity();
            String responseString = EntityUtils.toString(ans, "UTF-8");
            System.out.println(responseString);
            //Unserializing the responce message
            cm = gson.fromJson(responseString, CommunicationMessage.class);
            //If the response's Status Code is 201, then it means that the opponent we asked for is already in a game
            //System returns
            if (response.getStatusLine().getStatusCode() == 201) {
                if (cm.getType() == CommunicationType.busy) {
                    JOptionPane.showMessageDialog(null, "There is no aveliable game.");
                    return;
                }
            }
            //If the response's Status Code is 401, then if the communication type is "login_refuse", the request we aplied for
            //is unauthorised, or if the communication type is "player_not_exists",the the opponent hasn't logged created a game yet.
            //System returns
            if (response.getStatusLine().getStatusCode() == 401) {
//                if (cm.getType() == CommunicationType.login_refuse) {
//                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
//                } else if (cm.getType() == CommunicationType.game_not_exist) {
                    JOptionPane.showMessageDialog(null, "There is no aveliable game.");
//                }
                return;
            }
            //If the previous conditions are don't met, then the game starts.
            //Names and Scores of each player gets revealed in both game panels.
            if (cm.getType() == CommunicationType.game_accepted) {
                Player1.setText(cm.getGame().getP1().getName());
                Score1.setText(cm.getGame().getP1().getScore() + "");

                Player2.setText(cm.getGame().getP2().getName());
                Score2.setText(cm.getGame().getP2().getScore() + "");
                this.game = cm.getGame();

                poller = new Thread(new PollingThread(this));
                poller.start();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void LogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOffActionPerformed
        // TODO add your handling code here:
        try {
            //Creation of communication message with communication type "enter_specific_game",player's and opponent's identities 
            CommunicationMessage cm = new CommunicationMessage(CommunicationType.logoff, MyStatics.me);
            //Setting of the domain that the cm will be sent 
            String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/logoff";// put in your url
            //Object to serialize the cm
            Gson gson = new Gson();
            //creation of the client
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
            //Posting the serialized message
            post.setEntity(postingString);
            post.setHeader("Content-type", "text");
            //Response message from the server
            HttpResponse response = httpClient.execute(post);
            HttpEntity ans = response.getEntity();
            String responseString = EntityUtils.toString(ans, "UTF-8");
            System.out.println(responseString);
            //Unserializing the responce message
            cm = gson.fromJson(responseString, CommunicationMessage.class);
            
            //If the responce's message status code is 401 then
            //we have to check if the user is already in a game,if yes that means he cannot play with someone else at the same time, so the system returns
            //otherwise the request we aplied is unauthorised.
            if (response.getStatusLine().getStatusCode() == 401) {
                if (cm.getType() == CommunicationType.logoff_refuse) {
                    JOptionPane.showMessageDialog(null, "You have already joined a game!");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Unauthorised Action!");
                    return;
                }
            }
            
            
            
            JOptionPane.showMessageDialog(null, "Log Off.");
            System.exit(0);
            
           

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_LogOffActionPerformed
    

    //-----------------------------------------------------------------------------------------------------------------//
    //From this point and on, the implementation of Getters and Setters takes place.
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public JButton getPlayAgain() {
        return PlayAgain;
    }

    public void setPlayAgain(JButton PlayAgain) {
        this.PlayAgain = PlayAgain;
    }

    public JLabel getPlayer1() {
        return Player1;
    }

    public void setPlayer1(JLabel Player1) {
        this.Player1 = Player1;
    }

    public JLabel getPlayer2() {
        return Player2;
    }

    public void setPlayer2(JLabel Player2) {
        this.Player2 = Player2;
    }

    public JLabel getScore1() {
        return Score1;
    }

    public void setScore1(JLabel Score1) {
        this.Score1 = Score1;
    }

    public JLabel getScore2() {
        return Score2;
    }

    public void setScore2(JLabel Score2) {
        this.Score2 = Score2;
    }

    public JButton getTable1_1() {
        return table1_1;
    }

    public void setTable1_1(JButton table1_1) {
        this.table1_1 = table1_1;
    }

    public JButton getTable1_2() {
        return table1_2;
    }

    public void setTable1_2(JButton table1_2) {
        this.table1_2 = table1_2;
    }

    public JButton getTable1_3() {
        return table1_3;
    }

    public void setTable1_3(JButton table1_3) {
        this.table1_3 = table1_3;
    }

    public JButton getTable2_1() {
        return table2_1;
    }

    public void setTable2_1(JButton table2_1) {
        this.table2_1 = table2_1;
    }

    public JButton getTable2_2() {
        return table2_2;
    }

    public void setTable2_2(JButton table2_2) {
        this.table2_2 = table2_2;
    }

    public JButton getTable2_3() {
        return table2_3;
    }

    public void setTable2_3(JButton table2_3) {
        this.table2_3 = table2_3;
    }

    public JButton getTable3_1() {
        return table3_1;
    }

    public void setTable3_1(JButton table3_1) {
        this.table3_1 = table3_1;
    }

    public JButton getTable3_2() {
        return table3_2;
    }

    public void setTable3_2(JButton table3_2) {
        this.table3_2 = table3_2;
    }

    public JButton getTable3_3() {
        return table3_3;
    }

    public void setTable3_3(JButton table3_3) {
        this.table3_3 = table3_3;
    }

    public ActionListener getT11() {
        return t11;
    }

    public void setT11(ActionListener t11) {
        this.t11 = t11;
    }

    public ActionListener getT12() {
        return t12;
    }

    public void setT12(ActionListener t12) {
        this.t12 = t12;
    }

    public ActionListener getT13() {
        return t13;
    }

    public void setT13(ActionListener t13) {
        this.t13 = t13;
    }

    public ActionListener getT21() {
        return t21;
    }

    public void setT21(ActionListener t21) {
        this.t21 = t21;
    }

    public ActionListener getT22() {
        return t22;
    }

    public void setT22(ActionListener t22) {
        this.t22 = t22;
    }

    public ActionListener getT23() {
        return t23;
    }

    public void setT23(ActionListener t23) {
        this.t23 = t23;
    }

    public ActionListener getT31() {
        return t31;
    }

    public void setT31(ActionListener t31) {
        this.t31 = t31;
    }

    public ActionListener getT32() {
        return t32;
    }

    public void setT32(ActionListener t32) {
        this.t32 = t32;
    }

    public ActionListener getT33() {
        return t33;
    }

    public void setT33(ActionListener t33) {
        this.t33 = t33;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogOff;
    private javax.swing.JButton PlayAgain;
    private javax.swing.JLabel Player1;
    private javax.swing.JLabel Player2;
    private javax.swing.JLabel Score1;
    private javax.swing.JLabel Score2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JButton table1_1;
    private javax.swing.JButton table1_2;
    private javax.swing.JButton table1_3;
    private javax.swing.JButton table2_1;
    private javax.swing.JButton table2_2;
    private javax.swing.JButton table2_3;
    private javax.swing.JButton table3_1;
    private javax.swing.JButton table3_2;
    private javax.swing.JButton table3_3;
    // End of variables declaration//GEN-END:variables
}
