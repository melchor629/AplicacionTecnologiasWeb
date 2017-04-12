package app.cosas;

import java.security.MessageDigest;

public class Hash {
    
    
    public static String hash (String cadena) {
        try {
            MessageDigest sha256=MessageDigest.getInstance("SHA-256");
            sha256.update(cadena.getBytes("UTF-8"));
            byte [] digest = sha256.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i<digest.length;i++) {
                sb.append(String.format("%02x",digest[i]));

            }
            return sb.toString();
        } catch(Exception e) {
            return null;
        }
        
    }
}
