/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import CommClasses.CommunicationMessage;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;



/**
 *
 * @author Dragoon
 */
public class MainRequest {

    public static void main(String[] Args) throws UnsupportedEncodingException, IOException {

        String postUrl = "http://192.168.1.3:100/register/user";// put in your url
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        CommunicationMessage cm = new CommunicationMessage();
        StringEntity postingString = new StringEntity("sotos");//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "text");
        HttpResponse response = httpClient.execute(post);

    }
}
