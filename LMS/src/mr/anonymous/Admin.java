package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    String username,password;
    static ArrayList<Admin> adminList = new ArrayList<>();
    public Admin(String username,String password){
        this.username=username;
        this.password=password;
    }

    static Scanner sc = new Scanner(System.in);
    public static void Admin() {
        Admin temp = new Admin("admin","1234");
        adminList.add(temp);
        Flush.flush();
        System.out.println("Welcome to Admin Dashboard");
        boolean flag = true;
        int attempt = 0;
        while(flag){
        System.out.println("Enter Username");
        String username = sc.nextLine();
        System.out.println("Enter Password");
        String password = sc.nextLine();

        if(username.equals("admin") && password.equals("1234")){
            flag = false;
            System.out.println("welcome");
            System.out.println("1.List all books\n2.Search book\n3.sort book\n4.");
            sc.nextInt();
        }
        else{
            attempt++;
            if(attempt>=3){
                System.out.println("Maxinum login attempt exceeded try after some time");
                flag=false;
            }
            else{
            System.out.println("Wrong Username of Password Try again");}
        }

        }

    }
}
