package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

//La uso sia in fase di registrazione (per salvare la password)

public class SecurityUtils {

    public static String toDigest(String password) 
    {
        try {
        	//l'algoritmo di hashing SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // Trasformo la password in byte e calcolo il digest 
            byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Ora converto quei byte in una stringa esadecimale leggibile.
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                sb.append(String.format("%02x", b)); // trasforma in carattere esadecimali
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-512 non disponibile", e);
        }
    }
}