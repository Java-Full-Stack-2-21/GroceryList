package views;

import models.GroceryItem;
import models.GroceryList;
import services.GroceryItemService;
import services.GroceryListService;
import util.Print;

import java.util.Scanner;

public class GroceryListView {
    public static void view(Integer id){
        GroceryListService groceryListService = new GroceryListService();
        GroceryItemService groceryItemService = new GroceryItemService();
        GroceryList groceryList = groceryListService.getOne(id);

        Scanner sc = new Scanner(System.in);
        Boolean running = true;

        while (running){
            Print.itempage(groceryList);

            String input = sc.nextLine();

            switch (input){
                case "1":
                    System.out.println(groceryItemService.getAllGivenListId(groceryList.getId()));
                    break;
                case "2":
                    GroceryItem gi = new GroceryItem();
                    System.out.print("Item Name: ");
                    gi.setName(sc.nextLine());

                    System.out.print("Quantity (type \"n\" if you dont want to input a quantity): ");
                    String quantity = sc.nextLine();

                    if(!quantity.equals("n")){
                        gi.setQuantity(Integer.parseInt(quantity));
                    }
                    gi.setListId(groceryList.getId());

                    groceryItemService.createItem(gi);
                    System.out.println("item was added to list " + groceryList.getName());
                    break;
                case "3":
                    System.out.println(groceryItemService.getAllGivenListId(groceryList.getId()));
                    System.out.print("Item Id: ");
                    String itemId = sc.nextLine();

                    groceryItemService.markItemInCart(Integer.parseInt(itemId));
                    System.out.println("Item id " + itemId + " is in cart");
                    break;
                case "4":
                    System.out.println(groceryItemService.getAllGivenListId(groceryList.getId()));
                    System.out.print("Item Id: ");
                    String itId = sc.nextLine();

                    groceryItemService.deleteItemInCart(Integer.parseInt(itId));
                    System.out.println("Item id " + itId + " has been deleted");
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Input");

            }

        }

    }

}
