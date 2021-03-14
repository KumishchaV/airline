package by.htp.airline.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {

	private static final UserDataValidator instance = new UserDataValidator();

	public UserDataValidator() {
	}

	public static UserDataValidator getInstance() {
		return instance;
	}

	public boolean checkEmailPassword(String email, String password) {

		String regexEmail;
		String regexPass;
		Pattern patternEmail;
		Pattern patternPass;
		Matcher matcherEmail;
		Matcher matcherPass;

		regexEmail = "^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		regexPass = "^[a-zA-Z0-9]+$";

		patternEmail = Pattern.compile(regexEmail);
		matcherEmail = patternEmail.matcher(email);

		patternPass = Pattern.compile(regexPass);
		matcherPass = patternPass.matcher(password);

		if (matcherEmail.matches() && matcherPass.matches()) {

			return true;
		} else {

			return false;
		}
	}

}
