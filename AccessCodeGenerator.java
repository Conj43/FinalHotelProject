import java.util.Random;

public class AccessCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    public static String generateCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return codeBuilder.toString();
    }
}