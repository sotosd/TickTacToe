/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.requests;

import CommClasses.CommunicationMessage;
import CommClasses.CommunicationType;
import SharedClasses.Player;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
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
 *
 */
// This is the inner class HttpRequestHandler that handles the http requests sent from any device.
public class RegisterUserHandler implements HttpHandler {

    // Handle method. This method handles an HttpExchange.
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

            Gson gson = new Gson();

            CommunicationMessage cm = gson.fromJson(env, CommunicationMessage.class);

            Player p = cm.getPlayer();

            OutputStream os = he.getResponseBody();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            if (cm.getType() != CommunicationType.login) {
                he.sendResponseHeaders(401, 0);

                CommunicationMessage rcm = new CommunicationMessage();
                rcm.setType(CommunicationType.login_refuse);
                bw.write(new Gson().toJson(rcm));
                bw.close();
                he.close();

                return;
            }

            for (int i = 0; i < MyStatics.players.size(); i++) {
                if (MyStatics.players.get(i).getName().equals(p.getName())) {

                    he.sendResponseHeaders(400, 0);

                    CommunicationMessage rcm = new CommunicationMessage();
                    rcm.setType(CommunicationType.login_refuse);
                    bw.write(new Gson().toJson(rcm));
                    bw.close();
                    he.close();

                    return;
                }
            }

            he.sendResponseHeaders(201, 0);

            MyStatics.playerCount += 1;

            p.setId(MyStatics.playerCount);

            MyStatics.players.add(p);

            System.out.println("Text = " + env);

            CommunicationMessage rcm = new CommunicationMessage();
            rcm.setType(CommunicationType.login_accept);
            rcm.setPlayer(p);
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

    // This method is used to dismiss a get method recieved.
    private void dismissGetMethod(HttpExchange he) {
        try {
            // Get the responce headers of the request.
            Headers responseHeaders = he.getResponseHeaders();

            // Set the type of the responce.
            responseHeaders.set("Content-Type", "text/plain");

            // Send responce headers
            he.sendResponseHeaders(500, 0);

            // Get the responce body as an output stream.
            OutputStream responseBody = he.getResponseBody();

            // Inform the device's user that we do not accept get methods on this server.
            responseBody.write("No get methods allowed here. Go home!".getBytes());

            // Inform the admin that there was a client that tried GET request.
            System.err.println("Client with IP: " + he.getRemoteAddress() + " tried a get method. Shutting down connection.");

            // Close the connection to avoid any more transactions from this client.
            responseBody.close();

            // Close the HTTP Exchange to avoid any more transactions from the client.
            he.close();

        } // Catch any exception risen from the above statements.
        catch (IOException ex) {
            // Log any exception risen from the above statements.
            Logger.getLogger(RegisterUserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
