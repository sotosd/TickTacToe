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
 * @author root
 */
public class LogOffGameHandler implements HttpHandler {
    
     private HttpServer server;

    
    public void handle(HttpExchange he) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Get the request method.
                String requestMethod = he.getRequestMethod();

                if (requestMethod.equalsIgnoreCase("POST")) {
                    // If the request method is a POST method we have to process the connection.
                    processPostMethod(he);
                } else {
                    he.close();
                }
            }
        }).start();
    }
    
    
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

            Gson gson = new Gson();

            CommunicationMessage cm = gson.fromJson(env, CommunicationMessage.class);
            
            
            Game game= new Game();
            Player p = cm.getPlayer();
            Player p1New=null;
            int gameId=111111;

            OutputStream os = he.getResponseBody();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            if (cm.getType() != CommunicationType.logoff) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.logoff_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }
                 
            
            for(int i=0;i<MyStatics.games.size();i++){
                if(MyStatics.games.get(i).getP1().getName().equals(p.getName())){                    
                    p1New=MyStatics.games.get(i).getP2();
                    gameId=MyStatics.games.get(i).getId();
                    game=MyStatics.games.get(i);
                    
                }
                else if(MyStatics.games.get(i).getP2().getName().equals(p.getName())){
                    p1New=MyStatics.games.get(i).getP1();
                    gameId=MyStatics.games.get(i).getId();
                    game=MyStatics.games.get(i);
                }
            }
                p1New.setScore(p1New.getScore()+1);
            
                game.setP2(null);
                game.setP1(p1New);
                game.setId(gameId);
                game.setStatus(GameStatus.waiting_player_2_to_join);
//                MyStatics.games.add(game);

  
                        
            
            for (int i=0;i<MyStatics.players.size();i++){
                if(MyStatics.players.get(i).getName().equals(p.getName())){
                    MyStatics.players.remove(MyStatics.players.get(i));
                    MyStatics.playerCount -= 1;                                        
                }                
            }
            
            he.sendResponseHeaders(201, 0);              

            System.out.println("Text = " + env);

            CommunicationMessage rcm = new CommunicationMessage();
            rcm.setType(CommunicationType.logoff_accept);
            bw.write(new Gson().toJson(rcm));
  
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
    
    
}
