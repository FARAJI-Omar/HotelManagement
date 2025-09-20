package Hotel.ui;

import Hotel.utils.ConsoleHelper;

public class ClientSpace {
    public static void clientSpace() {
        while (true) {
            System.out.println("\n====== Client Space ======");
            System.out.println("1. Reserve Room");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservation History");
            System.out.println("4. Rate Hotel");
            System.out.println("5. Update Profile");
            System.out.println("6. Logout");

            int choice = ConsoleHelper.readInt("Choose an option");

            switch (choice) {
                case 1 -> {
                    System.out.println("Reserve Room!");
                    return;
                }
                case 2 -> {
                    System.out.println("Cancel Reservation!");
                    return;
                }
                case 3 -> {
                    System.out.println("Rate Hotel!");
                    return;
                }
                case 4 -> {
                    System.out.println("view hotel list!");
                    return;
                }
                case 5 -> {
                    System.out.println("Update Profile!");
                    return;
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    ConsoleUI.isHotelier = false;
                    ConsoleUI.start();
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
