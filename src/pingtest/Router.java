package pingtest;
class Router{
    private String name;
    private String rid;
    private Interface[] s=new Interface[2];
    private RoTableEntry[] rt=new RoTableEntry[5];
    private ArpTableEntry[] arp=new ArpTableEntry[30];
    private LSDBentry[] lsdb=new LSDBentry[2];

    public Router(String name){
        this.name=name;
        for(int i=0;i<s.length;i++){
            s[i]=new Interface();
        }
        for(int j=0;j<rt.length;j++){
            rt[j]=new RoTableEntry();
        }
        for(int k=0;k<arp.length;k++){
            arp[k]=new ArpTableEntry();
        }
        for(int m=0;m<lsdb.length;m++){
            lsdb[m]=new LSDBentry();
        }
    }
    public void setName(String n){
        name=n;
    }
    public void setRid(String rid){
        this.rid=rid;
    }
    public String getName(){
        return name;
    }
    public String getRid(){
        return rid;
    }
    public void setInterfacega00(String ip,String sub,String n,String m){
        s[0].setName("ga0/0");
        s[0].setIp(ip);
        s[0].setMask(sub);
        s[0].setNetwork(n);
        s[0].distributemac(m);       
    }
    public void setInterfaces001(String ip,String sub,String n,String m){
        s[1].setName("S0/0/1");
        s[1].setIp(ip);
        s[1].setMask(sub);
        s[1].setNetwork(n);
        s[1].distributemac(m); 
    }
    public String getMask(String name){
        String mask="";
        for(int i=0;i<s.length;i++){
            if(s[i].getName()==name){
                mask=s[i].getMask();
                break;

            }
        }
        return mask;

    }
    public String getIp(String name){
        String ip="";
        for(int i=0;i<s.length;i++){
            if(s[i].getName()==name){
                ip=s[i].getIp();
                break;

            }
        }
        return ip;

    }
    public String getNetwork(String name){
        String network="";
        for(int i=0;i<s.length;i++){
            if(s[i].getName()==name){
                network=s[i].getNetwork();
                break;

            }
        }
        return network;
    }
    public void setRoTable(String t,String n,String i,String sub,String s,String na){
        int value=0;
        if(rt[0].getIntname()!=""){
            for(int m=0;m<rt.length;m++){
                if(rt[m].getIntname()==""){
                    value=m;
                    break;
                }
            }

        }
        
        rt[value].setType(t);
        rt[value].setNetwork(n);
        rt[value].setIpddress(i);
        rt[value].setSub(sub);
        rt[value].setStatus(s);
        rt[value].setIntname(na);

    }

    public void buildRotable(){
        for(int i=0;i<s.length;i++){
            setRoTable("L",s[i].getNetwork(),s[i].getIp(),s[i].getMask(),"directly connected",s[i].getName());
        }
    }
    public void addRotable(String t,String n,String m,String s,String i){
        setRoTable(t,n,"",m,"via"+" "+s,i);
    }
    public void showRotable(){
        System.out.println(name);
        for(int i=0;i<rt.length;i++){
            rt[i].show();
        }
    }
    public void showArpTable(){
        System.out.println("ARP Table:");
        for(int s=0;s<arp.length;s++){
            
            System.out.println("ip:"+" "+arp[s].getIpadd()+" "+"mac:"+" "+arp[s].getMacadd());
            
        }
    }
    public void setArp(String ipad,String macad){
        int value=0;
        if(arp[0].getIpadd()!=""){
            for(int m=0;m<arp.length;m++){
                if(arp[m].getIpadd()==""){
                    value=m;
                    break;
                }
            }

        }

        arp[value].setIpadd(ipad);
        arp[value].setMacadd(macad);
    }

    public void receiveArp(ArpDataFrame frame){
        ArpPacket packet=new ArpPacket();
        packet=frame.getArpPacket();
        for(int i=0;i<s.length;i++){
            if(s[i].getIp().equals(packet.getDestip())){
                System.out.println("Router receives ARP request  broadcast");
                setArp(packet.getSourceip(), frame.getSourcemac());
                break;
            }
        }
    }
    public ArpDataFrame respondArp(ArpDataFrame frame){
        ArpPacket packet1=new ArpPacket();
        packet1=frame.getArpPacket();
        int value=99;
        for(int i=0;i<s.length;i++){
            if(s[i].getIp().equals(packet1.getDestip())){
                value=i;
                break;
            }
        }

        ArpPacket packet2=new ArpPacket("IPv4",s[value].getMac(),packet1.getDestip(),packet1.getSourcemac(),packet1.getSourceip());
        ArpDataFrame frame1=new ArpDataFrame(packet1.getSourcemac(),s[value].getMac(),"ARP",packet2);
        System.out.println( name+" responds with its MAC address");
        frame1.printArp();
        return frame1;
    }
    public void receiveArp_reply(ArpDataFrame frame){
        ArpPacket packet1=new ArpPacket();
        packet1=frame.getArpPacket();
        setArp(packet1.getSourceip(),frame.getSourcemac());
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
    public IcmpDataFrame receive_transmitIcmp(IcmpDataFrame f,Pc pc){
        Ip_packet ipp1=new Ip_packet();
        ipp1=f.getIpp();
        System.out.println(name+" gets destination ip address"+" "+ipp1.getDestip());
        System.out.println("and checks routing table to find which interface is in the same network as destination ip address");
        int value=99;
        for(int i=0;i<s.length;i++){
            if(Main.andIpMask(ipp1.getDestip(),s[i].getIp(),s[i].getMask())==4){
                value=i;

                break;
            }
        }
        System.out.println(name+" send ICMP request data frame to interface:"+" "+s[value].getName());
            System.out.println("if "+name+"'s ARP table has"+" "+pc.getName()+"'s mac address?");
            if(searchArp(ipp1.getDestip())==""){
                System.out.println("No");
                System.out.println(name+" sends out ARP request broadcast");
                ArpPacket packet=new ArpPacket("IPv4",s[value].getMac(),s[value].getIp(),"ff ff ff ff ff ff",ipp1.getDestip());
                ArpDataFrame arpframe=new ArpDataFrame("ff ff ff ff ff ff",s[value].getMac(),"ARP",packet);
                arpframe.printArp();
                pc.receiveArp_request(arpframe);
                ArpDataFrame frame0=new ArpDataFrame();
                frame0=pc.respondArp(arpframe);
                System.out.println(name+" receives the MAC address and saves it into ARP table ");
                receiveArp_reply(frame0);
            }
        System.out.println(name+"'s interface"+" "+s[value].getName()+" "+ " gets mac address from ARP table and sends out the frame");
        IcmpDataFrame frame5=new IcmpDataFrame(searchArp(ipp1.getDestip()),s[value].getMac(),"IPv4",ipp1);
        frame5.printData1();
        return frame5;
    }
    public IcmpDataFrame receive_transmitIcmp(IcmpDataFrame iframe,Router r){
        Ip_packet ipp1=new Ip_packet();
        ipp1=iframe.getIpp();
        System.out.println(name+"gets destination ip address"+" "+ipp1.getDestip());
        System.out.println("and checks routing table to find which interface is in the same network as destination ip address");
        int value=99;
        for(int i=0;i<s.length;i++){
            if(Main.andIpMask(ipp1.getDestip(),s[i].getIp(),s[i].getMask())==4){
                value=i;

                break;
            }
        }
        //System.out.println(value);
        String via="";
        System.out.println("check routing table");
        for(int i=0;i<rt.length;i++){
            if(Main.andIpMask(ipp1.getDestip(), rt[i].getNetwork(),rt[i].getSub() )==4){
                String[] temp=rt[i].getStatus().split(" ");
                via=temp[1];
                //System.out.println(via);
                for(int j=0;j<s.length;j++){
                    if(s[j].getName()==rt[i].getIntname()){
                        value=j;
                        break;
                    }
                }
                break;
                    
            }
        }
        System.out.println(name+" send ICMP request data frame to interface:"+" "+s[value].getName());
        System.out.println("if "+name+"'s ARP table has"+" "+via+"'s mac address?");
        if(searchArp(via)==""){
            System.out.println("No");
            System.out.println(name+" sends out ARP request broadcast");
            ArpPacket packet=new ArpPacket("IPv4",s[value].getMac(),s[value].getIp(),"ff ff ff ff ff ff",via);
            ArpDataFrame arpframe=new ArpDataFrame("ff ff ff ff ff ff",s[value].getMac(),"ARP",packet);
            arpframe.printArp();
            r.receiveArp(arpframe);
            ArpDataFrame frame0=new ArpDataFrame();
            frame0=r.respondArp(arpframe);
            System.out.println(name+" receives the MAC address and saves it into ARP table ");
            receiveArp_reply(frame0);
        }
        System.out.println(name+"'s interface"+" "+s[value].getName()+" "+ " gets mac address from ARP table and sends out the frame");
        IcmpDataFrame frame5=new IcmpDataFrame(searchArp(via),s[value].getMac(),"IPv4",ipp1);
        frame5.printData1();
        return frame5;
    }
    
    public void setLSDB(Lsaheader lsah){
        int value=0;
        if(lsdb[0].getLsah_linkstateid()!=""){
            for(int m=0;m<lsdb.length;m++){
                if(lsdb[m].getLsah_linkstateid()==""){
                    value=m;
                    break;
                }
            }

        }
        
        lsdb[value].setLsah(lsah);

        

    }
    public void setLSDB(Lsaheader lsah,int num){
        lsdb[0].setLsah(lsah);
        lsdb[0].setNumlsa(num);

        
 
    }
    public void receiveDbd(Dbd dbd){
        Lsaheader lsah2=new Lsaheader();
        lsah2=dbd.getLsah(1);
        setLSDB(lsah2);
        

    }

    public void setLsainLSDB(int i,Lsa lsa){
        lsdb[i-1].setLsa(lsa);
    }
    public void receiveLsu(Lsu lsu1){
        System.out.println(name+" receives the LSU packet");
        Lsa[] lsa=new Lsa[30];
        for(int i=0;i<lsu1.getNumlsa();i++){
             lsa[i]=lsu1.getLsa(i+1);
             
        }
        Lsaheader lsah=new Lsaheader();
        lsah=lsa[0].getLsah();
        //lsah.print();
        for(int k=0;k<lsdb.length;k++){
            if(lsah.getType()==lsdb[k].getLsah_type()&&lsah.getAdv()==lsdb[k].getLsah_adv()&&lsah.getLinkstateid()==lsdb[k].getLsah_linkstateid()){
                lsdb[k].setNumlsa(lsu1.getNumlsa());
                lsdb[k].setLsas(lsu1.getLsa());
                break;
              
            }
            
        }
        


    }
    public void showLSDB(){
        System.out.println("LSDB Table of"+" "+name+":");
        for(int s=0;s<lsdb.length;s++){
            
            lsdb[s].printLsah();
            System.out.println("Numb of Links:"+lsdb[s].getNumlsa());
            lsdb[s].printLsa();
            
        }

    }
    public String searchLSDB_intip(String s){

        String intf="empty";
        for(int i=0;i<lsdb.length;i++){
            if(lsdb[i].getLsah_linkstateid()==s){
                for(int j=0;j<lsdb[i].getNumlsa();j++){
                    if(lsdb[i].getLsalinkid(j)==rid){
                        intf=lsdb[i].getLsalinkdata(j);
                        
                        break;
                    }
                }
                
            }

            
        }
        
        return intf;
        

    }
    public String searchLSDB_network(String s){
        String net="empty";
        for(int i=0;i<lsdb.length;i++){
            if(lsdb[i].getLsah_linkstateid()==s){
                for(int j=0;j<lsdb[i].getNumlsa();j++){
                    if(lsdb[i].getLsalinkid(j)!=rid&&lsdb[i].getLsalinkid(j)!=getNetwork("S0/0/1")){
                        net=lsdb[i].getLsalinkid(j);
                        break;
                    }
                }
            }
        }
        return net;
    }
    public String searchLSDB_mask(String s){
        String mask="empty";
        for(int i=0;i<lsdb.length;i++){
            if(lsdb[i].getLsah_linkstateid()==s){
                for(int j=0;j<lsdb[i].getNumlsa();j++){
                    if(lsdb[i].getLsalinkid(j)!=rid&&lsdb[i].getLsalinkid(j)!=getNetwork("S0/0/1")){
                        mask=lsdb[i].getLsalinkdata(j);
                        break;
                    }
                }
            }
        }
        return mask;
    }
    public String getName_s001(){
        return s[1].getName();
    }
    public String getName_ga00(){
        return s[0].getName();
    }
    public String getRoTable(){
        String ret = "";
		for (int i = 0; i < rt.length; i++) {
            ret=ret+rt[i].getType()+"  "+rt[i].getNetwork()+" "+rt[i].getIpaddress()+" "+rt[i].getSub()+" "+rt[i].getStatus()+" "+rt[i].getIntname()+"\n";
        }
        return ret;
        

 


    }
    public void setIp(int num,String ip){
        s[num].setIp(ip);
    }
    public void setNetwork(int num,String n){
        s[num].setNetwork(n);
    }
    public void setMask(int num, String m){
        s[num].setMask(m);
    }
    public String getMac(String name){
        String mac="";
        for(int i=0;i<s.length;i++){
            if(s[i].getName()==name){
                mac=s[i].getMac();
                break;

            }
        }
        return mac;

    }
    public void buildRotable(String rid){
        String intf=searchLSDB_intip(rid);
        String net=searchLSDB_network(rid);
        addRotable("O", net,getMask("S0/0/1"), intf,"S0/0/1");
    }


}