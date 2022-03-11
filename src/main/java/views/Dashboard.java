package views;

import models.GroceryList;
import models.User;
import services.GroceryListService;
import util.Print;

import java.util.Scanner;

public class Dashboard {
    public static void view(User user){
        GroceryListService groceryListService = new GroceryListService();
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;

        while(running){
            Print.dashboard(user);
            String input = scanner.nextLine();

            switch (input){
                case "1":
                    System.out.println(groceryListService.getAllGivenUserId(user.getId()));
                    break;
                case "2":
                    System.out.print("List id: ");
                    String listId = scanner.nextLine();

                    if(groceryListService.isListOurs(user, Integer.parseInt(listId)))
                        GroceryListView.view(Integer.parseInt(listId));
                    else
                        System.out.println("Invalid Input");
                    break;
                case "3":
                    System.out.print("List Name: ");
                    String name = scanner.nextLine();
                    groceryListService.createList(new GroceryList(name, user.getId()));
                    System.out.println("List " + name + " was created");
                    break;
                case "4":
                    System.out.println(groceryListService.getAllGivenUserId(user.getId()));
                    System.out.print("List Id: ");
                    String liId = scanner.nextLine();
                    groceryListService.deleteList(Integer.parseInt(liId));
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid");
            }

        }

    }
}
