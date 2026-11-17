
package main;

import model.Item;
import model.LostItem;
import model.FoundItem;

import utilities.FileManager;
import threading.SaveTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    // Store lost and found products
    static ArrayList<LostItem> lostItems = new ArrayList<>();
    static ArrayList<FoundItem> foundItems = new ArrayList<>();

    // Scanner for user input
    static Scanner sc = new Scanner(System.in);

    // Generate unique item IDs
    static int lostCounter = 101;
    static int foundCounter = 101;

    // Background thread manager
    static ExecutorService executor =
            Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        // Load previously saved products
        FileManager.loadItems(lostItems, foundItems);

        // Update counters to avoid duplicate IDs
        lostCounter = getNextId(lostItems, "L");
        foundCounter = getNextId(foundItems, "F");

        System.out.println("\nSaved products loaded successfully!");

        int choice = 0;

        do {
            System.out.println("\n================================");
            System.out.println("       LOST AND FOUND SYSTEM");
            System.out.println("================================");

            System.out.println("1. Add Lost Product");
            System.out.println("2. Add Found Product");
            System.out.println("3. View Lost Products");
            System.out.println("4. View Found Products");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addLostItem();
                        break;

                    case 2:
                        addFoundItem();
                        break;

                    case 3:
                        viewLostItems();
                        break;

                    case 4:
                        viewFoundItems();
                        break;

                    case 5:
                        System.out.println(
                            "\nClosing Lost and Found System..."
                        );
                        break;

                    default:
                        System.out.println(
                            "Invalid choice! Please select 1 to 5."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Please enter a valid number!"
                );

            }

        } while (choice != 5);

        // Wait for background saving tasks
        executor.shutdown();

        try {

            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {

                executor.shutdownNow();

                System.out.println(
                    "Some saving tasks could not finish in time."
                );
            }

        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();

        }

        sc.close();

        System.out.println(
            "Thank you for using Lost and Found System!"
        );
    }

    // Generate next item ID
    public static int getNextId(
            ArrayList<? extends Item> items,
            String prefix) {

        int maximum = 100;

        for (Item item : items) {

            String id = item.getItemId();

            if (id.startsWith(prefix)) {

                try {

                    int number = Integer.parseInt(
                        id.substring(prefix.length())
                    );

                    if (number > maximum) {
                        maximum = number;
                    }

                } catch (NumberFormatException e) {
                    // Ignore incorrectly formatted IDs
                }
            }
        }

        return maximum + 1;
    }

    // Save products using background thread
    public static void saveDataInBackground() {

        // Create copies of current lists
        ArrayList<LostItem> lostCopy =
                new ArrayList<>(lostItems);

        ArrayList<FoundItem> foundCopy =
                new ArrayList<>(foundItems);

        // Submit background saving task
        executor.execute(
            new SaveTask(lostCopy, foundCopy)
        );
    }

    // Add lost product
    public static void addLostItem() {

        System.out.println("\n----- REPORT LOST PRODUCT -----");

        try {

            System.out.print("Enter product name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter category: ");
            String category = sc.nextLine().trim();

            System.out.print("Enter description: ");
            String description = sc.nextLine().trim();

            System.out.print("Enter lost location: ");
            String location = sc.nextLine().trim();

            System.out.print("Enter lost date (YYYY-MM-DD): ");
            String dateInput = sc.nextLine().trim();

            // Validate empty inputs
            if (name.isEmpty() || category.isEmpty()
                    || description.isEmpty()
                    || location.isEmpty()) {

                System.out.println(
                    "All product details are required!"
                );

                return;
            }

            LocalDate date = LocalDate.parse(dateInput);

            // Create LostItem object
            LostItem item = new LostItem(
                "L" + lostCounter,
                name,
                category,
                description,
                location,
                date
            );

            lostItems.add(item);
            lostCounter++;

            System.out.println(
                "\nLost product added successfully!"
            );

            System.out.println(item);

            // Save in background
            saveDataInBackground();

        } catch (Exception e) {

            System.out.println(
                "Error adding lost product: " + e.getMessage()
            );

        }
    }

    // Add found product
    public static void addFoundItem() {

        System.out.println("\n----- REPORT FOUND PRODUCT -----");

        try {

            System.out.print("Enter product name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter category: ");
            String category = sc.nextLine().trim();

            System.out.print("Enter description: ");
            String description = sc.nextLine().trim();

            System.out.print("Enter found location: ");
            String location = sc.nextLine().trim();

            System.out.print("Enter found date (YYYY-MM-DD): ");
            String dateInput = sc.nextLine().trim();

            // Validate empty inputs
            if (name.isEmpty() || category.isEmpty()
                    || description.isEmpty()
                    || location.isEmpty()) {

                System.out.println(
                    "All product details are required!"
                );

                return;
            }

            LocalDate date = LocalDate.parse(dateInput);

            // Create FoundItem object
            FoundItem item = new FoundItem(
                "F" + foundCounter,
                name,
                category,
                description,
                location,
                date
            );

            foundItems.add(item);
            foundCounter++;

            System.out.println(
                "\nFound product added successfully!"
            );

            System.out.println(item);

            // Save in background
            saveDataInBackground();

        } catch (Exception e) {

            System.out.println(
                "Error adding found product: " + e.getMessage()
            );

        }
    }

    // View lost products
    public static void viewLostItems() {

        System.out.println("\n----- LOST PRODUCTS -----");

        if (lostItems.isEmpty()) {

            System.out.println(
                "No lost products reported yet."
            );

            return;
        }

        for (LostItem item : lostItems) {

            System.out.println(item);
            System.out.println("--------------------------");

        }
    }

    // View found products
    public static void viewFoundItems() {

        System.out.println("\n----- FOUND PRODUCTS -----");

        if (foundItems.isEmpty()) {

            System.out.println(
                "No found products reported yet."
            );

            return;
        }

        for (FoundItem item : foundItems) {

            System.out.println(item);
            System.out.println("--------------------------");

        }
    }
}
