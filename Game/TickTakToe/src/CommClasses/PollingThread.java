
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommClasses;

import com.google.gson.Gson;
import frames.GamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import ticktaktoe.GameStatus;
import ticktaktoe.MyStatics;

/**
 *
 * @author sotos
 */
public class PollingThread implements Runnable {

    GameStatus previousStatus;
    boolean resultShownOnce = false;
    GamePanel gp;
    boolean player_2_joined = false;

    public PollingThread(GamePanel gp) {
        this.gp = gp;
    }

    public PollingThread() {
    }

    @Override
    public void run() {
        while (true) {
            try {

                CommunicationMessage cm = new CommunicationMessage(CommunicationType.get_status, MyStatics.me);
                String postUrl = "http://" + MyStatics.domain + ":" + MyStatics.port + "/games/" + this.gp.getGame().getId();// put in your url
                Gson gson = new Gson();
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(gson.toJson(cm));//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "text");
                HttpResponse response = httpClient.execute(post);
                HttpEntity ans = response.getEntity();
                String responseString = EntityUtils.toString(ans, "UTF-8");
                System.out.println(responseString);

                cm = gson.fromJson(responseString, CommunicationMessage.class);
                this.gp.setGame(cm.getGame());
                this.gp.getPlayer1().setText(cm.getGame().getP1().getName());
                this.gp.getScore1().setText(cm.getGame().getP1().getScore() + "");

                if (cm.getGame().getStatus() != GameStatus.result) {
                    this.gp.getPlayAgain().setEnabled(false);
                }

                if (previousStatus != null && previousStatus == GameStatus.result) {
                    if (cm.getGame().getStatus() == GameStatus.waiting_for_player_1_to_play) {
                        this.gp.setGame(cm.getGame());
                        ImageIcon image_init = new ImageIcon(("images/blank.png"));
                        this.gp.getTable1_1().setIcon(image_init);
                        this.gp.getTable1_2().setIcon(image_init);
                        this.gp.getTable1_3().setIcon(image_init);
                        this.gp.getTable2_1().setIcon(image_init);
                        this.gp.getTable2_2().setIcon(image_init);
                        this.gp.getTable2_3().setIcon(image_init);
                        this.gp.getTable3_1().setIcon(image_init);
                        this.gp.getTable3_2().setIcon(image_init);
                        this.gp.getTable3_3().setIcon(image_init);
                        resultShownOnce = false;
                    }
                }

                if (cm.getGame().getP2() == null) {
                } else {
                    this.gp.getPlayer2().setText(cm.getGame().getP2().getName());
                    this.gp.getScore2().setText(cm.getGame().getP2().getScore() + "");

                    if (!player_2_joined) {
                        player_2_joined = true;
                        this.gp.getTable1_1().setEnabled(true);
                        this.gp.getTable1_2().setEnabled(true);
                        this.gp.getTable1_3().setEnabled(true);
                        this.gp.getTable2_1().setEnabled(true);
                        this.gp.getTable2_2().setEnabled(true);
                        this.gp.getTable2_3().setEnabled(true);
                        this.gp.getTable3_1().setEnabled(true);
                        this.gp.getTable3_2().setEnabled(true);
                        this.gp.getTable3_3().setEnabled(true);
                    }
                }
                
                if (this.gp.getGame().getTable()[0][0] != '-') {
                    this.gp.getTable1_1().setIcon(this.gp.getGame().getTable()[0][0] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                   // this.gp.getTable1_1().removeActionListener(this.gp.getT11());
                }
                if (this.gp.getGame().getTable()[0][1] != '-') {
                    this.gp.getTable1_2().setIcon(this.gp.getGame().getTable()[0][1] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                    //this.gp.getTable1_2().removeActionListener(this.gp.getT12());
                }
                if (this.gp.getGame().getTable()[0][2] != '-') {
                    this.gp.getTable1_3().setIcon(this.gp.getGame().getTable()[0][2] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                    //this.gp.getTable1_3().removeActionListener(this.gp.getT13());
                }

                if (this.gp.getGame().getTable()[1][0] != '-') {
                    this.gp.getTable2_1().setIcon(this.gp.getGame().getTable()[1][0] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                    //this.gp.getTable2_1().removeActionListener(this.gp.getT21());
                }
                if (this.gp.getGame().getTable()[1][1] != '-') {
                    this.gp.getTable2_2().setIcon(this.gp.getGame().getTable()[1][1] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                    //this.gp.getTable2_2().removeActionListener(this.gp.getT22());
                }
                if (this.gp.getGame().getTable()[1][2] != '-') {
                    this.gp.getTable2_3().setIcon(this.gp.getGame().getTable()[1][2] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                    //this.gp.getTable2_3().removeActionListener(this.gp.getT23());
                }

                if (this.gp.getGame().getTable()[2][0] != '-') {
                    this.gp.getTable3_1().setIcon(this.gp.getGame().getTable()[2][0] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                   // this.gp.getTable3_1().removeActionListener(this.gp.getT31());
                }
                if (this.gp.getGame().getTable()[2][1] != '-') {
                    this.gp.getTable3_2().setIcon(this.gp.getGame().getTable()[2][1] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
                   // this.gp.getTable3_2().removeActionListener(this.gp.getT32());
                }
                if (this.gp.getGame().getTable()[2][2] != '-') {
                    this.gp.getTable3_3().setIcon(this.gp.getGame().getTable()[2][2] == 'x' ? new ImageIcon("images/X.jpg") : new ImageIcon("images/O.jpg"));
//                    this.gp.getTable3_3().removeActionListener(this.gp.getT33());
                }

                System.out.println("Status is " + cm.getGame().getStatus());
                if (cm.getGame().getStatus() == GameStatus.result && !resultShownOnce) {

                    if (cm.getGame().getWinner().getName().equals(MyStatics.me.getName())) {
                        JOptionPane.showMessageDialog(null, "You are the winner!");
                    } else {
                        JOptionPane.showMessageDialog(null, "You lost!");
                    }
                    resultShownOnce = true;
                    this.gp.getPlayAgain().setEnabled(true);

                }
                previousStatus = cm.getGame().getStatus();

                Thread.sleep(1000);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(PollingThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PollingThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PollingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
