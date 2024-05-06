package customer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.mu.customer.*;

class LoyaltyMemberSignUpTest {
	
	/*
	 * positive test for signing up for loyalty points
	 */

	@Test
	void testSignUp()
	{
		Customer customer = new Customer();
		LoyaltyMemberSignUp.signUp(customer);
		assertTrue(customer.isRewardsMember());
		assertEquals(20, customer.getRewardsPoints());
}
}
