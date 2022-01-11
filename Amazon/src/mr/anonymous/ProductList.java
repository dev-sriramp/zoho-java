package mr.anonymous;
public class ProductList {
    String ProductId,ProductName,MerchantId,ProductDescription,ProductCategory;
    double ProductPrice,ProductDiscount;
    int ProductCount;
    public ProductList(String ProductId ,String ProductName,String MerchantId,String ProductDescription,String ProductCategory,double ProductDiscount,double ProductPrice,int ProductCount ){
        this.MerchantId=MerchantId;
        this.ProductId = ProductId;
        this.ProductName=ProductName;
        this.ProductDescription=ProductDescription;
        this.ProductCategory=ProductCategory;
        this.ProductPrice=ProductPrice;
        this.ProductCount=ProductCount;
        this.ProductDiscount=ProductDiscount;

    }
}
