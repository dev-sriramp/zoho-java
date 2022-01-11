package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class RRS {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<UserList> userlists = new ArrayList<>();
    public static ArrayList<TravelHistory> travelhistory = new ArrayList<>();
    public static ArrayList<Booking> booking = new ArrayList<>();
    static int currentUser = -1;
    static int currentStation = 0;
    static int station[][] = new int[5][10];
    static int waiting[][] = new int[5][];
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            Flush.flush();
            System.out.println("Welcome to Railway reservation System");
            System.out.println("Enter an option");
            System.out.println("1.Admin\n2.User\n3.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> Admin();
                case 2 -> User();
                case 3 -> flag = false;
                default -> System.out.println("Enter between 1 to 3");
            }
        }

    }

    private static void User() {
        currentUser = -1;
        Flush.flush();
        System.out.println("Welcome User");
        System.out.println("Enter an operation");
        boolean flag = true;
        while(flag){
            System.out.println("1.New User\n2.Extisting user\n3.Exit");
            int operation = sc.nextInt();
            switch (operation) {
                case 1 -> CreateUser();
                case 2 -> UserLogin();
                case 3 -> flag = false;
                default -> System.out.println("Enter between 1 to 3");
            }
        }
    }

    private static void CreateUser() {
        sc.nextLine();
        System.out.println("Enter username");
        String username = sc.nextLine();
        boolean flag = true;
        int count = 0;
        while(flag){
            System.out.println("Enter Password");
            String password = sc.nextLine();
            System.out.println("Enter confirm password");
            String cpassword = sc.nextLine();
            if(password.equals(cpassword)){
                flag = false;
                UserList temp = new UserList(username,password,"U10"+userlists.size());
                userlists.add(temp);
                System.out.println("User Registered Sucessfully");
                sc.nextLine();
            }
            else{
                System.out.println("Password Mismatch Try again");
                count++;
            }
            if(count>=3){
                System.out.println("Soething went Wromg Try after Some time");
                flag  = false;
            }

        }
    }

    private static void UserLogin() {
        sc.nextLine();
        boolean flag = true;
        int count = 0;
        while (flag){
            System.out.println("Enter username");
            String username = sc.nextLine();
            for(int i=0;i<userlists.size();i++){
                if(username.equals(userlists.get(i).username)){
                    currentUser = i;
                    break;
                }

            }
            if(currentUser>=0){
                System.out.println("Enter password");
                String password = sc.nextLine();
                if(password.equals(userlists.get(currentUser).password)){
                    UserOPerations();
                    flag = false;
                }
                else{
                    System.out.println("Wrong Password try again");
                    sc.nextLine();
                    count++;
                }
            }
            else{
                System.out.println("No user found Try again");
                count++;
            }
            if(count>=3){
                flag = false;
            }
        }
    }

    private static void UserOPerations() {
        sc.nextLine();
        Flush.flush();
        boolean flag = true;
        while (flag){
            System.out.println("Enter an operation");
            System.out.println("1.Book Ticket\n2.Cancel Ticket\n3.View My Travel\n4.Exit");
            int operation = sc.nextInt();
            switch (operation){
                case 1-> BookTicket();
                case 2-> CancelTicket();
                case 3->TravelHistory();
                case 4->flag = false;
                default -> System.out.println("Enter between 1 10 4");
            }
        }

    }

    private static void BookTicket() {
        sc.nextLine();
       System.out.println("Enter number of passengers");
       int passengers = sc.nextInt();

       for(int i=0;i<passengers;i++){
           System.out.println("1.station 1\t2.station 2\t3.station 3\t4.station 4\t5.station 5");
           System.out.println("Enter starting station ");
           int startingStation = sc.nextInt();
           System.out.println("Enter ending statoin");
           int endingStation = sc.nextInt();
           int seat  = 0;
           for(int j=0;j<station.length;j++){
               int sum =0;
               for(int k=startingStation-1;k<endingStation-1;k++){
                   sum+=station[j][k];
               }
               if(sum==0){
                   System.out.println("seat allocated "+j+1);
                   String pnrid = "#PNR"+userlists.get(currentUser).userId+booking.size();
                   System.out.println("Your pnr id is "+pnrid);
                   Booking temp = new Booking(userlists.get(currentUser).userId,startingStation,endingStation,pnrid);
                   booking.add(temp);
                   for(int m= startingStation-1;m<endingStation;m++){
                       station[j][m]=i+1;
                   }
                   seat=1;
                   break;

               }

           }
           if(seat == 0){
               System.out.println("Seat not available");
           }
           else{
               seat =0;
           }
       }
       for(int q=0;q<station.length;q++){
           for(int w=0;w<station[q].length;w++){
               System.out.print(station[q][w]+" ");
           }
           System.out.println();
       }
    }

    private static void CancelTicket() {
    }

    private static void TravelHistory() {
        for (TravelHistory travelHistory : travelhistory) {
            if (travelHistory.userId.equals(userlists.get(currentUser).userId)) {
                System.out.println("Travel history Appears here");
                sc.nextLine();

            }
        }
    }

    private static void Admin() {
        Flush.flush();
        sc.nextLine();
        System.out.println("Welcome Admin");
        boolean flag = true;
        int count = 0;
        while(flag){
            System.out.println("Enter Username");
            String username = sc.nextLine();
            if(username.equals("admin")){
                System.out.println("Enter password");
                String password = sc.nextLine();
                if(password.equals("1234")){
                    System.out.println("HEllo");
                    sc.nextLine();
                    flag = false;
                }
                else {
                    count++;
                }
            }
            else{
                count++;
            }
            if(count>=3){
                System.out.println("Try after some time");
                flag = false;
            }
        }

    }
}
