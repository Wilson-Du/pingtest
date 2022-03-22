package pingtest;
class IcmpDataFrame {
    private String sourcemac;
    private String destmac;
    private String type;
    private Ip_packet ippacket;
   
    public IcmpDataFrame(){}
    public IcmpDataFrame(String dm,String sm,String type,Ip_packet ipp){

        destmac=dm;
        sourcemac=sm;
        this.type=type;
        ippacket=ipp;
    }
    public void printData1(){
        System.out.println("ICMP request Data Frame");
        System.out.println("Destination Mac:"+destmac+" "+"Source Mac:"+sourcemac+" "+"type:"+type);
        ippacket.printIp();
    }
    public void printData2(){
        System.out.println("ICMP reply Data Frame");
        System.out.println("Destination Mac:"+destmac+" "+"Source Mac:"+sourcemac+" "+"type:"+type);
        ippacket.printIp();
        
    }
    public Ip_packet getIpp(){
        return ippacket;
    }
    public String getDestmac(){
        return destmac;
    }
    public String getSourcemac(){
        return sourcemac;
    }

}