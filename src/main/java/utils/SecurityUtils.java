package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SecurityUtils {

    public static String toDigest(String password) 
    {
        try {
        	// hashing SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // Trasformo la password in byte e calcolo il digest 
            byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // converto in una stringa
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                sb.append(String.format("%02x", b)); 
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-512 non disponibile", e);
        }
    }
}