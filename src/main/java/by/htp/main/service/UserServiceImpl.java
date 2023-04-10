package by.htp.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.dao.DAOException;
import by.htp.main.dao.NewsDAO;
import by.htp.main.dao.UserDAO;
import by.htp.main.entity.User;
import by.htp.main.util.validation.UserDataValidationImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public String signIn(String login, String password) throws ServiceException {
		
		try {
			UserDataValidationImpl validator = new UserDataValidationImpl();
			ArrayList<String> checkAuthenErrors = validator.checkAuthentData(login, password);
			String errors = "";
			for (String error : checkAuthenErrors) {
				errors = errors + error + ", ";
			}
	
			if (errors.length() != 0) {
				return errors;
			} else {
				return userDAO.logination(login, password);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	
	@Transactional
	public int findIdUser(String login, String password) throws ServiceException {
		
		try {
			return userDAO.idUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	
	private static final String ADMIN = "admin";
	private static final String REDACTOR = "redactor";
	private static final String USER = "user";
	private static final String GUEST = "guest";
	
	@Transactional
	public String registration(User user) throws ServiceException {
		
		try {
			UserDataValidationImpl validator = new UserDataValidationImpl();
			ArrayList<String> registrationErrors = validator.checkRegistrationData(user);
			String errorsResult = "";
			String role = "";
			for (String error : registrationErrors) {
				errorsResult = errorsResult + error + ", ";
			}
	
			if (errorsResult.length() != 0) {
				return errorsResult;
			} else if (userDAO.registration(user)) {
				if(user.getRole().getId() == 1) {
					role = ADMIN;
					}
				if(user.getRole().getId() == 2) {
					role = USER;
					}
				if(user.getRole().getId() == 3) {
					role = REDACTOR;
					}
			} else {
				role = GUEST;
			}
			return role;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
}
