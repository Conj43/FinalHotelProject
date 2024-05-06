package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.mu.test.Main;

public class MainTest {
	
	/*
	 * most methods in main are not really meant to be tested, so we chose to leave those out because still have great code coverage
	 */

    @Test
    void testCalculateDaysStayed() {
        // test dates
        LocalDate checkInDate = LocalDate.of(2024, 6, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 6, 5);

        // expected
        int expectedDays = 4;

        // call calculate days
        int actualDays = Main.calculateDaysStayed(checkInDate, checkOutDate);

        //check if correct
        assertEquals(expectedDays, actualDays, "Number of days stayed should match");
    }
}

