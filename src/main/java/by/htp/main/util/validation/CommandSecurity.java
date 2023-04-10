package by.htp.main.util.validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandSecurity {

	private static final String ROLE = "role";
	private static final String ADMIN = "admin";
	private static final String REDACTOR = "redactor";

	public boolean checkCommandSecurity(HttpServletRequest request)
			throws ServletException, IOException {
		
		boolean result = true;
		
		String role = (String) request.getSession().getAttribute(ROLE);
		if (!(ADMIN.equals(role) || (REDACTOR.equals(role)))) {
			result = false;
		}
		return result;
	}
}
