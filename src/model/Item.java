
package model;

import java.time.LocalDate;

public abstract class Item implements Comparable<Item> {

    private String itemId;
    private String itemName;
    private String category;
    private String description;
    private String location;
    private LocalDate dateReported;
    private boolean claimed;

    // Constructor
    public Item(String itemId, String itemName, String category,
                String description, String location,
                LocalDate dateReported) {

        if (itemId == null || itemId.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be empty");
        }

        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.location = location;
        this.dateReported = dateReported;
        this.claimed = false;
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }

    public boolean isClaimed() {
        return claimed;
    }

    // Setter
    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    // Abstract method
    public abstract String getItemType();

    // Natural sorting by item name
    @Override
    public int compareTo(Item other) {
        return this.itemName.compareToIgnoreCase(other.itemName);
    }

    // Display item details
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nItem ID: ").append(itemId);
        sb.append("\nItem Name: ").append(itemName);
        sb.append("\nCategory: ").append(category);
        sb.append("\nDescription: ").append(description);
        sb.append("\nLocation: ").append(location);
        sb.append("\nDate Reported: ").append(dateReported);
        sb.append("\nType: ").append(getItemType());
        sb.append("\nClaimed: ").append(claimed ? "Yes" : "No");

        return sb.toString();
    }
}
