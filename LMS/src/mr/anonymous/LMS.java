package mr.anonymous;

import java.util.Scanner;

public class LMS {
    static Scanner sc = new Scanner(System.in);
    static Admin adminobj = new Admin();
    static User userobj = new User();
    static Books bookObj = new Books();
    public static void main(String[] args) {
        boolean flag = true;
        Flush.flush();
        
        while(flag){
           Flush.flush();
            System.out.println("Welcome to Library Management System");
            System.out.println("1.Admin\n2.User\n3.Exit");
            int option = sc.nextInt();
            switch (option){
                case 1-> {adminobj.admin();}
                case 2 ->{userobj.userwelcome();}
                case 3-> flag = false;
                default -> System.out.println("Enter between 1 to 3");
            }
        }

    }
    
    
}
