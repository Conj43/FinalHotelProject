package edu.mu.hotel;

import java.util.Random;


/*
 * cladd used to generate access code for amenities
 */
public class AccessCodeGenerator {
	
	/*
	 * character set and length of code
	 */
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int CODE_LENGTH = 6;

    
    
    /*
     * method to generate random code from charaters of length code_length
     * @return random string of length code_length
     */
    public static String generateCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))); //append characters to code
        }
        return codeBuilder.toString(); //returns a string which is a random code
    }
}