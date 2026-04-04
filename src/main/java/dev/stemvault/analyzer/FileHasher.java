package dev.stemvault.analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHasher {

    private static final int FULL_BUFFER_SIZE = 65536;

    public static String hash(Path file) {
        byte[] buffer = new byte[FULL_BUFFER_SIZE];
        try (FileInputStream fis = new FileInputStream(file.toFile())) {
            int bytesRead = fis.read(buffer);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer, 0, bytesRead);
            byte[] hashBytes = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
