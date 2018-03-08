package apz.airplane;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserControllerCaseTest {

	@Test
	public void testFindAndAddUser() {
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
		assertEquals(false, ctrl.removeUser("zerg"));
	}

}
