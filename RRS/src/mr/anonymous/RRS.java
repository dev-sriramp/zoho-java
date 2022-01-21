package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class RRS {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<UserList> userlists = new ArrayList<>();
//    public static ArrayList<TravelHistory> travelhistory = new ArrayList<>();
    public static ArrayList<Booking> booking = new ArrayList<>();
    public static ArrayList<WaitingList> wating = new ArrayList<>();
    static int currentUser = -1;
//    static int currentStation = 0;
    public static int[][] station = new int[5][10];
    //static String waiting[][] = new String[5][];
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            Flush.flush();
            System.out.println("Welcome to Railway reservation System");
            System.out.println("Enter an option");
            System.out.println("1.Admin\n2.User\n3.Exit");
            int option = sc.nextInt();
            switch (option) {
                //case 1:
                //   ---somethig
                //    break;
                //default:
                //   --somrtging
                //break;
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
                System.out.println("Something went Wrong Try after Some time");
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
//           System.out.println("1.station 1\t2.station 2\t3.station 3\t4.station 4\t5.station 5");
           System.out.println("Enter starting station ");
           int startingStation = sc.nextInt();
           System.out.println("Enter ending statoin");
           int endingStation = sc.nextInt();
           int seat  = 0;
           for(int j=0; j<station.length ; j++){
               int sum =0;
               //            1-1->0                  2-1->1
               for(int k=startingStation-1;k<endingStation-1;k++){
                   sum+=station[j][k];
               }
               // 0 0 1 1 0
               // 0 0 1 1 0
               // 0 0 1 1 0
               // 0 0 0 0 0   -> 3
               // 0 0 1 1 0
               if(sum==0){
                   System.out.println("seat allocated "+(j+1));
                   String pnrid = "#PNR"+userlists.get(currentUser).userId+booking.size();
                   System.out.println("Your pnr id is "+pnrid);
                   Booking temp = new Booking(userlists.get(currentUser).userId,startingStation,endingStation,pnrid,"no",j+1);
                   booking.add(temp);
                   for(int m= startingStation-1;m<endingStation-1;m++){
                       station[j][m]=i+1;
                   }
                   seat=1;
                   break;
               }
           }
           if(seat == 0 && wating.size()<5){
               System.out.println("Seat not available");
                   System.out.println("putting you in waiting list");
                   WaitingList temp = new WaitingList(userlists.get(currentUser).userId,startingStation,endingStation);
                   wating.add(temp);
           }else if(wating.size()>=5){
               System.out.println("Seat not available");
           }
           else{
               seat =0;
           }
       }
        for (WaitingList i : wating) {
                System.out.println(i.userid + " " + i.startingStation + " " + i.endingStation + " ");
            System.out.println();
        }
    }

    private static void CancelTicket() {
        sc.nextLine();
        System.out.println("Enter the pnr id to cancel the ticket");
        String CancelTickets = sc.nextLine();
        for(int i=0;i<booking.size();i++){
            if(CancelTickets.equals(booking.get(i).pnrid) && userlists.get(currentUser).userId.equals(booking.get(i).userid)){
                if(booking.get(currentUser).travelled.equals("no")){
                    System.out.println("Enter yes to confirm");
                    String confirm = sc.nextLine();
                    if(confirm.equals("yes") || confirm.equals("Yes") || confirm.equals("Y")||confirm.equals("y")){
                        booking.get(i).travelled="cancelled";
                        System.out.println("Cancelled");
                        for(int m= booking.get(i).from-1;m<booking.get(i).to;m++){
                            station[booking.get(i).seat-1][m]=0;
                        }

                        for(int j=0;j<wating.size();j++){
                            for(int k=0;k<station.length;k++){
                                int sum =0;
                                for(int s=booking.get(i).from-1;s<booking.get(i).to-1;s++){
                                    sum+=station[k][s];
                                }
                                if(sum==0){
                                    System.out.println("seat allocated "+(k+1));
                                    String pnrid = "#PNR"+wating.get(j).userid+booking.size();
                                    System.out.println("Your pnr id is "+pnrid);
                                    Booking temp = new Booking(wating.get(j).userid,wating.get(j).startingStation,wating.get(j).endingStation,pnrid,"no",k+1);
                                    booking.add(temp);
                                    for(int m= wating.get(j).startingStation-1;m<wating.get(j).endingStation;m++){
                                        station[k][m]=i+1;
                                    }
                                    wating.remove(j);
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("User already Travelled");
                }
            }
        }

    }

    private static void TravelHistory() {
        System.out.println("Travel history Appears here");
        for (Booking value : booking) {
            if (value.userid.equals(userlists.get(currentUser).userId)) {
                System.out.println("From->" + value.from);
                System.out.println("To->" + value.to);
                System.out.println("Seat No ->" + value.seat);
                System.out.println("PNRID ->" + value.pnrid);
                System.out.println("Travelled->" + value.travelled);
                System.out.println("---------------------------------------");
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
