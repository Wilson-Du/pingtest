package pingtest;
class ArpDataFrame {
    private String sourcemac;
    private String destmac;
    private ArpPacket arppacket;
    private String type;

    public ArpDataFrame(String dm,String sm,String type,ArpPacket arppacket){
        destmac=dm;
        sourcemac=sm;
        this.type=type;
        this.arppacket=arppacket;

    }
    public ArpDataFrame(){}
    public String getDestmac(){
        return destmac;
    }
    public String getSourcemac(){
        return sourcemac;
    }
    public ArpPacket getArpPacket(){
        return arppacket;
    }
    public void printArp(){
        System.out.println("ARP Data Frame:");
        System.out.println("Destination Mac:"+destmac+" "+"Source Mac:"+sourcemac);
        arppacket.showArpPacket();      
    }
}