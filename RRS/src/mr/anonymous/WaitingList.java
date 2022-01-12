package mr.anonymous;

public class WaitingList {
    String userid;
    int startingStation,endingStation;
    public WaitingList(String userid,int startingStation,int endingStation){
        this.userid=userid;
        this.startingStation=startingStation;
        this.endingStation=endingStation;
    }
}
