package com.csc;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        driverProgram();
    }

    public static Integer promptForInteger(
        String promptMessage,
        String errorMessage,
        Integer lowerBound,
        Integer upperBound,
        Integer defaultValue,
        String abortKeyword
    ) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptMessage);
            String userInput = scanner.nextLine().trim();

            //abort keyword
            if (userInput.equalsIgnoreCase(abortKeyword)) {
                System.out.println("Aborted.");
                return null; 
            }

            //handle default 
            if (userInput.equalsIgnoreCase("default")) {
                if (defaultValue != null) {
                    return defaultValue;
                } else {
                    System.out.println("Error: No default value specified.");
                    continue;
                }
            }

            //if input is an integer
            Integer integerValue;
            try {
                integerValue = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
                continue;
            }

            //if input is within bounds
            if ((lowerBound != null && integerValue < lowerBound) ||
                (upperBound != null && integerValue > upperBound)) {
                System.out.println(errorMessage);
                continue;
            }

            return integerValue;
        }
    }

    public static void driverProgram() {
        //No bounds, no default, no abort option
        Integer value1 = promptForInteger(
            "Enter an integer:",
            "Error: Please enter a valid integer.",
            null, null, null, null
        );
        if (value1 != null) {
            System.out.println("You entered: " + value1);
        }

        //Lower bound only, with default value, no abort option
        Integer value2 = promptForInteger(
            "Enter an integer greater than or equal to 10 (or 'default' to use default value of 100):",
            "Error: Integer must be at least 10.",
            10, null, 100, "exit"
        );
        if (value2 != null) {
            System.out.println("You entered: " + value2);
        }

        //Upper bound only, with abort option
        Integer value3 = promptForInteger(
            "Enter an integer less than or equal to 50 (or 'exit' to abort):",
            "Error: Integer must be no more than 50.",
            null, 50, null, "exit"
        );
        if (value3 != null) {
            System.out.println("You entered: " + value3);
        } else {
            System.out.println("User aborted.");
        }

        // Both bounds, with default value and abort option
        Integer value4 = promptForInteger(
            "Enter an integer between 20 and 30 (or 'default' to use default value of 25 or 'exit' to abort):",
            "Error: Integer must be between 20 and 30.",
            20, 30, 25, "exit"
        );
        if (value4 != null) {
            System.out.println("You entered: " + value4);
        } else {
            System.out.println("User aborted.");
        }
    }
}
