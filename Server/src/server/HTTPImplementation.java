/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.requests.JoinSpecificGameHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.requests.JoinRandGameHandler;
import server.requests.LogOffGameHandler;
import server.requests.RegisterGameHandler;
import server.requests.RegisterUserHandler;

/**
 *
 * @author Dragoon
 */
public class HTTPImplementation {

    public HTTPImplementation() {
        try {
            // Create a socket on port 9000.
            InetSocketAddress addr = new InetSocketAddress(MyStatics.port);

            // Create an http server for the specific address created above.
            HttpServer server = HttpServer.create(addr, 0);

            // Create a new context for the server under the /register/user domain.
            // Use the handler to handle the http requests for registering a user.
            server.createContext("/register/user", new RegisterUserHandler());
            
            server.createContext("/register/game", new RegisterGameHandler(server));
            
            server.createContext("/join/specific", new JoinSpecificGameHandler());
            
            server.createContext("/join/random", new JoinRandGameHandler());
            
            server.createContext("/logoff", new LogOffGameHandler());
                        
            // Set the executor for the server as a Cached Thread Pool.
            server.setExecutor(Executors.newCachedThreadPool());

            // Start the server.
            server.start();

            // Inform the server administrator that the server is now listening on port 9000.
            System.out.println("Server is listening on port " + MyStatics.port);
        } // Catch any exception risen from the above statements. // Catch any exception risen from the above statements.
        catch (IOException ex) {
            // Log any exception risen from the above statements.
            Logger.getLogger(HTTPImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}