package views;

import util.Print;

import java.util.Scanner;

public class Landing {

    public static void view(){
        Scanner sc = new Scanner(System.in);
        Boolean running = true;

        while(running){
            Print.landing();
            String input = sc.nextLine();

            switch (input){
                case "1":
                    Login.view();
                    break;
                case "2":
                    Register.view();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("INVALID INPUT WOMP WOMP");

            }

        }
    }
}
