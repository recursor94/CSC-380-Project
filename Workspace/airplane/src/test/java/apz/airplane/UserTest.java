package apz.airplane;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	@Test
	public void testLogin() {
		UserController uc = new UserController();
		User testUser = new User("user", "pass");
		uc.addUser(testUser);
		
		// actual/expected value			// experimental value
		assertEquals(testUser, uc.login("user", "pass"));
	}

}
