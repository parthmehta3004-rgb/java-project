
package threading;

import model.LostItem;
import model.FoundItem;
import utilities.FileManager;

import java.util.ArrayList;

public class SaveTask implements Runnable {

    private ArrayList<LostItem> lostItems;
    private ArrayList<FoundItem> foundItems;

    public SaveTask(
            ArrayList<LostItem> lostItems,
            ArrayList<FoundItem> foundItems) {

        this.lostItems = lostItems;
        this.foundItems = foundItems;
    }

    @Override
    public void run() {

        System.out.println(
            "Background thread: Saving product data..."
        );

        FileManager.saveItems(lostItems, foundItems);

        System.out.println(
            "Background thread: Data saved successfully!"
        );
    }
}
