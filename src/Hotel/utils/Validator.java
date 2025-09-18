package Hotel.utils;

public class Validator {
    // name cant be empty
    public static boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    // validate email format
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // password must be at least 6 characters
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
