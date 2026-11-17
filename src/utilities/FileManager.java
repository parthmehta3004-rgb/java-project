
package utilities;

import model.Item;
import model.LostItem;
import model.FoundItem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileManager {

    private static final String LOST_FILE = "lost_items.csv";
    private static final String FOUND_FILE = "found_items.csv";

    // Save lost and found items
    public static void saveItems(
            ArrayList<LostItem> lostItems,
            ArrayList<FoundItem> foundItems) {

        saveLostItems(lostItems);
        saveFoundItems(foundItems);
    }

    private static void saveLostItems(ArrayList<LostItem> items) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOST_FILE))) {

            for (LostItem item : items) {
                writer.println(
                    item.getItemId() + "," +
                    item.getItemName() + "," +
                    item.getCategory() + "," +
                    item.getDescription() + "," +
                    item.getLocation() + "," +
                    item.getDateReported()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving lost items: " + e.getMessage());
        }
    }

    private static void saveFoundItems(ArrayList<FoundItem> items) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FOUND_FILE))) {

            for (FoundItem item : items) {
                writer.println(
                    item.getItemId() + "," +
                    item.getItemName() + "," +
                    item.getCategory() + "," +
                    item.getDescription() + "," +
                    item.getLocation() + "," +
                    item.getDateReported()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving found items: " + e.getMessage());
        }
    }

    // Load saved data when application starts
    public static void loadItems(
            ArrayList<LostItem> lostItems,
            ArrayList<FoundItem> foundItems) {

        loadLostItems(lostItems);
        loadFoundItems(foundItems);
    }

    private static void loadLostItems(ArrayList<LostItem> items) {

        File file = new File(LOST_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 6) {
                    continue;
                }

                LostItem item = new LostItem(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                    LocalDate.parse(data[5])
                );

                items.add(item);
            }

        } catch (Exception e) {
            System.out.println("Error loading lost items: " + e.getMessage());
        }
    }

    private static void loadFoundItems(ArrayList<FoundItem> items) {

        File file = new File(FOUND_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 6) {
                    continue;
                }

                FoundItem item = new FoundItem(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                    LocalDate.parse(data[5])
                );

                items.add(item);
            }

        } catch (Exception e) {
            System.out.println("Error loading found items: " + e.getMessage());
        }
    }
}
