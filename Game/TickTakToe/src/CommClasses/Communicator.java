/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommClasses;


import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ticktaktoe.MyStatics;

/**
 *
 * @author ToyMaker
 */
public class Communicator {
    
    DataInputStream dis;
    DataOutputStream dos;
    
    public Communicator(){
        
    }
    
    public void sendMessage(CommunicationMessage input){
        try {
            String env = new Gson().toJson(input);
//            String toSend = CryptographyUtils.symmetricEncrypt(MyStatics.aes_key, env);
            dos.writeUTF(env);
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public CommunicationMessage recieveMessage(){
        try {
            String recieved = dis.readUTF();
//            String decrypted = CryptographyUtils.symmetricDecrypt(MyStatics.aes_key, recieved);
            return new Gson().fromJson(recieved, CommunicationMessage.class);
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
