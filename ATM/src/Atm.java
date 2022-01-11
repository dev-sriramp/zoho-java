import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Atm {
    static Scanner sc = new Scanner(System.in);
    static int amount[] = { 100, 200, 500, 2000 };
    static int count[] = { 0, 0, 0, 0 };
    static int max[] = { 250, 250, 200, 300 };
    static long totalAmount = 0;
    static User user1[] = new User[3];

    public static ArrayList<Ministatement> ministatement = new ArrayList<>();

    static int currentUser = -1;

    public static void main(String[] args) {
        user1[0] = new User("sriram", 1234, 100000);
        user1[1] = new User("anonymous", 9585, 1000);
        user1[2] = new User("mranonymous", 1723, 19865);

        Flush.flush();

        boolean flag = true;

        while (flag) {
            System.out.println("\nATM MACHINE\n1.Admin\n2.User\n3.exit");
            int user = sc.nextInt();
            sc.nextLine();
            switch (user) {
                case 1:
                    Admin();
                    break;
                case 2:
                    Users();

                    break;
                case 3:
                    Flush.flush();
                    System.out.println("Bye bye");
                    flag = false;
                    break;
                default:
                    Flush.flush();
                    System.out.print("Invalid input");
                    break;
            }
        }
        sc.close();

    }

    public static void Admin() {
        Flush.flush();
        System.out.println("Enter username");
        String username = sc.nextLine();
        System.out.println("Enter password");
        int pass = sc.nextInt();
        if (username.equals("admin") && pass == 1234) {
            System.out.println("welcome admin");
            boolean leave = true;
            while (leave) {
                System.out.print("1.add money\n2.check money\n3.exit\n");
                int ope = sc.nextInt();
                switch (ope) {
                    case 1:
                        for (int i = 0; i < count.length;) {
                            System.out.print("enter no of " + amount[i] + " ");
                            int money = sc.nextInt();
                            if (money + count[i] <= max[i]) {
                                count[i] += money;
                                totalAmount = totalAmount + amount[i] * money;
                                i++;
                            } else {
                                System.out.println("Already presented " + count[i]);

                                System.out.println("maximum limit for " + amount[i] + " is " + max[i]);
                                System.out.println("Enter between 0 to " + (max[i] - count[i]));
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < count.length; i++) {
                            System.out.println(amount[i] + "->" + count[i]);
                        }
                        System.out.println("Total amount in the Atm is " + totalAmount);
                        break;
                    case 3:
                        leave = false;
                        Flush.flush();
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            }
        }
    }

    public static void Deposit() {

        long totalDeposit = 0;
        int notes[] = { 0, 0, 0, 0 };
        for (int i = 0; i < 4;) {
            System.out.println("No of " + amount[i]);
            int m = sc.nextInt();
            if (m + count[i] <= max[i]) {
                notes[i] = count[i] + m;
                totalDeposit = totalDeposit + (amount[i] * m);
                i++;
            } else {
                System.out.println("Maximum notes of " + amount[i] + " exceeded");
            }
        }
        if (totalDeposit > 99) {
            user1[currentUser].amount += totalDeposit;
            count = notes;
            totalAmount += totalDeposit;
            LocalDateTime time = LocalDateTime.now();
            Ministatement temp1 = new Ministatement("withdraw", currentUser, time, totalDeposit,
                    user1[currentUser].amount);
            ministatement.add(temp1);

        }
    }

    public static void withDraw() {
        System.out.println("Enter amount to withdraw");
        // System.out.println("Yor balance is "+user1[currentUser].amount);

        int nowwith = sc.nextInt();
        int temp = nowwith;
        if (nowwith >= totalAmount && user1[currentUser].amount >= nowwith) {
            System.out.println("Enter a lower amount to withdraw");

        } else {
            int possibility[] = { 0, 0, 0, 0 };
            int presentCount[] = new int[4];
            for(int i=0;i<=3;i++){
                presentCount[i] = count[i];
            }

            if (nowwith % 10 == 0 && nowwith % 100 == 0) {
                while (nowwith >= 2000 && presentCount[3] > 0) {
                    nowwith -= 2000;
                    presentCount[3]--;
                    possibility[3]++;
                }
                while (nowwith >= 500 && presentCount[2] > 0) {
                    nowwith -= 500;
                    presentCount[2]--;
                    possibility[2]++;
                }
                while (nowwith >= 200 && presentCount[1] > 0) {
                    nowwith -= 200;
                    presentCount[1]--;
                    possibility[1]++;
                }
                while (nowwith >= 100 && presentCount[0] > 0) {
                    nowwith -= 100;
                    presentCount[0]--;
                    possibility[0]++;
                }
                if (nowwith == 0) {
                    count = presentCount;
                    totalAmount = totalAmount - temp;
                    user1[currentUser].amount = user1[currentUser].amount - temp;
                    LocalDateTime time = LocalDateTime.now();
                    Ministatement temp1 = new Ministatement("withdraw", currentUser, time, temp,
                            user1[currentUser].amount);
                    ministatement.add(temp1);
                } else {
                    System.out.println("Enter a valid combination");
                }
            } else {
                System.out.println("Enter amount in 100's only");
            }
        }
    }
    public static void Users() {
        Flush.flush();
        System.out.println("Enter username");
        String username = sc.nextLine();

        try {
            for (int i = 0; i < 3; i++) {
                if (user1[i].name.equals(username)) {
                    currentUser = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("User not found\n");
        }
        if (currentUser < 0) {
            System.out.println("User Not found");
        } else {
            boolean attemped = true;
            int attempt = 0;
            while (attemped) {
                if (attempt > 0) {
                    System.out.println("Wrong pin entered try again");
                    System.out.println("Attempt left " + (3 - attempt));
                }
                System.out.println("enter pin");
                int pass = sc.nextInt();
                if (pass == user1[currentUser].pin) {
                    attemped = false;
                    Flush.flush();

                    boolean userTransaction = true;
                    while (userTransaction) {
                        System.out.println("Welcome " + user1[currentUser].name);
                        System.out.println("1.withdraw\n2.Deposit\n3.mini statement\n4.balance\n5.exit");
                        int operation = sc.nextInt();
                        Flush.flush();
                        switch (operation) {
                            case 1:
                                withDraw();
                                break;
                            case 2:
                                Deposit();
                                break;
                            case 3:
                                int n = ministatement.size() - 1;
                                for (int i = n; i >= 0; i--) {
                                    if (ministatement.get(i).id == currentUser) {
                                        System.out.println(ministatement.get(i).type + " " + ministatement.get(i).time
                                                + " " + ministatement.get(i).taken + " " + ministatement.get(i).Money);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Your balanve is " + user1[currentUser].amount);
                                break;
                            case 5:
                                userTransaction = false;
                                break;
                            default:
                                System.out.println("Wrong Input try again");
                        }
                    }
                } else {
                    attempt++;
                }
                if (attempt == 3) {
                    System.out.println(user1[currentUser].name + " is blocked temporiarily");
                    attemped = false;
                }
            }
        }
    }

}

class User {
    String name;
    int pin;
    long amount;

    public User(String name, int pin, long amount) {
        this.name = name;
        this.pin = pin;
        this.amount = amount;

    }

}

class Flush {
    static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class Ministatement {
    int id;
    long Money, taken;
    String type;
    LocalDateTime time;

    public Ministatement(String type, int currentUser, LocalDateTime time, long taken, long balance) {
        this.type = type;
        this.id = currentUser;
        this.time = time;
        this.taken = taken;
        this.Money = balance;
    }
}