package billing;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

import edu.mu.billing.SeasonalPricingStrategy;
import edu.mu.hotel.rooms.*;

/**
 * JUnit test class for SeasonalPricingStrategy.
 */
public class SeasonalPricingStrategyTest {

    /**
     * Test case to verify the calculation of price during peak season.
     */
    @Test
    public void testCalculatePrice_PeakSeason() {
        // Arrange
        RoomType roomType = new StandardRoom(); // use standard room as base
        LocalDate peakDate = LocalDate.of(2024, 6, 10); // peak season date
        double expectedPrice = 120.0; // Base price * 1.2

        
        double actualPrice = new SeasonalPricingStrategy().calculatePrice(roomType, peakDate);

       
        assertEquals(expectedPrice, actualPrice, 0.01); // should calculate to 120.0
    }

    /**
     * Test case to verify the calculation of price during non-peak season.
     */
    @Test
    public void testCalculatePrice_NonPeakSeason() {
        // Arrange
        RoomType roomType = new StandardRoom(); // use standard as base
        LocalDate nonPeakDate = LocalDate.of(2024, 4, 10); // not peak season date
        double expectedPrice = 100.0; // Base price

        
        double actualPrice = new SeasonalPricingStrategy().calculatePrice(roomType, nonPeakDate);

        
        assertEquals(expectedPrice, actualPrice, 0.01); // should be 100
    }
}

