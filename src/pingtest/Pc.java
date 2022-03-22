package pingtest;
class Pc{
    private String name;
    private String ipaddress;
    private String macaddress;
    private String gateway;
    private String submask;
    private ArpTableEntry[] arp=new ArpTableEntry[4];
    public Pc(String n,String ip,String mac,String gat,String sub){
        for(int i=0;i<arp.length;i++){
            arp[i]=new ArpTableEntry();       
        }
        name=n;
        ipaddress=ip;
        macaddress=mac;
        gateway=gat;
        submask=sub;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setIp(String ip){
        ipaddress=ip;
    }
    public void setGateway(String gat){
        gateway=gat;
    }
    public void setSubmask(String sub){
        submask=sub;
    }
    
    public String getName(){
        return name;
    }
    public String getIp(){
        return ipaddress;
    }
    public String getMac(){
        return macaddress;
    }
    public String getGateway(){
        return gateway;
    }
    public String getSubmask(){
        return submask;
    }
    public void setArp(String ipad,String macad){
        int value=0;
        if(arp[0].getIpadd()!=""){
            for(int m=1;m<arp.length;m++){
                if(arp[m].getIpadd()==""){
                    value=m;
                    break;
                }
            }
            
        }
        arp[value].setIpadd(ipad);
        arp[value].setMacadd(macad);
    }
    public void showArpTable(){
        System.out.println(name+" ARP Table:");
        for(int s=0;s<arp.length;s++){
            
            System.out.println("ip:"+" "+arp[s].getIpadd()+" "+"mac:"+" "+arp[s].getMacadd());
            
        }
    }
    public String searchArp(String ipaddr){
        String mac="";
        for(int i=0;i<arp.length;i++){
            
            if(arp[i].getIpadd()==ipaddr){
                mac=arp[i].getMacadd();
                break;
            }
        }
        return mac;
    }
    public void sendArp(ArpDataFrame frame){
        System.out.println("NIC of"+" "+name+" "+"sends out ARP request  broadcast");
    }
    public void receiveArp_request(ArpDataFrame frame){
        ArpPacket packet=new ArpPacket();
        packet=frame.getArpPacket();
        if(ipaddress==packet.getDestip()){
            System.out.println("NIC of"+" "+name+" "+"receives ARP request  broadcast");
            setArp(packet.getSourceip(), frame.getSourcemac());

        }else{
            System.out.println("discard");
        }
    }
    public ArpDataFrame respondArp(ArpDataFrame frame){
        ArpPacket packet1=new ArpPacket();
        packet1=frame.getArpPacket();
        ArpPacket packet2=new ArpPacket("IPv4",macaddress,packet1.getDestip(),packet1.getSourcemac(),packet1.getSourceip());
        ArpDataFrame frame1=new ArpDataFrame(packet1.getSourcemac(),macaddress,"ARP",packet2);
        System.out.println("NIC of" +" "+name+" "+ "responds with its MAC address");
        frame1.printArp();
        return frame1;
    }
    public void receiveArp_reply(ArpDataFrame frame){
        ArpPacket packet1=new ArpPacket();
        packet1=frame.getArpPacket();
        setArp(packet1.getSourceip(),frame.getSourcemac());
    }
    public IcmpDataFrame receiveIcmpReq(IcmpDataFrame iframe){
        System.out.println("NIC of"+" "+name+" "+ "receives the data frame and sends back an ICMP reply data frame");
        Ip_packet ipp1=new Ip_packet();
        ipp1=iframe.getIpp();
        IcmpPacket icmp1=new IcmpPacket();
        icmp1=ipp1.getIcmp();
        IcmpPacket icmp2=new IcmpPacket(0,0,"0x553a");
        Ip_packet ipp2=new Ip_packet("ICMP",ipp1.getDestip(),ipp1.getSourceip(),icmp2);
        IcmpDataFrame iframe2=new IcmpDataFrame(iframe.getSourcemac(),iframe.getDestmac(),"IPv4",ipp2);
        iframe2.printData2();
        return iframe2;





    }
    public void receiveIcmpRep(IcmpDataFrame frame){
        System.out.println(name+" "+"receives the Icmp reply data frame");
    }
}