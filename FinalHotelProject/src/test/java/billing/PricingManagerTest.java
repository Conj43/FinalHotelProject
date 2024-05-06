package billing;

import org.junit.Test;

import edu.mu.billing.PricingManager;
import edu.mu.hotel.rooms.*;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for PricingManager.
 */
public class PricingManagerTest {

    /**
     * Test case to verify the calculation of price using seasonal pricing strategy.
     */
    @Test
    public void testCalculatePrice_WithSeasonalStrategy() {
        // Arrange
        RoomType roomType = new StandardRoom(); // or any other room type
        LocalDate date = LocalDate.of(2024, 6, 4); // Booking date
        double expectedPrice = 120.0; // Assume the expected price calculated by SeasonalPricingStrategy

        
        double actualPrice = PricingManager.calculatePrice(roomType, date);
       
        assertEquals(expectedPrice, actualPrice, 0.01); // compare actual and expected
    }

    /**
     * Test case to verify the calculation of price using advance booking pricing strategy.
     */
    @Test
    public void testCalculatePrice_WithAdvanceBookingStrategy() {
        // Arrange
        RoomType roomType = new StandardRoom(); // or any other room type
        LocalDate date = LocalDate.of(2024, 8, 6); // Booking date
        double expectedPrice = 90.0; // Assume the expected price calculated by AdvanceBookingPricingStrategy

       
        double actualPrice = PricingManager.calculatePrice(roomType, date);

        
        assertEquals(expectedPrice, actualPrice, 0.01); //comapre actual and expected
    }
}
