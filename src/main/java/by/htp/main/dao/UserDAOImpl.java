package by.htp.main.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.main.entity.News;
import by.htp.main.entity.User;
import by.htp.main.entity.UserDetails;
import by.htp.main.entity.Role;
import by.htp.main.entity.StatusUser;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	private final static String ADMIN = "admin";
	private final static String USER = "user";
	private final static String REDACTOR = "redactor";
	private final static String ACTIVE = "active";

	private final static String STATUS_USER_ACTIVE = "active";
	private final static String USER_ROLE_GUEST = "guest";
	private static final String QUERY_USER = "FROM User WHERE login = :paramLogin and"
			+ " password = :paramPassword and statusUser.statusName = :paramStatus";
	private static final String QUERY_CHANGE_USER = "FROM User WHERE login = :paramLogin or"
			+ " userDetails.phone = :paramPhone or userDetails.email = :paramEmail";
	private static final String QUERY_FIND_USER = "FROM User WHERE login = :paramLogin and"
			+ " password = :paramPassword";
	private final static String PARAM_LOGIN = "paramLogin";
	private final static String PARAM_PASSWORD = "paramPassword";
	private final static String PARAM_PHONE = "paramPhone";
	private final static String PARAM_EMAIL = "paramEmail";

	private final static String PARAM_STATUS = "paramStatus";
	private final static String DATE_FORMAT = "yyyy-MM-dd HH*mm*ss";
	

	
	
	public String logination(String login, String password) throws DAOException{
		
		String userRole = null;
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<User> theQuery = currentSession.createQuery(QUERY_USER, User.class);	
			theQuery.setParameter(PARAM_LOGIN, login);
			theQuery.setParameter(PARAM_PASSWORD, password);
			theQuery.setParameter(PARAM_STATUS, STATUS_USER_ACTIVE);
					
			List<User> user = theQuery.getResultList();
			for(User everyUser:user) {
				userRole = everyUser.getRole().getRoleName();
			}
			if(userRole == null) {
				return USER_ROLE_GUEST;
			} else {
				return userRole;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
		
	public boolean registration(User user) throws DAOException{
		
		String userRole = null;
		String methodResult;
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			
			Query<User> theQuery = currentSession.createQuery(QUERY_CHANGE_USER, User.class);
			theQuery.setParameter(PARAM_LOGIN, user.getLogin());
			theQuery.setParameter(PARAM_PHONE, user.getUserDetails().getPhone());
			theQuery.setParameter(PARAM_EMAIL, user.getUserDetails().getEmail());
					
			List<User> userResult = theQuery.getResultList();
		
			for(User everyUser:userResult) {
				userRole = everyUser.getRole().getRoleName();
			}
			if(userRole == null) {
			
			Date dateOfUserCreate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			String formattedDateOfUserCreate = formatter.format(dateOfUserCreate);
			
			user.getUserDetails().setDateOfCreation(formattedDateOfUserCreate);
			
			Role role = new Role();
			if(user.getRole().getId() == 1) {
				role.setId(1);
				role.setRoleName(ADMIN);
			}
			if(user.getRole().getId() == 2) {
				role.setId(2);
				role.setRoleName(USER);
			}
			if(user.getRole().getId() == 3) {
				role.setId(3);
				role.setRoleName(REDACTOR);
			}
			
			user.setRole(role);
			
			StatusUser statusUser = new StatusUser();
			
			statusUser.setId(1);
			statusUser.setStatusName(ACTIVE);
			
			user.setStatusUser(statusUser);
			
			user.getUserDetails().setUser(user);
			currentSession.saveOrUpdate(user);
	
			return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
		
	public int idUser(String login, String password) throws DAOException{
		
		int userId = 0;
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<User> theQuery = currentSession.createQuery(QUERY_FIND_USER, User.class);	
			theQuery.setParameter(PARAM_LOGIN, login);
			theQuery.setParameter(PARAM_PASSWORD, password);
					
			List<User> user = theQuery.getResultList();
			for(User everyUser:user) {
				userId = (int)Integer.valueOf(everyUser.getId());
			}
			return userId;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}
