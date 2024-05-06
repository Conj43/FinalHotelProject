package billing;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import edu.mu.billing.AdvanceBookingPricingStrategy;
import edu.mu.hotel.rooms.RoomType;
import edu.mu.hotel.rooms.StandardRoom;

import java.time.LocalDate;

/**
 * JUnit test class for the AdvanceBookingPricingStrategy class.
 */
public class AdvanceBookingPricingStrategyTest {

    /**
     * Test case to verify that no discount is applied when booking is not in advance.
     */
    @Test
    public void testCalculatePrice_NoDiscount() {
        AdvanceBookingPricingStrategy pricingStrategy = new AdvanceBookingPricingStrategy();
        RoomType roomType = new StandardRoom(); 
        LocalDate bookingDate = LocalDate.of(2024, 5, 20); // Booking 15 days in advance

        double price = pricingStrategy.calculatePrice(roomType, bookingDate);

       //price shouldn't change
        assertEquals(100.0, price, 0.001);
    }

    /**
     * Test case to verify that the discount is applied when booking is in advance.
     */
    @Test
    public void testCalculatePrice_WithDiscount() {
        AdvanceBookingPricingStrategy pricingStrategy = new AdvanceBookingPricingStrategy();
        RoomType roomType = new StandardRoom(); 
        LocalDate bookingDate = LocalDate.of(2024, 6, 6); // Booking 32 days in advance

        double price = pricingStrategy.calculatePrice(roomType, bookingDate);

       //should get 10% discount
        assertEquals(90.0, price, 0.001); 
    }
}

