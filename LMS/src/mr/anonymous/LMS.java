package mr.anonymous;

import java.util.Scanner;

public class LMS {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            Flush.flush();
            System.out.println("Welcome to Library Management System");
            System.out.println("1.Admin\n2.User\n3.Exit");
            int option = sc.nextInt();
            switch (option){
                case 1-> Admin.Admin();
                case 2 -> User();
                case 3-> flag = false;
                default -> System.out.println("Enter between 1 to 3");
            }
        }

    }
    
    private static void User() {
    }
}