package mr.anonymous;

public class MerchantList {
    String merchantName,password,verified,merchantId;
    public MerchantList(String merchantName,String merchantId,String password,String verified){
        this.merchantName = merchantName;
        this.password = password;
        this.verified = verified;
        this.merchantId = merchantId;
    }
}
