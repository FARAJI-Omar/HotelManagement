package Hotel.utils;

import java.util.*;

public class ConsoleHelper {
    public static Scanner scanner = new Scanner(System.in);

    // read a line with a message
    public static String readLine(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine().trim();
    }

    // read integer with a message
    public static int readInt(String message){
        while (true){
            try{
                System.out.printf(message + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Invalid number, try again.");
            }
        }
    }

    // read yes/no input
    public static boolean readYesNo(String message) {
        while (true) {
            String input = readLine(message + " (y for yes/n for no)").toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Enter 'y' or 'n'.");
        }
    }

}
