package apz.airplane.OTHERS;

import org.junit.jupiter.api.Test;

import apz.airplane.User;
import apz.airplane.UserController;
import junit.framework.TestCase;

public class UserControllerTest extends TestCase {

	@Test
	public void testFindUser() {
		User user = new User("zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(user, ctrl.findUser("zeg"));
	}
	
	@Test
	public void findUserFail() {
		User user = new User("zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		
		//The user name being passed in is not in the user list, so it should not be found
		assertEquals(null, ctrl.findUser("zerg"));
	}
	
	@Test
	public void testRemoveUser() {
		User user = new User("zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.removeUser("zeg"));
	}
	
	@Test
	public void removeUserFail() {
		User user = new User("zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		
		//The user name being passed in is not in the user list, so it should not be removed
		assertEquals(false, ctrl.removeUser("zerg"));
	}

}
