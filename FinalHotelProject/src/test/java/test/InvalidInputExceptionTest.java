package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.mu.test.InvalidInputException;

/**
 * JUnit test class for InvalidInputException.
 */
public class InvalidInputExceptionTest {

    /**
     * Test case to verify creating an InvalidInputException.
     */
    @Test
    void testCreateInvalidInputException() {
        
        String message = "Invalid input provided";

        // create a sample InvalidInputException
        InvalidInputException exception = new InvalidInputException(message);

        // check that the exception is not null
        assertNotNull(exception, "Exception should not be null");

        // check that the exception message matches the input message
        assertEquals(message, exception.getMessage(), "Exception message should match");
    }
}

