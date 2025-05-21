package client.view;

import shared.Login;

import java.io.InputStream;
import java.util.Scanner;

public class Terminal {
    Scanner scanner = new Scanner(System.in);

    public Terminal() {
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
}
