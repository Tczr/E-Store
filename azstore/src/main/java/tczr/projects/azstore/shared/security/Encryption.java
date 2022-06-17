package tczr.projects.azstore.shared.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String encryptPassword(String pass){
        byte[] bt = encrypte(pass.getBytes(StandardCharsets.UTF_8), "SHA3-256");
        return byteToHex(bt);
    }

    private static byte[]  encrypte(byte[] in, String algo) {


        MessageDigest md ;
        try {
            md = MessageDigest.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw  new IllegalArgumentException();
        }
        byte[] result = md.digest(in);
        return result;
    }

    private static String byteToHex(byte[] bytes){
        StringBuilder msg = new StringBuilder();

        for(byte by : bytes){
            msg.append(String.format("%02x",by));
        }
        return msg.toString();
    }
}
