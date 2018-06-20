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
public class RegisterGameHandler implements HttpHandler {

    private HttpServer server;

    public RegisterGameHandler(HttpServer server) {
        this.server = server;
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

            if (cm.getType() != CommunicationType.create_game) {

                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.login_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;

            }

            for (int i = 0; i < MyStatics.players.size(); i++) {
                if (MyStatics.players.get(i).getId() == cm.getPlayer().getId()) {
                    if (!MyStatics.players.get(i).isPlaying()) {
                        MyStatics.players.get(i).setPlaying(true);
                        break;
                    } else {
                        he.sendResponseHeaders(401, 0);

                        CommunicationMessage rcm = new CommunicationMessage();
                        rcm.setType(CommunicationType.already_joined_game);
                        bw.write(new Gson().toJson(rcm));
                        bw.close();
                        he.close();

                        return;
                    }
                }
            }

            MyStatics.gamesCount += 1;

            Game game = new Game();

            game.setP1(cm.getPlayer());

            game.setId(MyStatics.gamesCount);

            game.setStatus(GameStatus.waiting_player_2_to_join);

            MyStatics.games.add(game);

            he.sendResponseHeaders(201, 0);

            CommunicationMessage rcm = new CommunicationMessage(CommunicationType.game_created, game);

            bw.write(new Gson().toJson(rcm));

            this.server.createContext("/games/" + game.getId(), new PlayGameHandler(game));

            bw.flush();

            bw.close();
            he.close();

            //Inform the administrator of the server that the lines are all recieved.
            System.out.println("Lines Recieved.");

        } // Catch any exception risen from the above statements.
        catch (IOException ex) {
            // Log the exception risen from the above statements.
            Logger.getLogger(RegisterUserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HttpServer getServer() {
        return server;
    }

    public void setServer(HttpServer server) {
        this.server = server;
    }

}
