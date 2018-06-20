/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.requests;

import CommClasses.CommunicationMessage;
import CommClasses.CommunicationType;
import SharedClasses.GameStatus;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
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
public class JoinSpecificGameHandler implements HttpHandler {

    public JoinSpecificGameHandler() {

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

            if (cm.getType() != CommunicationType.enter_specific_game) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.login_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }

            boolean playerExists = false;

            for (int i = 0; i < MyStatics.players.size(); i++) {
                if (MyStatics.players.get(i).getName().equals(cm.getSpecificPlayer())) {
                    playerExists = true;
                    break;
                }
            }

            if (!playerExists) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.player_not_exists);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }

            for (int i = 0; i < MyStatics.games.size(); i++) {
                if (MyStatics.games.get(i).getP1().getName().equals(cm.getSpecificPlayer()) && MyStatics.games.get(i).getStatus() == GameStatus.waiting_player_2_to_join) {
                    MyStatics.games.get(i).setP2(cm.getPlayer());
                    MyStatics.games.get(i).setStatus(GameStatus.waiting_for_player_1_to_play);

                    he.sendResponseHeaders(201, 0);

                    CommunicationMessage rcm = new CommunicationMessage();
                    rcm.setType(CommunicationType.game_accepted);
                    MyStatics.games.get(i).setStatus(GameStatus.waiting_for_player_1_to_play);
                    rcm.setGame(MyStatics.games.get(i));
                    bw.write(new Gson().toJson(rcm));
                    bw.close();
                    he.close();
                    return;

                } else if (MyStatics.games.get(i).getP1().getName().equals(cm.getSpecificPlayer()) && (MyStatics.games.get(i).getStatus() == GameStatus.waiting_for_player_1_to_play || MyStatics.games.get(i).getStatus() == GameStatus.waiting_for_player_2_to_play)) {
                    he.sendResponseHeaders(201, 0);

                    CommunicationMessage rcm = new CommunicationMessage();
                    rcm.setType(CommunicationType.busy);
                    bw.write(new Gson().toJson(rcm));
                    bw.close();
                    he.close();

                    return;
                }
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
