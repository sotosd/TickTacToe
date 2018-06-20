///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Security;
//
//
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.security.InvalidKeyException;
//import java.security.KeyFactory;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.Signature;
//import java.security.SignatureException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import ticktaktoe.MyStatics;
//
///**
// *
// * @author Dragoon
// */
//public class CryptographyUtils {
//
//    private int AES_Key_Size = 128;
//
//    public byte[] makeKey(){
//
//        try {
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            kgen.init(AES_Key_Size);
//            SecretKey key = kgen.generateKey();
//            return key.getEncoded();
//            
//        } catch (NoSuchAlgorithmException ex) {
//            System.err.println("Something went wrong with the makeKey func");
//        }
//        return null;
//    }
//
//    public static String signData(byte[] data, PrivateKey privateKey){
//        try {
//            Signature signature = Signature.getInstance("SHA256withRSA");
//            signature.initSign(privateKey);
//            signature.update(data);
//            return Base64.getEncoder().encodeToString(signature.sign());
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SignatureException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    public static boolean verifyData(byte[] data, String sigBytes, PublicKey publicKey){
//        try {
//            Signature signature = Signature.getInstance("SHA256withRSA");
//            signature.initVerify(publicKey);
//            signature.update(data);
//            return signature.verify(Base64.getDecoder().decode(sigBytes));
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SignatureException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//    
////    public String sign(PrivateKey privateKey,String message) throws SignatureException{
////    try {
////        Signature sign = Signature.getInstance("SHA1withRSA");
////        sign.initSign(privateKey);
////        sign.update(message.getBytes("UTF-8"));
////        return new String(Base64.getEncoder().encode(sign.sign()),"UTF-8");
////    } catch (Exception ex) {
////        throw new SignatureException(ex);
////    }
////}
//
////public boolean verify(PublicKey publicKey, String message, String signature) throws SignatureException{
////    try {
////        Signature sign = Signature.getInstance("SHA1withRSA");
////        sign.initVerify(publicKey);
////        sign.update(message.getBytes("UTF-8"));
////        return sign.verify(Base64.getDecoder().decode(signature.getBytes("UTF-8)")));
////    } catch (Exception ex) {
////        throw new SignatureException(ex);
////    }
////}
//    
//    public static String digest(String input) {
//        String toReturn = "";
//        try {
//            byte[] bytes = input.getBytes();
//
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(bytes);
//            byte[] digest;
//            digest = md.digest();
//
//            toReturn = String.format("%064x", new java.math.BigInteger(1, digest));
//
//        } catch (NoSuchAlgorithmException ex) {
//            System.err.println("Something went wrong with the digest func");
//        }
//        return "".equals(toReturn) ? null : toReturn;
//    }
//
//    public static String symmetricEncrypt(byte[] key, String value) {
//        try {
//            String digest = digest(value);
//            int length = value.length();
//
//            value = digest + " " + length + " " + value;
//
//            IvParameterSpec iv = new IvParameterSpec(MyStatics.initVector.getBytes("UTF-8"));
//            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
//            byte[] encrypted = cipher.doFinal(value.getBytes());
//
//            return Base64.getEncoder().encodeToString(encrypted);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String symmetricDecrypt(byte[] key, String encrypted) {
//        try {
//            IvParameterSpec iv = new IvParameterSpec(MyStatics.initVector.getBytes("UTF-8"));
//            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//
//            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
//
//            return verifySymmetricEncrypt(new String(original));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private static String verifySymmetricEncrypt(String toDecrypt) {
//        String[] strings = toDecrypt.split(" ");
//        for (int i = 3; i < strings.length; i++) {
//            strings[2] = strings[2] + " " + strings[i];
//        }
//        if (strings[0].equals(digest(strings[2])) && strings[2].length() == new Integer(strings[1])) {
//            return strings[2];
//        } else {
//            return null;
//        }
//    }
////    http://stackoverflow.com/questions/3243018/how-to-load-rsa-private-key-from-file
//
//    public static PrivateKey getPrivateKeyFromFile(String filename)
//            throws Exception {
////        System.out.println(filename);
////        String path = CryptographyUtils.class.getResource(filename).getPath();
//        
//        File f = new File("clients_private_key");
//        
//        
//                
//        ///
//        FileInputStream fis = new FileInputStream(f);
//        DataInputStream dis = new DataInputStream(fis);
//        byte[] keyBytes = new byte[1216];
//        dis.readFully(keyBytes);
//        dis.close();
//
//        PKCS8EncodedKeySpec spec
//                = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        return kf.generatePrivate(spec);
//    }
//
//    public static PublicKey getPublicKeyFromFile(String filename)
//            throws Exception {
//
//        File f = new File(filename);
//        FileInputStream fis = new FileInputStream(f);
//        DataInputStream dis = new DataInputStream(fis);
//        byte[] keyBytes = new byte[(int) f.length()];
//        dis.readFully(keyBytes);
//        dis.close();
//
//        X509EncodedKeySpec spec
//                = new X509EncodedKeySpec(keyBytes);
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        return kf.generatePublic(spec);
//    }
//    
////    public static String assymetricServerDecrypt(String text){
////        try {            
////            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
////            cipher.init(Cipher.DECRYPT_MODE, getPrivateKeyFromFile("servers_private_key"));
////            System.out.println("Length = " + text.getBytes().length);
////            return new String(cipher.doFinal( Base64.getDecoder().decode(text.getBytes())));
////            
////        } catch (NoSuchAlgorithmException ex) {
////            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (NoSuchPaddingException ex) {
////            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (InvalidKeyException ex) {
////            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (Exception ex) {
////            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        return null;
////    }
//    public static String assymetricServerEncrypt(String text){
//        try {            
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyFromFile(MyStatics.servers_public_key));
//            
//            
//            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
//            
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    
//    public static String assymetricClientDecrypt(String text){
//        try {            
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.DECRYPT_MODE, getPrivateKeyFromFile("clients_private_key"));
//            System.out.println("Length = " + text.getBytes().length);
//            return new String(cipher.doFinal( Base64.getDecoder().decode(text.getBytes())));
//            
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    public static String assymetricClientEncrypt(String text){
//        try {            
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyFromFile(MyStatics.clients_public_key));
////            cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyFromFile("clients_public_key.der"));
//            
//            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
//            
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    
//}
