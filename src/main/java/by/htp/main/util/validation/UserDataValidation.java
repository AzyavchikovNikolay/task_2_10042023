package by.htp.main.util.validation;

import java.util.ArrayList;

import by.htp.main.entity.User;


public interface UserDataValidation {
	ArrayList<String> checkAuthentData(String login, String password);
	ArrayList<String> checkRegistrationData(User user);
}
