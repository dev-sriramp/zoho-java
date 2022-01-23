package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends LMS{
    int currentUser = -1;
    String username, password, adminId;
    User userobj = new User();
    public Admin() {
    }

    public Admin(String username, String password, String adminId) {
        this.username = username;
        this.password = password;
        this.adminId = adminId;
    }

    ArrayList<Admin> adminList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    

    public void admin(User userobj) {
        if (adminList.size() == 0) {
            Admin temp = new Admin("admin", "1234", "A0" + adminList.size());
            adminList.add(temp);
        }
        Flush.flush();
        System.out.println("Welcome to Admin Dashboard");
        boolean flag = true;
        int attempt = 0;
        while (flag) {
            System.out.println("Enter the Admin Id");
            String adminId = sc.nextLine();

            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).adminId.equals(adminId)) {
                    currentUser = i;
                    break;
                }
            }
            if (currentUser >= 0) {
                if (adminList.get(currentUser).password.equals("")) {
                    System.out.println("Enter new Password ");
                    String password = sc.nextLine();
                    System.out.println("Enter Confirm Password");
                    String cpassword = sc.nextLine();
                    if (password.equals(cpassword)) {
                        adminList.get(currentUser).password = password;
                        System.out.println("Password Updated Sucessfully");
                        flag = false;
                    }
                } else {
                    System.out.println("Enter Password");
                    String password = sc.nextLine();
                    if (password.equals(adminList.get(currentUser).password)) {
                        flag = false;
                        adminOperation(userobj);
                    } else {
                        attempt++;
                        System.out.println("Wrong password Try again");
                    }
                }
            } else {
                if (attempt < 3)
                    System.out.println("No ID Found");
                attempt++;
            }
            if (attempt >= 3) {
                System.out.print("Maximum limit exceeded Try after some time :(");
                sc.nextLine();
                flag = false;
                break;
            }

        }

    }

    private void adminOperation(User userobj) {
        boolean flag = true;
        while (flag) {
            Flush.flush();
            System.out.println("Welcome " + adminList.get(currentUser).username);
            System.out.println("Enter an operation");
            System.out.println("1.Add Admin\n2.Add Borrower\n3.Add Book\n4.Delete Book\n5.Search Book\n6.Sort Book\n7.Exit");
            int operation = sc.nextInt();
            switch (operation) {
                case 1 -> addAdmin();
                case 2 -> {
                    userobj.addUser();
                }
                case 3->{
                    addBook();
                }
                case 4->{deleteBook();}
                case 5->{searchBook();}
                case 6 ->{sortBook();}
                case 7 -> {
                    sc.nextLine();
                    flag = false;
                }
                default -> {
                    System.out.println("Enter between 1 to 3");
                }
            }
        }

    }

    private void sortBook() {
    }

    private void searchBook() {
    }

    private void deleteBook() {
    }

    private void addBook() {
    }

    private void addAdmin() {
        sc.nextLine();
        System.out.println("Enter new Admin username");
        String username = sc.nextLine();
        String adminID = "A0" + adminList.size();
        Admin temp = new Admin(username, "", adminID);
        adminList.add(temp);
        System.out.println("Admin added Sucessfully");
        System.out.println("New admin can set Password upon their login");
        System.out.println("New Admin id : " + adminID);
        sc.nextLine();
    }

}
