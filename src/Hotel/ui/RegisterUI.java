package Hotel.ui;

import Hotel.utils.ConsoleHelper;
import Hotel.utils.Validator;

public class RegisterUI {
	public static String[] register() {
		System.out.println("Please enter your credentials:");
		String email;
		do {
			email = ConsoleHelper.readLine("Email");
			if (!Validator.isValidEmail(email)) {
				System.out.println("Invalid email format. Try again.");
			}
		} while (!Validator.isValidEmail(email));

		String name;
		do {
			name = ConsoleHelper.readLine("Name");
			if (!Validator.isValidName(name)) {
				System.out.println("Name cannot be empty. Try again.");
			}
		} while (!Validator.isValidName(name));

		String password;
		do {
			password = ConsoleHelper.readLine("Password");
			if (!Validator.isValidPassword(password)) {
				System.out.println("Password must be at least 6 characters. Try again.");
			}
		} while (!Validator.isValidPassword(password));

		String[] credentiels = new String[] { email, name, password };
		return credentiels;
	}
}