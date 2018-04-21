package apz.airplane;

import static org.junit.Assert.*;

import org.junit.Test;

import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class UserControllerCaseTest {

	@Test
	public void testFindAndAddUser() {
		User user = new User("asd", "zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(user, ctrl.findUser("zeg"));
	}
	
	@Test
	public void findUserFail() {
		User user = new User("asd", "zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(null, ctrl.findUser("zerg"));
	}
	
	@Test
	public void testRemoveUser() {
		User user = new User("asd", "zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.removeUser("zeg"));
	}
	
	@Test
	public void removeUserFail() {
		User user = new User("asd", "zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(false, ctrl.removeUser("zerg"));
	}
	
	@Test
	public void userExistsTest() {
		User user = new User("asd", "zeg", "pass");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.userExists("zeg"));
		
	}
	@Test
	public void userNonExistsTest() {
		User user = new User("asd", "zeg", "pass");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(false, ctrl.userExists("zerg"));
		
	}

}
