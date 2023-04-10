package by.htp.main.service;

import by.htp.main.entity.User;

public interface UserService {

	public String signIn(String login, String password) throws ServiceException;
	public int findIdUser(String login, String password) throws ServiceException;
	public String registration(User user) throws ServiceException;
}
