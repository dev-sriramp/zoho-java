package mr.anonymous;

import java.util.ArrayList;
import java.util.Scanner;

public class Amazon {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<UserList> userlists = new ArrayList<>();
    public static ArrayList<MerchantList> merchantlists = new ArrayList<>();
    public static ArrayList<ProductList> productlists = new ArrayList<>();
    public static ArrayList<OrderList> orderlist = new ArrayList<>();
    static int currentUser = -1;
    static int currentMerchant = -1;
    static String cart = "";
    static String id = "";

    public static void main(String[] args) {
    boolean flag = true;
    while(flag){
        Flush.flush();
        System.out.println("Welcome to Amazon");
        System.out.println("1.Admin\n2.Merchant\n3.User\n4.Exit");
        int during = sc.nextInt();
        sc.nextLine();
        switch (during) {
            case 1 -> Admin();
            case 2 -> Merchant();
            case 3 -> User();
            case 4 -> flag = false;
            default -> System.out.println("Enter between 1 to 4");
        }
    }
    }

    private static void User() {
        Flush.flush();
        System.out.println("Enter an option \n1.Login\n2.Register\n3.Exit");
        int option = sc.nextInt();
        switch (option){
            case 1:
                LoginUser();
                break;
            case 2:
                CreateUser();
                break;
            case 3:
                break;
            default:
                System.out.println("Enter between 1 to 3:");
                break;
        }
    }

    private static void LoginUser() {
        Flush.flush();
        sc.nextLine();
        boolean userattempt = true;
        int time =0;
        while (userattempt){
        System.out.println("Enter the username");
        String username = sc.nextLine();
        if(time>=3){
            userattempt=false;
        }
        for(int i=0;i<userlists.size();i++){
            if(userlists.get(i).username.equals(username)){
                currentUser = i;
                break;
            }}
            if(currentUser>=0){
                String password;
                boolean flag = true;
                int attempt = 0;
                while(flag){
                    System.out.println("Enter Password");
                    password = sc.nextLine();
                    userattempt = false;
                    if(password.equals(userlists.get(currentUser).password)){
                        flag = false;
                        LoggedInUser();
                        currentUser = -1;
                        break;
                    }
                    else{
                        attempt++;
                        System.out.println("Wrong Password try again :(");
                    }
                    if(attempt>3){
                        System.out.println("Better luck next time");
                        flag = false;
                    }
                }
            }else{
                time++;
                System.out.println("User Not Found");
            }
        }}

    private static void LoggedInUser() {
        boolean loop = true;
        while(loop){
            System.out.println("Enter an option");
            System.out.println("1.View Product\n2.Cart\n3.AddMoney\n4.My Orders\n5.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    ViewProduct("all");
                    AddToCart();
                }
                case 2 -> GoToCart();
                case 3 -> AddMoney();
                case 4 -> MyOrder();
                case 5 -> loop = false;
                default -> System.out.println("Enter option between 1 to 3 : ");
            }
        }

    }

    private static void MyOrder() {
        for (OrderList orderList : orderlist) {
            if (orderList.CurrentUser == currentUser) {
                System.out.println("Product Name " + orderList.ProductName);
                System.out.println("Product Id " + orderList.ProductId);
                System.out.println("Product Description " + orderList.ProductDescription);
                System.out.println("Product Category " + orderList.ProductCategory);
                System.out.println("Product Merchant " + orderList.MerchantId);
                System.out.println("Product Price " + orderList.ProductPrice);
                System.out.println("-------------------------------------------------------------");
            }
        }
    }

    private static void GoToCart() {
        sc.nextLine();
        int product = -1;
        System.out.println(cart+" "+id);
        for(int i=0;i<productlists.size();i++){
            if(cart.equals(productlists.get(i).ProductId) && id.equals(productlists.get(i).MerchantId)){
               product = i;
            }
        }
        if(product>=0){
            if(productlists.get(product).ProductCount>0){
            System.out.println("Product Name "+productlists.get(product).ProductName);
                System.out.println("Product Id "+productlists.get(product).ProductId);
                System.out.println("Product Description "+productlists.get(product).ProductDescription);
                System.out.println("Product Category "+productlists.get(product).ProductCategory);
                System.out.println("Product Merchant "+productlists.get(product).MerchantId);
                System.out.println("Product Price "+productlists.get(product).ProductPrice);
                System.out.println("Product Count "+productlists.get(product).ProductCount);
                System.out.println("Product Discount "+productlists.get(product).ProductDiscount+"%");
                System.out.println("-------------------------------------------------------------");
            System.out.println("Proceed to buy [y/n]");
            String buy = sc.nextLine();
            if(buy.equals("y")){
            if(userlists.get(currentUser).money>=productlists.get(product).ProductPrice){
                UserList temp = new UserList(userlists.get(currentUser).username,userlists.get(currentUser).password,userlists.get(currentUser).money-productlists.get(product).ProductPrice);
                userlists.set(currentUser,temp);
                ProductList temp1 = new ProductList(productlists.get(product).ProductId,productlists.get(product).ProductName,productlists.get(product).MerchantId,productlists.get(product).ProductDescription,productlists.get(product).ProductCategory,productlists.get(product).ProductDiscount,productlists.get(product).ProductPrice,productlists.get(product).ProductCount-1);
                productlists.set(product,temp1);
                cart = "";
                id = "";
                OrderList temp2 = new OrderList(currentUser,productlists.get(product).ProductId,productlists.get(product).ProductName,productlists.get(product).MerchantId,productlists.get(product).ProductDescription,productlists.get(product).ProductCategory,productlists.get(product).ProductPrice);
            orderlist.add(temp2);
            }
            else{
                System.out.println("Insufficient fund in your account");
            }}

            }
            else{
                System.out.println("Currently out of stock");
            }
        }
        else{
            System.out.println("Product Not Found");
        }
    }

    private static void AddToCart() {
        System.out.println("Enter to add to cart");
        sc.nextLine();
        System.out.println("Enter the product id");
        cart = sc.nextLine();
        System.out.println("Enter the product Merchant");
        id = sc.nextLine();


    }

    private static void AddMoney() {
        sc.nextLine();
        System.out.println("Enter amount of money to add");
        double Money = sc.nextDouble();
        UserList temp = new UserList(userlists.get(currentUser).username,userlists.get(currentUser).password,userlists.get(currentUser).money+Money);
        userlists.set(currentUser,temp);
        System.out.println("Money available "+userlists.get(currentUser).money);
        sc.nextLine();
    }

    private static void CreateUser() {
        System.out.print("Enter name :");
        sc.nextLine();
        String username = sc.nextLine();
        String password = "";
        boolean passwordcrt = true;
        while(passwordcrt){
        System.out.print("Enter new password");
        password = sc.nextLine();
        System.out.print("Confirm Password");
        String cpassword = sc.nextLine();
        if(password.equals(cpassword)){
            passwordcrt = false;
        }
        else{
            System.out.println("Enter password same:");
        }
        }
        UserList temp = new UserList(username,password,0);
        userlists.add(temp);


    }

    private static void Merchant() {

        boolean flag = true;
        while(flag) {
            Flush.flush();
            System.out.println("Welcome to merchant Dashboard");
            System.out.println("1.New Merchant\n2.Extisting Merchant\n3.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> createMerchant("normal");
                case 2 -> merchantLogin();
                case 3 -> {
                    flag = false;
                    currentMerchant = -1;
                }
                default -> System.out.println("Enter between 1 to 3: ");
            }
        }
    }

    private static void merchantLogin() {
        sc.nextLine();
        boolean flag = true;
        int attempt = 0;
        while (flag) {
            System.out.println("Enter Merchant id");
            String id = sc.nextLine();
            for (int i = 0; i < merchantlists.size(); i++) {
                if (id.equals(merchantlists.get(i).merchantId)) {
                    currentMerchant = i;
                }
            }
            if (currentMerchant >= 0) {
                flag = false;
                boolean attempts = true;
                while (attempts){
                System.out.println("Enter password");
                String password = sc.nextLine();
                if(password.equals(merchantlists.get(currentMerchant).password)){
                    if(merchantlists.get(currentMerchant).verified.equals("yes")){
                        attempts = false;
                        merchantOperation();
                    }else{
                        System.out.println("Merchant not yet verified");
                        attempts = false;
                    }
                }
                else{
                    System.out.println("Password Wrong");
                    attempt++;
                }
                if(attempt>3){
                    attempt = 1;
                    attempts = false;

                }}

            } else {
                System.out.println("Merchant id not found");
            }

        }
    }

    private static void merchantOperation() {
        boolean flag = true;
        while (flag){
            Flush.flush();
            System.out.println("Enter an operation");
            System.out.println("1.Add Product\n2.Update product\n3.View product\n4.Delete product\n5.View My Product\n6.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> AddProduct();
                case 2 -> UpdateProduct();
                case 3 -> ViewProduct("all");
                case 4 -> deleteProduct();
                case 5 -> ViewProduct("my");
                case 6 -> flag = false;
                default -> System.out.println("Enter between 1 to 5");
            }
        }
    }

    private static void deleteProduct() {
        sc.nextLine();
        System.out.println("Enter a product Id to delete");
        String DeleteProduct = sc.nextLine();
        int delete = -1;
        for(int i=0;i<productlists.size();i++){
            if(DeleteProduct.equals(productlists.get(i).ProductId) && productlists.get(i).MerchantId.equals(merchantlists.get(currentMerchant).merchantId)){
                delete = i;
                break;
            }
        }
        if(delete>=0){
            productlists.remove(delete);
        }
        else{
            System.out.println("Product not found");
        }
        sc.nextLine();
    }

    private static void ViewProduct(String product) {
        for (ProductList productlist : productlists) {
            if (product.equals("all")) {
                System.out.println("Product Name " + productlist.ProductName);
                System.out.println("Product Id " + productlist.ProductId);
                System.out.println("Product Description " + productlist.ProductDescription);
                System.out.println("Product Category " + productlist.ProductCategory);
                System.out.println("Product Merchant " + productlist.MerchantId);
                System.out.println("Product Price " + productlist.ProductPrice);
                System.out.println("Product Count " + productlist.ProductCount);
                System.out.println("Product Discount " + productlist.ProductDiscount + "%");
                System.out.println("-------------------------------------------------------------");

            } else {
                if ((productlist.MerchantId).equals(merchantlists.get(currentMerchant).merchantId)) {
                    System.out.println("Product Name " + productlist.ProductName);
                    System.out.println("Product ID " + productlist.ProductId);
                    System.out.println("Product Description " + productlist.ProductDescription);
                    System.out.println("Product Category " + productlist.ProductCategory);
                    System.out.println("Product Merchant " + productlist.MerchantId);
                    System.out.println("Product Price " + productlist.ProductPrice);
                    System.out.println("Product Count " + productlist.ProductCount);
                    System.out.println("Product Discount " + productlist.ProductDiscount + "%");
                    System.out.println("-------------------------------------------------------------");
                }
            }
        }

        sc.nextLine();
    }

    private static void UpdateProduct() {
        sc.nextLine();
        System.out.println("Enter Product name");
        String ProductName = sc.nextLine();
        System.out.println("Enter Product Id");
        String ProductId = sc.nextLine();
        System.out.println("Enter Product Description");
        String ProductDescription = sc.nextLine();
        System.out.println("Enter Product Category");
        String ProductCategory = sc.nextLine();
        System.out.println("Product Price");
        double ProductPrice = sc.nextDouble();
        System.out.println("Enter Product Count");
        int ProductCount = sc.nextInt();
        System.out.println("Enter Product Discount");
        double ProductDiscount = sc.nextDouble();
        int updateList = -1;
        for(int i=0;i<productlists.size();i++){
            if(productlists.get(i).ProductId.equals(ProductId) && productlists.get(i).MerchantId.equals(merchantlists.get(currentMerchant).merchantId)){
                updateList = i;
            }
        }
        if(updateList>=0){
        ProductList temp = new ProductList(ProductId,ProductName, merchantlists.get(currentMerchant).merchantId, ProductDescription, ProductCategory, ProductDiscount, ProductPrice, ProductCount);
        productlists.set(updateList,temp);}
        else{
            System.out.println("Product Id Not found");
        }
        System.out.println("Enter to Continue");

    }

    private static void AddProduct() {
        sc.nextLine();
        System.out.println("Enter Product name");
        String ProductName = sc.nextLine();
        System.out.println("Enter Product Id");
        String ProductId = sc.nextLine();
        System.out.println("Enter Product Description");
        String ProductDescription = sc.nextLine();
        System.out.println("Enter Product Category");
        String ProductCategory = sc.nextLine();
        System.out.println("Product Price");
        double ProductPrice = sc.nextDouble();
        System.out.println("Enter Product Count");
        int ProductCount = sc.nextInt();
        System.out.println("Enter Product Discount");
        double ProductDiscount = sc.nextDouble();
        ProductList temp = new ProductList(ProductId,ProductName, merchantlists.get(currentMerchant).merchantId, ProductDescription, ProductCategory, ProductDiscount, ProductPrice, ProductCount);
        productlists.add(temp);
    }

    private static void createMerchant(String value) {
        sc.nextLine();
        String user;
        if(value.equals("admin")){;
        user = "yes";}
        else{
            user = "not";
        }
        System.out.println("Enter your Business name");
        String username = sc.nextLine();
        System.out.println("Enter Password");
        String password = sc.nextLine();
        MerchantList temp = new MerchantList(username,"M10"+(merchantlists.size()+1),password,user);
        merchantlists.add(temp);
        System.out.println("Merchant added your ID is M10"+merchantlists.size());
        sc.nextLine();
    }

    private static void Admin() {
        System.out.println("Welcome Sudo");
        System.out.println("Enter Username");
        String user = sc.nextLine();
        System.out.println("Enter Passwrd");
        int pass = sc.nextInt();
        if(user.equals("1234") && pass==1234){
            boolean flag = true;
            while(flag){
                System.out.println("Enter Operation");
                System.out.println("1.Add Merchant\n2.Verify Mercahnt\n3.Remove Merchant\n4.View Products\n5.View Merchants\n6.Exit");
                int operation = sc.nextInt();
                switch (operation) {
                    case 1 -> createMerchant("admin");
                    case 2 -> VerifyMerchant();
                    case 3 -> removeMerchant();
                    case 4 -> ViewProduct("all");
                    case 5 -> viewMerchants();
                    case 6 -> flag = false;
                    default -> System.out.println("Enter between 1 to 5");
                }
            }
        }

    }

    private static void viewMerchants() {
        for (MerchantList merchantlist : merchantlists) {
            System.out.println("Merchant Business" + merchantlist.merchantName);
            System.out.println("Merchant Status" + merchantlist.verified);
            System.out.println("Merchant ID" + merchantlist.merchantId);
            System.out.println("---------------------------------------------");
        }
        sc.nextLine();
    }

    private static void removeMerchant() {
        sc.nextLine();
        int removeMerchant = -1;
        System.out.println("Enter Merchant Id to remove");
        String merchantId = sc.nextLine();
        for(int i=0;i< merchantlists.size();i++){
            if(merchantId.equals(merchantlists.get(i).merchantId)){
                removeMerchant = i;
                break;
            }
        }
        if(removeMerchant >=0){
        merchantlists.remove(removeMerchant);}
        else{
            System.out.println("Merchant Id not found");
        }
        if(removeMerchant>=0){
        int size = 0;
        for(int i=0;i< productlists.size();){
            if(merchantId.equals(productlists.get(i).MerchantId)){
                productlists.remove(i);
                size++;
            }
            else{
                i++;
            }
        }}

    }

    private static void VerifyMerchant() {
        sc.nextLine();
        for(int i=0;i<merchantlists.size();i++){
            System.out.println(merchantlists.size());
            if(merchantlists.get(i).verified.equals("not")){
            System.out.println("Merchant Business name "+merchantlists.get(i).merchantName);
            System.out.println("Verify merchant : [y/n/d]");
            String verify = sc.nextLine();
            if(verify.equals("y")){
                MerchantList temp = new MerchantList(merchantlists.get(i).merchantName,merchantlists.get(i).merchantId,merchantlists.get(i).password,"yes");
                merchantlists.set(i,temp);
            }
        }}
    }
}
