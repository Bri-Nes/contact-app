package contacts;

import util.Input;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ContactApp{
    private static String directory = "data";
    private static String filename = "contacts.txt";
    private static Input input = new Input();
    private static boolean exitApp = false;
    private static int itemID;



    public static void createFileIfNotExists(String directory, String filename) {
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            if (Files.notExists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createFileIfNotExists(directory, filename);
        System.out.println("Welcome to our fancy contact list! ");
        try {
            do {
                selection(menu());
            } while (!exitApp);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static int menu() {
        System.out.print(
                        "1. View contacts.\n" +
                        "2. Add a new contact.\n" +
                        "3. Search a contact by name.\n" +
                        "4. Delete an existing contact.\n" +
                        "5. Exit.\n");
        return input.getInt(1, 5);
    }

    public static ArrayList<String> add() throws IOException {
        String firstname;
        String lastname;
        String phonenumber;
        String email;
        ArrayList<String> list = new ArrayList<>();
        do {
            firstname = input.getString("Type the first name of the contact you'd like to add: ");
            lastname = input.getString("Type the last name of the contact: ");
            phonenumber = input.getString("Type the phone number of the contact: ");
            email = input.getString("And finally, the email: ");
            Contact contact = new Contact(firstname,lastname,phonenumber,email);
            list.add(contact.firstName  + " " +contact.lastName + "   |   " + contact.phoneNumber + "   |   " + contact.email);
        } while(input.yesNo("Do you want to add another contact to the list? [yes/no]: "));
        writeListToFile(list, directory, filename);
        return list;
    }

    private static void selection(int selected) throws IOException {
        switch (selected) {
            case 1:
                readLines("data", "contacts.txt");
                break;
            case 2:
                add();
                break;
            case 3:
                searchByName(directory,filename,input.getString("Search contact: "));
                break;
            case 4:
                delete(directory,filename,input.getString("Type the name of the contact you'd like to delete: "));
                break;
            case 5:
                System.out.println("Goodbye!");
                exitApp = true;
                break;
        }
    }



    public static void writeListToFile(ArrayList<String> list, String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, list, StandardOpenOption.APPEND);
    }
    public static void writeListToFile(List<String> list, String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, list);



    }

    public static void readLines(String directory, String filename) throws IOException {
        Path filePath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filePath);
        for(String item : list) {
            System.out.println(item);
        }
    }

    public static void searchByName(String directory, String filename, String name) throws IOException {
        name = name.toLowerCase();
        Path filePath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filePath);


        for(String contact : list) {
            if(contact.toLowerCase().contains(name)) {
                System.out.println(contact);
            }
        }
    }

    public  static void delete( String directory, String filename, String name) throws IOException {
        Path filePath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filePath);
        for(String item : list) {
            if(item.contains(name)) {
                System.out.println(item);
                boolean choice = input.yesNo("Would you like to delete this? ");
                if (choice) {
                    itemID = list.indexOf(item);
                    System.out.println("Deleted!");
                } else {
                    break;
                }
            }
        }
        list.remove(itemID);
        writeListToFile(list,directory,filename);
    }
}
