package apz.airplane;

import java.time.LocalDateTime;

public class SessionCache {
	
	private User user;
	private LocalDateTime time;
	
	public SessionCache(User user, LocalDateTime time) {
		this.user = user;
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
}
