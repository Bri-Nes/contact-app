import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    private static String directory = "data";
    private static String filename = "contacts.txt";
    private static Input input = new Input();
    private static boolean exitApp = false;

    public static void main(String[] args) {
        System.out.println(makeList());
        createFileIfNotExists(directory, filename);

        do {
            selection(menu());
        } while (!exitApp);

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

    private static void selection(int selected) {

        switch (selected) {
            case 1:
                System.out.println("You choose view.");
//                view(contacts.list());
                break;
            case 2:
                System.out.println("You choose add.");
//                add();
                break;
            case 3:
                System.out.println("You choose search.");
//                search();
                break;
            case 4:
                System.out.println("You choose delete.");
//                delete();
                break;
            case 5:
                System.out.println("You choose to leave me.");
//                exit();
                break;

        }

    }


//        ArrayList<String> items = makeList();
//        System.out.println(items);
//
//        try {
//            writeListToFile(items, directory, filename);
//        } catch(IOException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            readLines(directory, filename);
//        } catch(IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//    }
//
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

    public static ArrayList<String> makeList() {
        ArrayList<String> list = new ArrayList<>();
        Input input = new Input();
        String item;

        do {
            System.out.println("Please input the item you want to add to the list.");
            item = input.getString();
            list.add(item);

        } while(input.yesNo("Do you want to add another item to the list? Press y or Yes to continue"));
        return list;
    }





}
