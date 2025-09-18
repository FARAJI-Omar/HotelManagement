package Hotel.ui;

import Hotel.service.AuthService;
import Hotel.service.serviceImp.AuthServiceImpl;
import Hotel.repository.repoImp.InMemoryUserRepository;
import Hotel.entity.User;
import Hotel.utils.*;

public class ConsoleUI {
    public static boolean isClient = false;
    public static boolean isHotelier = false;
    private static AuthServiceImpl authService = new AuthServiceImpl(new InMemoryUserRepository());

    public static void start() {

            System.out.println("\n=== Welcome to Hotel Reservation App ===");
            System.out.println("1. Espace Client");
            System.out.println("2. Espace Hotelier");
            System.out.println("3. Exit");

            int choice = ConsoleHelper.readInt("Choose an option");

            switch (choice) {
                case 1 -> {
                    isClient = true;
                    isHotelier = false;
                    authSpace();
                    break;
                }
                case 2 -> {
                    isHotelier = true;
                    isClient = false;
                    authSpace();
                    break;
                }
                case 3 -> {
                    System.out.println("Goodbye!");
                    return; // exit app
                }
                default -> System.out.println("Invalid choice, try again.");
            }

    }

    public static void authSpace() {
        System.out.println("\n=== Auth Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. <- Back");
        int choice = ConsoleHelper.readInt("Choose an option");
        switch (choice) {
            case 1: 
            String[] loginCredentials = LoginUI.login();
            if (authService.login(loginCredentials[0], loginCredentials[1]).isPresent()) {
                if (isClient) {
                    System.out.println("Welcome to the Client Dashboard!");
                } else {
                    System.out.println("Welcome to the Hotelier Dashboard!");
                }
            } else {
                System.out.println("Login failed. Please try again.");
            }
            break;
            case 2: 
            String[] registerCredentials = RegisterUI.register();
            User.Role registerRole = isClient ? User.Role.CLIENT : User.Role.ADMIN;
            if (authService.emailExists(registerCredentials[0], registerRole)){
                System.out.println("Sorry this email already exists");
            } else {
                    try {
                        authService.register(registerCredentials[0], registerCredentials[1], registerCredentials[2], registerRole);
                        if (isClient) {
                            System.out.println("Welcome to the Client Dashboard!");
                        } else {
                            System.out.println("Welcome to the Hotelier Dashboard!");
                        }
                    } catch (Exception e) {
                        System.out.println("Registration failed: " + e.getMessage());
                    }
                }
            break;  
            case 3: {
                // go back to main menu
                ConsoleUI.start();
                break;
            }
            default: System.out.println("Invalid choice, try again.");
        }

    }
}
