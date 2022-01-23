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

    public void user() {

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

    }

}
