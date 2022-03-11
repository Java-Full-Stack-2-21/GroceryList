package util;

import models.GroceryItem;
import models.GroceryList;
import models.User;

public class Print {

    public static void landing(){
        System.out.println("******Grocery App******");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("0) EXIT");
    }

    public static void dashboard(User user){
        System.out.println("Welcome, " + user.getFirstname() + " " + user.getLastname());
        System.out.println("1) Display All Lists for " + user.getFirstname());
        System.out.println("2) Go to a specific list");
        System.out.println("3) Create a new list");
        System.out.println("4) Delete a list");
        System.out.println("0) LOGOUT");
    }

    public static void itempage(GroceryList groceryList){
        System.out.println("Current List: " + groceryList.getName());
        System.out.println("1) Display All Items");
        System.out.println("2) Create Item");
        System.out.println("3) Mark Item In Cart");
        System.out.println("4) Delete Item");
        System.out.println("0) BACK TO DASHBOARD");
    }

}
