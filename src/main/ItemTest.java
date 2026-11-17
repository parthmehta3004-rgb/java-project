
package main;

import model.LostItem;
import model.FoundItem;
import model.Item;

import java.time.LocalDate;

public class ItemTest {

    public static void main(String[] args) {

        Item lost = new LostItem(
            "L101",
            "Black Wallet",
            "Accessories",
            "Black leather wallet",
            "College Library",
            LocalDate.now()
        );

        Item found = new FoundItem(
            "F101",
            "Blue Water Bottle",
            "Station Canteen",
            "Blue bottle with a sticker",
            "College Canteen",
            LocalDate.now()
        );

        System.out.println("LOST ITEM:");
        System.out.println(lost);

        System.out.println("\nFOUND ITEM:");
        System.out.println(found);
    }
}
