package mr.anonymous;

public class OrderList {
    String ProductId,ProductName,MerchantId,ProductDescription,ProductCategory;
    double ProductPrice;
    int CurrentUser;
    public OrderList(int currentUser,String productId, String productName, String merchantId, String productDescription, String productCategory,  double productPrice) {
    this.ProductId=productId;
    this.ProductName=productName;
    this.MerchantId=merchantId;
    this.ProductDescription=productDescription;
    this.ProductCategory=productCategory;
    this.ProductPrice = productPrice;
    this.CurrentUser=currentUser;
    }
}
