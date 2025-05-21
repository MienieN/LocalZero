package client.view;

import shared.Login;
import shared.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
    Scanner scanner = new Scanner(System.in);


    public Terminal() {

    }

    public int printMenu(){
        int choice;
        System.out.println("Press 1 for biking");
        choice = scanner.nextInt();
        return choice;
    }

    public Login loginUser(){
        String username;
        String password;
        System.out.println("Please enter username");
        username = scanner.nextLine();
        System.out.println("Please enter password");
        password = scanner.nextLine();
        return new Login(username, password);
    }

    public int bike() {
        int kilometers;
        System.out.println("Great Job!");
        System.out.println("How far did you bike? (In kilometers)");
        kilometers = scanner.nextInt();
        return kilometers;
    }
}
