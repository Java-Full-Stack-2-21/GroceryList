package services;

import models.GroceryList;
import models.User;
import repositories.GroceryListDAO;
import repositories.GroceryListDAOImpl;

import java.util.Collections;
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

    public GroceryList createList(GroceryList groceryList){

        if(this.groceryListDAO.createList(groceryList)){
            List<GroceryList> lists = groceryListDAO.getAllListsGivenUserId(groceryList.getUserId());

            Collections.sort(lists, (a, b) -> a.getId().compareTo(b.getId()));
            return lists.get(lists.size() - 1);
        }

        return null;
    }

    public void deleteList(Integer listId){
        this.groceryListDAO.deleteList(listId);
    }

    public Boolean isListOurs(User user, Integer listId){
            GroceryList groceryList = this.groceryListDAO.getOneList(listId);
            return groceryList.getUserId().equals(user.getId());
    }


}
