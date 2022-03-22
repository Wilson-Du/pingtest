package pingtest;
class ArpPacket{
    private IPheader iph;
    private String sourcemac;
    private String destmac;
    private String protocol;
    public ArpPacket(final String p, final String smac, final String sip, final String dmac, final String dip) {
        iph=new IPheader(sip,dip);
        sourcemac=smac;
        destmac=dmac;
        protocol=p;

    }
    public ArpPacket(){}
    public String getSourceip(){
        return iph.getSip();
    }
    public String getDestip(){
        return iph.getDip();
    }
    public String getSourcemac(){
        return sourcemac;
    }
    public String getDestmac(){
        return destmac;
    }
    public void showArpPacket(){
        System.out.println("protocol:"+" "+protocol+" "+"source mac address:"+" "+sourcemac
        +" "+"destination mac address:"+" "+destmac);
        iph.showIpheader();
    }
    
}
