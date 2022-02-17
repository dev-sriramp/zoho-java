package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class Books {

    ArrayList<Books> booksList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String bookName,ISBNnumber,frequencyRate;
    int presentCount,totalCount,borrowed;
    float bookPrice;
    public Books(){}
    public Books(String bookName,String ISBNnumber,String frequencyRate,int presentCount,int totalCount,int borrowed,float bookPrice){
        this.bookName=bookName;
        this.ISBNnumber=ISBNnumber;
        this.frequencyRate=frequencyRate;
        this.presentCount=presentCount;
        this.borrowed=borrowed;
        this.totalCount=totalCount;
        this.bookPrice=bookPrice;

    }
    public void addbook(){
        System.out.print("Enter Book Name  ");
        String bookName = sc.nextLine();
        String ISBNnumber ="";
        boolean flag = true;
        while(flag){
            System.out.print("Enter ISBN Number ");
            ISBNnumber = sc.nextLine();
            boolean unique = true;
            for(int i=0;i<booksList.size();i++){
                if(ISBNnumber.equals(booksList.get(i).ISBNnumber)){
                    System.out.println("Book ISBN Number shouold be unique");
                    unique = false;
                    break;
                }
            }
            if(unique){
                flag = false;
            }
        }
        
        
        System.out.print("Enter number of books ");
        int totalCount = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Price");
        float bookPrice = sc.nextFloat();
        sc.nextLine();
        System.out.print("Type y to add Book  ");
        String confirm = sc.nextLine();
        if(confirm.equals("y")){
            Books temp = new Books(bookName, ISBNnumber, "", totalCount, totalCount, borrowed, bookPrice);
            booksList.add(temp);
            System.out.println("Book Added Sucessfully");
        }
        Flush.flush();
       
        sc.nextLine();
    }
    public void listBook(){
        int sno=0;
        
        System.out.println("S.no\tBook Name\tISBN Number\tTotal Count\tBorrowed\tPresent Count\tBook Price");
        for(int i=0;i<booksList.size();i++){
            System.out.println(sno+++"\t"+booksList.get(i).bookName+"\t"+booksList.get(i).ISBNnumber+"\t"+booksList.get(i).totalCount+"\t"+booksList.get(i).borrowed+"\t"+booksList.get(i).presentCount+"\t"+booksList.get(i).bookPrice);
        }
        sc.nextLine();
    }
    
}
