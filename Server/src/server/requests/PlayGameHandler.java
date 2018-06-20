/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.requests;

import CommClasses.CommunicationMessage;
import CommClasses.CommunicationType;
import SharedClasses.Game;
import SharedClasses.GameStatus;
import SharedClasses.Player;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.MyStatics;

/**
 *
 * @author Dragoon
 */
public class PlayGameHandler implements HttpHandler {

    Game game;

    public PlayGameHandler(Game game) {
        this.game = game;
    }

    // Handle method. This method handles an HttpExchange.
    public void handle(HttpExchange he) {
        new Thread(new Runnable() {
            @Override
            public void run() {

// Get the request method.
                String requestMethod = he.getRequestMethod();

                // Check the request method of the HttpExchange.
                if (requestMethod.equalsIgnoreCase("POST")) {
                    // If the request method is a POST method we have to process the connection.
                    processPostMethod(he);
                } else {
                    he.close();
                }

            }
        }).start();

    }

    // This method is used to handle the Post Method recieved.
    private void processPostMethod(HttpExchange he) {
        try {
            // Inform the administrator of the server that there was a post method from a specific client.
            System.out.println("Client with IP: " + he.getRemoteAddress() + " tried a POST method.");

            // Get the input stream of the HTTP Exchange.
            InputStream requestBody = he.getRequestBody();

            // Create a buffered reader with request body as input.
            BufferedReader br = new BufferedReader(new InputStreamReader(requestBody));

            // Create a line that reads the input from the buffered reader.
            String line = "";

            // Create an envelope that contains the overall text sent from the client.
            String env = "";

            // While there are more bytes to read.
            // Read the line.
            while ((line = br.readLine()) != null) {
                // Add the read bytes to the envelope.
                env = env + line;
            }

            System.out.println("Text = " + env);

            Gson gson = new Gson();

            OutputStream os = he.getResponseBody();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            CommunicationMessage cm = gson.fromJson(env, CommunicationMessage.class);

            if (!cm.getPlayer().getName().equals(this.game.getP1().getName()) && !cm.getPlayer().getName().equals(this.game.getP2().getName())) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.login_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }
            
            if (cm.getType() == CommunicationType.restart){
                Player p1 = game.getP2();
                Player p2 = game.getP1();
                int id = game.getId();
                this.game = new Game(p1, p2);
                this.game.setId(id);
                this.game.setStatus(GameStatus.waiting_for_player_1_to_play);
                
                he.sendResponseHeaders(201, 0);
                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setGame(this.game);
                rcm.setType(CommunicationType.send_status);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }

            if (cm.getType() == CommunicationType.get_status) {
                if (game.getP2() == null) {
                } else {
                    if (game.getStatus() != GameStatus.result) {
                        game.checkIfFinished();
                    }
                }

                he.sendResponseHeaders(201, 0);
                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.send_status);
                rcm.setGame(this.game);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }

            if (cm.getType() == CommunicationType.added_x && cm.getPlayer().getName().equals(this.game.getP1().getName())) {
                he.sendResponseHeaders(201, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.send_status);

                this.game.setStatus(GameStatus.waiting_for_player_2_to_play);
                rcm.setGame(this.game);

                int x = cm.getPosx();
                int y = cm.getPosy();

                if (this.game.getTable()[x][y] == '-') {
                    this.game.getTable()[x][y] = 'x';
                } else {
                    throw new UnsupportedOperationException("Setting a character to a not empty box.");
                }
                System.out.println(new Gson().toJson(rcm));
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;

            } else if (cm.getType() == CommunicationType.added_x && !cm.getPlayer().getName().equals(this.game.getP1().getName())) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.login_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }

            if (cm.getType() == CommunicationType.added_o && cm.getPlayer().getName().equals(this.game.getP2().getName())) {
                he.sendResponseHeaders(201, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.send_status);

                this.game.setStatus(GameStatus.waiting_for_player_1_to_play);
                rcm.setGame(this.game);

                int x = cm.getPosx();
                int y = cm.getPosy();

                if (this.game.getTable()[x][y] == '-') {
                    this.game.getTable()[x][y] = 'o';
                } else {
                    throw new UnsupportedOperationException("Setting a character to a not empty box.");
                }

                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;

            } else if (cm.getType() == CommunicationType.added_o && !cm.getPlayer().getName().equals(this.game.getP2().getName())) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.login_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();
                
                return;
            }

            //Inform the administrator of the server that the lines are all recieved.
            System.out.println("Lines Recieved.");

        } // Catch any exception risen from the above statements.
        catch (IOException ex) {
            // Log the exception risen from the above statements.
            Logger.getLogger(RegisterUserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
