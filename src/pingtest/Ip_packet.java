package pingtest;
class Ip_packet{
    private IPheader iph;
    private String protocol;
    private IcmpPacket icmp;
    public Ip_packet(){}
    public Ip_packet(String p,String sip,String dip, IcmpPacket icmp){
        iph=new IPheader(sip,dip);
        this.icmp=icmp;
        protocol=p;

    }
    public void printIp(){
        System.out.println("Protocol:"+" "+protocol);
        iph.showIpheader();
        icmp.printReq();
    }
    public IcmpPacket getIcmp(){
        return icmp;
    }
    public String getSourceip(){
        return iph.getSip();
    }
    public String getDestip(){
        return iph.getDip();
    }
}