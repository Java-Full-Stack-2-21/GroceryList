package controllers;

import io.javalin.http.Context;
import models.GroceryList;
import models.JsonResponse;
import services.GroceryListService;

import java.util.List;

public class GroceryListController {
    GroceryListService groceryListService;

    public GroceryListController(){
        this.groceryListService = new GroceryListService();
    }

    public GroceryListController(GroceryListService groceryListService){
        this.groceryListService = groceryListService;
    }

    public void displayAllListForUser(Context context){
        Integer userId = Integer.parseInt(context.queryParam("userId"));

        List<GroceryList> lists = groceryListService.getAllGivenUserId(userId);
        context.json(new JsonResponse(true, "listing all list for user " + userId, lists));
    }

    public void createList(Context context){
        GroceryList groceryList = context.bodyAsClass(GroceryList.class);

        GroceryList groceryListFromDb = groceryListService.createList(groceryList);
        if(groceryListFromDb == null){
            context.json(new JsonResponse(false, "an error has occurred for creating list", null));
        }else{
            context.json(new JsonResponse(true, "list created for user " + groceryList.getUserId(), groceryListFromDb));
        }


    }

    public void deleteList(Context context) {
        Integer listId = Integer.parseInt(context.pathParam("listId"));

        groceryListService.deleteList(listId);

        context.json(new JsonResponse(true, "list id " + listId + " deleted if exists", null));
    }
}
