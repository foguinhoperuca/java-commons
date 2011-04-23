package org.awknet.commons.security;

import org.awknet.commons.model.User;

public interface IAuthentication {

	public void login(User user);

	public void logout();
	
	//public User resetPassword(User user);
}
