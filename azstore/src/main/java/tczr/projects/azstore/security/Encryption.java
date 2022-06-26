package tczr.projects.azstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String encryptPassword(String pass){
        byte[] bt = encrypt(pass.getBytes(StandardCharsets.UTF_8), "SHA3-256");
        return byteToHex(bt);
    }

    private static byte[]  encrypt(byte[] in, String algo) {


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
