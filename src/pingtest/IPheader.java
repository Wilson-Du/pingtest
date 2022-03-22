package pingtest;

public class IPheader {
    protected String sourceip;
    protected String destip;
    public IPheader(String sip,String dip){
        sourceip=sip;
        destip=dip;
        
    }
    public void showIpheader(){
        System.out.println("Sourceip:"+sourceip+" "+ "Destinationip:"+destip);
    }
    public String getSip(){
        return sourceip;
    }
    public String getDip(){
        return destip;
    }

}