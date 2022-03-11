package services;

import models.GroceryItem;
import repositories.GroceryItemDAO;
import repositories.GroceryItemDAOImpl;

import java.util.List;

public class GroceryItemService {
    GroceryItemDAO groceryItemDAO;

    public GroceryItemService(){
        this.groceryItemDAO = new GroceryItemDAOImpl();
    }

    public GroceryItemService(GroceryItemDAO groceryItemDAO){
        this.groceryItemDAO = groceryItemDAO;
    }

    public List<GroceryItem> getAllGivenListId(Integer listId){
        return this.groceryItemDAO.getAllItemsGivenListId(listId);
    }

    public void markItemInCart(Integer itemId){
        this.groceryItemDAO.markItemInCart(itemId);
    }

    public void deleteItemInCart(Integer itemId){
        this.groceryItemDAO.deleteItemFromList(itemId);
    }

    public void createItem(GroceryItem groceryItem){
        if(groceryItem.getQuantity() == null){
            this.groceryItemDAO.createItemForListWithoutQuantity(groceryItem);
        }else{
            this.groceryItemDAO.createItemForListWithQuantity(groceryItem);
        }
    }


}
