package services;

import models.GroceryList;
import models.User;
import repositories.GroceryListDAO;
import repositories.GroceryListDAOImpl;

import java.util.List;

public class GroceryListService {

    private GroceryListDAO groceryListDAO;

    public GroceryListService(){
        this.groceryListDAO = new GroceryListDAOImpl();
    }

    public GroceryListService(GroceryListDAO groceryListDAO){
        this.groceryListDAO = groceryListDAO;
    }

    public List<GroceryList> getAllGivenUserId(Integer userId){
        return this.groceryListDAO.getAllListsGivenUserId(userId);
    }

    public GroceryList getOne(Integer listId){
        return this.groceryListDAO.getOneList(listId);
    }

    public void createList(GroceryList groceryList){
        this.groceryListDAO.createList(groceryList);
    }

    public void deleteList(Integer listId){
        this.groceryListDAO.deleteList(listId);
    }

    public Boolean isListOurs(User user, Integer listId){
            return getOne(listId).getUserId().equals(user.getId());
    }


}
