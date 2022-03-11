package views;

import models.User;
import services.UserService;

import java.util.Scanner;

public class Register {
    public static void view(){
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("First Name: ");
        String firstname = sc.nextLine();

        System.out.print("Last Name: ");
        String lastname = sc.nextLine();

        User user = new User(username,password,firstname,lastname);
        userService.createUser(user);

    }
}
