package by.htp.main.dao;

import by.htp.main.entity.User;

public interface UserDAO {

	public String logination(String login, String password) throws DAOException;
	public boolean registration(User user) throws DAOException;
	//public String getRole(String login, String password) throws DAOException;
	public int idUser(String login, String password) throws DAOException;
}
