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
		assertEquals(null, uc.login("user", "pass34"));
	}
	
	@Test
	public void testValidPassword() {
		
		User testUser = new User("user", "password");	
		assertEquals(true, testUser.validatePassword("password"));
	}
	
	@Test
	public void testInvalidPassword() {
		User testUser = new User("test", "password123");
		assertEquals(false, testUser.validatePassword("wordpass"));
	}

}
