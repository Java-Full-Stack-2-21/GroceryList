package repositories;

import models.GroceryItem;

import java.util.List;

public interface GroceryItemDAO {
    List<GroceryItem> getAllItemsGivenListId(Integer listId);
    void markItemInCart(Integer itemId);
    void createItemForListWithQuantity(GroceryItem item);
    void createItemForListWithoutQuantity(GroceryItem item);
    void deleteItemFromList(Integer itemId);
}
