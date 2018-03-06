package apz.airplane;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserControllerTest {

	@Test
	public void testFindUser() {
		User user = new User("zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(user, ctrl.findUser("zeg"));
	}
	
	@Test
	public void testRemoveUser() {
		User user = new User("zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.removeUser("zeg"));
	}

}
