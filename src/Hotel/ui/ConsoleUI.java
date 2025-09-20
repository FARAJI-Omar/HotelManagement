package Hotel.ui;

import Hotel.service.AuthService;
import Hotel.service.serviceImp.AuthServiceImpl;
import Hotel.service.HotelService;
import Hotel.service.serviceImp.HotelServiceImpl;
import Hotel.repository.repoImp.InMemoryHotelRepository;
import Hotel.repository.repoImp.InMemoryUserRepository;
import Hotel.entity.User;
import Hotel.utils.*;

import java.util.Optional;

public class ConsoleUI {
    public static boolean isClient = false;
    public static boolean isHotelier = false;
    public static User currentUser = null;  // track logged-in user

    private static final AuthServiceImpl authService = new AuthServiceImpl(new InMemoryUserRepository());
    public static final HotelService hotelService = new HotelServiceImpl(new InMemoryHotelRepository());

    public static void start() {
        while (true) {
            System.out.println("\n==== Welcome to Hotel Reservation App ====\n");
            System.out.println("1. Espace Client");
            System.out.println("2. Espace Hotelier");
            System.out.println("3. Exit");

            int choice = ConsoleHelper.readInt("Choose an option");

            switch (choice) {
                case 1 -> {
                    isClient = true;
                    isHotelier = false;
                    authSpace();
                    return; // exit the loop after handling the choice
                }
                case 2 -> {
                    isHotelier = true;
                    isClient = false;
                    authSpace();
                    return;
                }
                case 3 -> {
                    System.out.println("Goodbye!");
                    return; // exit app
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    public static void authSpace() {
        while (true) {
            System.out.println("\n====== Auth Menu ======\n");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. <- Back");
            int choice = ConsoleHelper.readInt("Choose an option");

            switch (choice) {
                case 1:
                    String[] loginCredentials = LoginUI.login();
                    Optional<User> loggedUser = authService.login(loginCredentials[0], loginCredentials[1]);

                    if (loggedUser.isPresent()) {
                        currentUser = loggedUser.get();
                        if (isClient && currentUser.getRole() == User.Role.CLIENT) {
                            System.out.println("Welcome " + currentUser.getName() + " to the Client Dashboard!");
                            ClientSpace.clientSpace();
                            return;
                        } else if (isHotelier && currentUser.getRole() == User.Role.ADMIN) {
                            System.out.println("Welcome " + currentUser.getName() + " to the Hotelier Dashboard!");
                            HotelierSpace.hotelierSpace();
                            return;
                        } else {
                            System.out.println("Access denied.");
                            currentUser = null;
                        }
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case 2:
                    String[] registerCredentials = RegisterUI.register();
                    User.Role registerRole = isClient ? User.Role.CLIENT : User.Role.ADMIN;
                    if (authService.emailExists(registerCredentials[0], registerRole)) {
                        System.out.println("Sorry this email already exists");
                    } else {
                        authService.register(registerCredentials[0], registerCredentials[1], registerCredentials[2], registerRole);
                        System.out.println("Registration successful! Please login.");
                    }
                    break;
                case 3: {
                    ConsoleUI.start();
                    return;
                }
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

    }
}
