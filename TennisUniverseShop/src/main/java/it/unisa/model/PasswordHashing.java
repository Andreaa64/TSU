package main.java.it.unisa.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

    public static String hashPassword(String password) {
        try {
            // Ottieni un'istanza di MessageDigest con l'algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Applica l'hash alla password
            byte[] hash = digest.digest(password.getBytes());

            // Converti il byte array dell'hash in una rappresentazione esadecimale
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Gestisci l'eccezione se l'algoritmo di hashing non è disponibile
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String password = "passwordDaCifrare";
        String hashedPassword = hashPassword(password);
        System.out.println("Password cifrata: " + hashedPassword);
    }
}
