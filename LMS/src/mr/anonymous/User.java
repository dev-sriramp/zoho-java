package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    ArrayList<User> userList = new ArrayList<>();
    String username, password, userId;
    int initialAmount;
    int currentUser = -1;

    public User() {
    }

    public User(String username, String password, String userId, int initialAmount) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.initialAmount = initialAmount;
    }

    Scanner sc = new Scanner(System.in);

    public void userwelcome() {
        boolean flag = true;
        while (flag) {
            System.out.println("Welcome User");
            System.out.println("1.New user\n2.Extisting User\n3.Exit");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1 -> {addUser();}
                case 2 -> {userlogin();}
                case 3 -> {flag = false;}
                default -> {
                    System.out.println("Enter between 1 to 3");
                }
            }
        }

    }

    public void addUser() {
        System.out.println("Enter new username");
        String username = sc.nextLine();
        String userId = "U0" + userList.size();
        User temp = new User(username, "", userId, 1500);
        userList.add(temp);
        System.out.println("User added Sucessfully");
        System.out.println("New User can set Password upon their login");
        System.out.println("New User id : " + userId);
        sc.nextLine();

    }

    public void userlogin() {

        boolean flag = true;
        int attempt = 0;
        while (flag) {
            System.out.println("Enter UserID");

            String userId = sc.nextLine();
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).userId.equals(userId)) {
                    currentUser = i;
                    break;
                }
            }
            if (currentUser >= 0) {
                if (userList.get(currentUser).password.equals("")) {
                    int times = 0;
                    for (;;) {

                        System.out.println("Enter new Password");
                        String password = sc.nextLine();
                        System.out.println("Enter confirm Password");
                        String cpassword = sc.nextLine();
                        if (password.equals(cpassword)) {
                            userList.get(currentUser).password = password;
                            System.out.println("Password Updated");
                            sc.nextLine();
                            flag = false;
                            break;
                        } else {
                            System.out.println("Password doesnot Match\nTry again");
                            times++;
                        }
                        if (times >= 3) {
                            flag = false;
                            break;
                        }

                    }
                } else {
                    System.out.println("Enter Password");
                    String password = sc.nextLine();
                    if (password.equals(userList.get(currentUser).password)) {
                        flag = false;
                        userOperation();
                    } else {
                        System.out.println("Wrong Password Try Again");
                        attempt++;
                    }
                }
            } else {
                System.out.println("No UserId found");
                attempt++;
            }
            if (attempt >= 3) {
                System.out.println("Maximum attempt limit exists try after some time :(");
                sc.nextLine();
                flag = false;
            }
        }
    }

    private void userOperation() {
        boolean flag = true;
        while (flag) {
            Flush.flush();
            System.out.println("Welcome " + userList.get(currentUser).username);
            System.out.println("Enter an option");
            System.out.println("1.Borrow Book\n2.Return Book\n3.My History\n4.Exit");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                    flag = false;
                    currentUser = -1;
                }
                default -> {
                    System.out.println("Enter between 1 to 4");
                }
            }
        }

    }

}
