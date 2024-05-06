package hotel;





import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.mu.hotel.GsonReservation;
import edu.mu.hotel.ServiceRequest;

public class GsonReservationTest {

	
	
	/**
     * Test case to verify the default constructor.
     */
    @Test
    void testDefaultConstructor() {
        GsonReservation reservation = new GsonReservation();

        assertNotNull(reservation, "Reservation object should not be null");
    }
    
    

}
