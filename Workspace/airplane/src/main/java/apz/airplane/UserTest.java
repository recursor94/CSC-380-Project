package apz.airplane;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	@Test
	public void testPassword() {
		String expectedValue = "asd";
		String experimentalValue = new User("Pooper", "asd").getPassword();
		
		assertEquals(expectedValue, experimentalValue);
	}

}
