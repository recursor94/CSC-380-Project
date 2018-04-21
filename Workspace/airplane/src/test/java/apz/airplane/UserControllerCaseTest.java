package apz.airplane;

import static org.junit.Assert.*;

import org.junit.Test;

import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class UserControllerCaseTest {

	@Test
	public void testFindAndAddUser() {
<<<<<<< HEAD
		User user = new User("asd", "zeg", "password");
=======
		User user = new User("asd@asd.org", "Pooper", "Scooper");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(user, ctrl.findUser("Pooper"));
	}
	
	@Test
	public void findUserFail() {
<<<<<<< HEAD
		User user = new User("asd", "zeg", "password");
=======
		User user = new User("asd@asd.org", "Pooper", "Scooper");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(null, ctrl.findUser("zerg"));
	}
	
	@Test
	public void testRemoveUser() {
<<<<<<< HEAD
		User user = new User("asd", "zeg", "password");
=======
		User user = new User("asd@asd.org", "zeg", "password");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.removeUser("zeg"));
	}
	
	@Test
	public void removeUserFail() {
<<<<<<< HEAD
		User user = new User("asd", "zeg", "password");
=======
		User user = new User("asd@asd.org", "Pooper", "Scooper");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(false, ctrl.removeUser("zerg"));
	}
	
	@Test
	public void userExistsTest() {
<<<<<<< HEAD
		User user = new User("asd", "zeg", "pass");
=======
		User user = new User("asd@asd.org", "zeg", "pass");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(true, ctrl.userExists("zeg"));
		
	}
	@Test
	public void userNonExistsTest() {
<<<<<<< HEAD
		User user = new User("asd", "zeg", "pass");
=======
		User user = new User("asd@asd.org", "zeg", "pass");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		assertEquals(false, ctrl.userExists("zerg"));
		
	}

}
