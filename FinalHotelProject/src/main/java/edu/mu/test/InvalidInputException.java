package edu.mu.test;


/*
 * class to throw invalid unput exceptions
 */
public class InvalidInputException extends Exception {
    private static final long serialVersionUID = 1L;

    /*
     * paramaterized constructor that throws an exception
     */
	public InvalidInputException(String message) {
        super(message);
    }
}
