package views;

import models.User;
import services.UserService;

import java.util.Scanner;
import java.util.logging.SocketHandler;

public class Login {
    public static void view(){
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.validateCredentials(username, password);

        if(user == null){
            System.out.println("Username or password incorrect");
        }else{
            Dashboard.view(user);
        }


    }
}
