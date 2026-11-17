
package model;

import java.time.LocalDate;

public class FoundItem extends Item {

    public FoundItem(String itemId, String itemName, String category,
                     String description, String location,
                     LocalDate dateReported) {

        super(itemId, itemName, category, description,
              location, dateReported);
    }

    @Override
    public String getItemType() {
        return "FOUND";
    }
}
