package model;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UniqueCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Set<String> usedCodes = new HashSet<>();

    public static String generateOrderCode() {
        StringBuilder code;
        do {
            code = new StringBuilder(CODE_LENGTH);
            long timestamp = new Date().getTime();
            code.append(Long.toString(timestamp, 36).toUpperCase()); // Using base 36 for timestamp

            while (code.length() < CODE_LENGTH) {
                code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }

            // Ensure code length is exactly 10 characters
            if (code.length() > CODE_LENGTH) {
                code.setLength(CODE_LENGTH);
            }
        } while (usedCodes.contains(code.toString()));

        usedCodes.add(code.toString());
        return code.toString();
    }
}
