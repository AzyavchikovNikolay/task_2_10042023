package by.htp.main.dao;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public DAOException() {
		super();
	}
	
	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String message, Exception exception) {
		super(message, exception);
	}
	
}
