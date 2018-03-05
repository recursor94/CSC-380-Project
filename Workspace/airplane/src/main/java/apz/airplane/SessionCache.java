package apz.airplane;

import java.time.LocalTime;

public class SessionCache {
	
	static User user;
	static LocalTime time;
	
	public SessionCache(User user, LocalTime time) {
		this.user = user;
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SessionCache " + user + "_" + time;
	}
	
}
