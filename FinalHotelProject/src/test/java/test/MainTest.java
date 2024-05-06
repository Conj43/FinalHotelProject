package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.mu.test.Main;

public class MainTest {

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

