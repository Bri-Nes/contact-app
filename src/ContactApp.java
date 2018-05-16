import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ContactApp {
    private static String directory = "data";
    private static String filename = "contacts.txt";
    private static Input input = new Input();
    private static boolean exitApp = false;

    public static void main(String[] args) {
        createFileIfNotExists(directory, filename);
        try {
            do {
                selection(menu());
            } while (!exitApp);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static int menu() {
        System.out.println("Welcome to our fancy contact list! ");
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
            list.add(contact.firstName + "   |   " + contact.lastName + "   |   " + contact.phoneNumber + "   |   " + contact.email);
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
//                System.out.println("You choose add.");
                add();
                break;
            case 3:
//                System.out.println("You choose search.");
                searchByName(input.getString("Search contact: "));
                break;
            case 4:
                System.out.println("You choose delete.");
                delete(input.getString("delete contact: "));
                break;
            case 5:
                System.out.println("You choose to leave me.");
                exitApp = true;
                break;
        }
    }

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

    public static void writeListToFile(ArrayList<String> list, String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, list, StandardOpenOption.APPEND);
    }

    public static void readLines(String directory, String filename) throws IOException {
        Path filePath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filePath);
        for(String item : list) {
            System.out.println(item);
        }
    }

    public static void searchByName(String name) throws IOException {
        Path filePath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filePath);
        for(String item : list) {
            if(item.toLowerCase().contains(name.toLowerCase())) {
                System.out.println(item);
                }
        }
    }

    public  static void delete(String name) throws IOException {
        Path filePath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filePath);
        for(String item : list) {
            if(item.toLowerCase().contains(name.toLowerCase())) {
                list.remove(item);
            }
        }

    }
//        public static void searchByTitle() {
//            System.out.println("| Search by title: ");
////        input.getString();
//            String search = input.getString();
//            for(Movie movie : movieArray) {
//                if(movie.getName().toLowerCase().contains(search.toLowerCase())) {
//                    displayMovie(movie);
//                }
//            }
//        }

}
