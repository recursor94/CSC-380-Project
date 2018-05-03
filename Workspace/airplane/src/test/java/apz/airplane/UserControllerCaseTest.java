package apz.airplane;

import static org.junit.Assert.*;
import org.junit.Test;
import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class UserControllerCaseTest {

	@Test
	public void testFindAndAddUser() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(user, ctrl.findUser("Pooper"));
	}
	
	@Test
	public void findUserFail() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(null, ctrl.findUser("zerg"));
	}
	
	@Test
	public void testRemoveUser() {
		User user = new User("asd@asd.org", "zeg", "password");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.removeUser("zeg"));
	}
	
	@Test
	public void removeUserFail() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(false, ctrl.removeUser("zerg"));
	}
	
	@Test
	public void userExistsTest() {
		User user = new User("asd@asd.org", "zeg", "pass");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.userExists("zeg"));
		
	}
	
	@Test
	public void userNonExistsTest() {
		User user = new User("asd@asd.org", "zeg", "pass");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(false, ctrl.userExists("zerg"));
		
	}
}
