package Hotel.ui;

import Hotel.utils.ConsoleHelper;
import Hotel.utils.Validator;

public class LoginUI {
    	public static String[] login() {
		System.out.println("Please enter your email and password:");
		String email;
		do {
			email = ConsoleHelper.readLine("Email");
			if (!Validator.isValidEmail(email)) {
				System.out.println("Invalid email format. Try again.");
			}
		} while (!Validator.isValidEmail(email));

		String password;
		do {
			password = ConsoleHelper.readLine("Password");
			if (!Validator.isValidPassword(password)) {
				System.out.println("Password must be at least 6 characters. Try again.");
			}
		} while (!Validator.isValidPassword(password));

		String[] credentiels = new String[] { email, password };
		return credentiels;
	}
}
