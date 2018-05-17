package util;
import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);
    boolean val;

    public String getString() {
//        System.out.println("Type a String: ");
        String input = scanner.next();
        System.out.println(input);
        return input;
    }

    public String getString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public boolean yesNo() {
        System.out.println("Yes or No: ");
        String input = scanner.next();
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            val = true;
        } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            val = false;
        } else {
            System.out.println("Oops, I'm not sure what you mean by that.. try again!");
            yesNo();
        }
        return val;
    }

    public boolean yesNo(String message) {
        System.out.println(message);
        String input = scanner.next();
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            val = true;
        } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            val = false;
        } else {
            System.out.println("Oops, I'm not sure what you mean by that.. try again!");
            yesNo(message);
        }
        return val;
    }

    public int getInt(int min, int max) {
        System.out.println("Type a number between " + min + " and " + max + ": ");
        try {
            String input = scanner.nextLine();
            int inputval = Integer.valueOf(input);
            if (inputval > max) {
                System.out.println("Try again... remember the numbers to choose from are " + min + " and " + max);
                getInt(min, max);
            } else if (inputval < min) {
                System.out.println("Try again... remember the numbers to choose from are " + min + " and " + max);
                getInt(min, max);
            } else {
                System.out.println(inputval);
                return inputval;
            }
            return inputval;
        } catch (Exception e) {
            System.out.println("That must not have been a number, try again!");
            return getInt(min, max);
        }
    }

    public int getInt() {
        try {
            String inputInt  = this.scanner.nextLine();
            return Integer.valueOf(inputInt);
        } catch(NumberFormatException e) {
            System.out.println("Try again. Fail. ");
            return getInt();
        }
    }

    public double getDouble(double min, double max) {
        System.out.println("Type a decimal number between "+min+" and "+max+": ");
        double input = scanner.nextDouble();
        if (input > max) {
            System.out.println("Try again... remember the numbers to choose from are "+min+" and "+max);
            getDouble(min, max);
        } else if (input < min) {
            System.out.println("Try again... remember the numbers to choose from are "+min+" and "+max);
            getDouble(min, max);
        } else {
            System.out.println(input);
            return input;
        }
        return input;
    }

    public double getDouble() {
        System.out.println("Type a decimal: ");
        double input = scanner.nextDouble();
        System.out.println(input);
        return input;
    }
}