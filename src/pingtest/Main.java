package pingtest;
public class Main{
    public static int andIpMask(String ip1S,String ip2S,String submaskS){
        int t=0;
        int f=0;
        String[] submask1= submaskS.split("\\.");     
        String[] ip1= ip1S.split("\\.");
        String[] ip2= ip2S.split("\\.");
        for(int k=0;k<submask1.length; k++){
            if((Integer.valueOf(ip1[k]) & Integer.valueOf(submask1[k]))==
            (Integer.valueOf(ip2[k]) & Integer.valueOf(submask1[k]))){
                //System.out.println("true");
                t=t+1;
            }else{
                //System.out.println("false");
                f=f+1;
                }
            }
        return t;
    }
    public static void pingtest(Pc pc1,Pc pc2,Router r1,Router r2){
        System.out.println(pc1.getName()+" "+"ping"+" "+pc2.getName());
        System.out.println(pc1.getName()+" "+"creates an icmp echo-request packet:");
        //icmp echo-request packet
        IcmpPacket icmp1=new IcmpPacket(8,0,"0x4d3a");
        icmp1.printReq();
        System.out.println("ICMP packet is encapsulated into IP packet:");
        //ip packet
        Ip_packet ipp=new Ip_packet("ICMP",pc1.getIp(),pc2.getIp(),icmp1);
        ipp.printIp();
        System.out.println("if PC"+" "+pc1.getName()+" "+ "and PC"+" "+pc2.getName()+" "+ "are in the same network?");
        //if they are in the same network
        if(Main.andIpMask(pc1.getIp(),pc2.getIp(),pc1.getSubmask())==4){
            System.out.println("Yes");//in the same network
            System.out.println("if"+" "+pc1.getName()+"'s ARP table has"+" "+pc2.getName()+"'s mac address?");
            //check Arp Table
            if(pc1.searchArp("192.168.1.3")!=""){
                System.out.println("Yes"); 
            }else{
                System.out.println("No");
                ArpPacket packet=new ArpPacket("IPv4",pc1.getMac(),pc1.getIp(),"ff ff ff ff ff ff",pc2.getIp());
                ArpDataFrame frame1=new ArpDataFrame("ff ff ff ff ff ff",pc1.getMac(),"ARP",packet);
                pc1.sendArp(frame1);
                frame1.printArp();
                pc2.receiveArp_request(frame1);
                pc2.showArpTable();
                ArpDataFrame frame0=new ArpDataFrame();
                frame0=pc2.respondArp(frame1);
                //frame0.printArp();
                //pc2.setArp("1111111", "123424343242");   test
                System.out.println("NIC of"+" "+pc1.getName()+" "+ "receives the MAC address and saves it into ARP table ");
                pc1.receiveArp_reply(frame0);
                pc1.showArpTable();
            }
            System.out.println(pc1.getName()+" "+"gets"+pc2.getName()+"'s mac address from ARP table");;
            System.out.println(pc1.getName()+" "+"sends ICMP request data frame to"+" "+pc2.getName());
            //icmp request data frame
            IcmpDataFrame df=new IcmpDataFrame(pc2.getMac(), pc1.getMac(),"IPv4",ipp);
            df.printData1();
            IcmpDataFrame df1=new IcmpDataFrame();
            df1=pc2.receiveIcmpReq(df);
            pc1.receiveIcmpRep(df1);
        }else{
            System.out.println("No");//in different network
            System.out.println("if"+" "+pc1.getName()+"'s ARP table has its gateway's mac address?");
            if(pc1.searchArp("192.168.1.1")!=""){
                System.out.println("Yes");
            }else{
                System.out.println("No");
                ArpPacket packet=new ArpPacket("IPv4",pc1.getMac(),pc1.getIp(),"ff ff ff ff ff ff",pc1.getGateway());
                ArpDataFrame frame1=new ArpDataFrame("ff ff ff ff ff ff",pc1.getMac(),"ARP",packet);
                pc1.sendArp(frame1);
                frame1.printArp();
                r1.receiveArp(frame1);
                ArpDataFrame frame0=new ArpDataFrame();
                frame0=r1.respondArp(frame1);
                //frame0.printArp();
                System.out.println("NIC of"+" "+pc1.getName()+" "+ "receives the MAC address and saves it into ARP table ");
                pc1.receiveArp_reply(frame0);
            }
            System.out.println(pc1.getName()+" "+"gets its gateway's mac address from ARP table");
            System.out.println(pc1.getName()+" "+"sends ICMP request data frame to its gateway");
            //icmp request data frame
            IcmpDataFrame df=new IcmpDataFrame(r1.getMac("ga0/0"), pc1.getMac(),"IPv4",ipp);
            df.printData1();
            IcmpDataFrame df1=new IcmpDataFrame();
            df1=r1.receive_transmitIcmp(df,r2);
            IcmpDataFrame df2=new IcmpDataFrame();
            df2=r2.receive_transmitIcmp(df1,pc2);
            IcmpDataFrame df3=new IcmpDataFrame();
            df3=pc2.receiveIcmpReq(df2);
            IcmpDataFrame df4=new IcmpDataFrame();
            df4=r2.receive_transmitIcmp(df3,r1);
            IcmpDataFrame df5=new IcmpDataFrame();
            df5=r1.receive_transmitIcmp(df4,pc1);
            pc1.receiveIcmpRep(df5);
            
        }
    }
    public static void setOspf(Router r1,Router r2){
        HelloPacket h1=new HelloPacket(r1.getIp("S0/0/1"), "224.0.0.5",r1.getRid(), "0.0.0.0", r1.getMask("S0/0/1"),r2.getRid(),10, 40);
        System.out.println("R1 sends hello packet to R2");
        h1.printHello();
        HelloPacket h2=new HelloPacket(r2.getIp("S0/0/1"), "224.0.0.5",r2.getRid(), "0.0.0.0", r2.getMask("S0/0/1"),r1.getRid(),10, 40);
        System.out.println("R2 sends hello packet to R1");
        h2.printHello();
        if((h1.getAreaid()==h2.getAreaid()) && (h1.getHelloint()==h2.getHelloint()) && (h1.getDeadint()==h2.getDeadint()) && (h1.getMask()==h2.getMask())){
            System.out.println("Establish neighbor relationship");
            Lsaheader lsah1=new Lsaheader(1642, 1, r1.getRid(), r1.getRid());
            Lsa lsa11=new Lsa(r1.getNetwork("ga0/0"), r1.getMask("ga0/0"), lsah1);
            Lsa lsa12=new Lsa(r2.getRid(), r1.getIp("S0/0/1"), lsah1);
            Lsa lsa13=new Lsa(r1.getNetwork("S0/0/1"), r1.getMask("S0/0/1"), lsah1);
            Lsaheader lsah2=new Lsaheader(1704, 1, r2.getRid(), r2.getRid());
            Lsa lsa21=new Lsa(r2.getNetwork("ga0/0"),r2.getMask("ga0/0"),lsah2);
            Lsa lsa22=new Lsa(r1.getRid(),r2.getIp("S0/0/1"),lsah2);
            Lsa lsa23=new Lsa(r2.getNetwork("S0/0/1"), r2.getMask("S0/0/1"), lsah2);
            Dbd dbd1=new Dbd(r1.getIp("S0/0/1"),r2.getIp("S0/0/1"));
            dbd1.addLsah(lsah1,1);
            System.out.println("R1 sends dbd packet to R2");
            r2.setLSDB(lsah2, 3);
            r2.setLsainLSDB(1, lsa21); r2.setLsainLSDB(1, lsa22); r2.setLsainLSDB(1, lsa23);
            r2.receiveDbd(dbd1);
            System.out.println("R2 sends LSR to R1");
            Lsr lsr2=new Lsr(r2.getIp("S0/0/1"),r1.getIp("S0/0/1"),1,r1.getRid(),r1.getRid());
            System.out.println("R1 receives LSR and respond with LSU containing its LSAs");
            Lsu lsu1=new Lsu(r1.getIp("S0/0/1"),r2.getIp("S0/0/1"));
            lsu1.setNumlsa(3);
            lsu1.setLsa(lsa11,1);
            lsu1.setLsa(lsa12,2);
            lsu1.setLsa(lsa13,3);
            r2.receiveLsu(lsu1);
            r2.showLSDB();
            Dbd dbd2=new Dbd(r2.getIp("S0/0/1"),r1.getIp("S0/0/1"));
            dbd2.addLsah(lsah2,1);
            System.out.println("R2 sends dbd packet to R1");
            r1.setLSDB(lsah1,3);
            r1.setLsainLSDB(1, lsa11); r1.setLsainLSDB(1, lsa12); r1.setLsainLSDB(1, lsa13);
            //r1.showLSDB();
            r1.receiveDbd(dbd2);
            System.out.println("R1 sends LSR to R2");
            Lsr lsr1=new Lsr(r1.getIp("S0/0/1"),r2.getIp("S0/0/1"),1,r2.getRid(),r2.getRid());
            System.out.println("R2 receives LSR and respond with LSU containing its LSAs");
            Lsu lsu2=new Lsu(r2.getIp("S0/0/1"),r1.getIp("S0/0/1"));
            lsu2.setNumlsa(3);
            lsu2.setLsa(lsa21,1);
            lsu2.setLsa(lsa22,2);
            lsu2.setLsa(lsa23,3);
            //lsu2.printLsu();
            r1.receiveLsu(lsu2);
            r1.showLSDB();
            r1.buildRotable(r2.getRid());
            r1.showRotable();
            r2.buildRotable(r1.getRid());
            r2.showRotable();


            




        }
        

    }
    

    public static void main(String[] args) {
        Pc pc1=new Pc("pc1","192.168.1.2","3E0-77A-BC6-F8D-110-3C6","192.168.1.1","255.255.255.0");
        Pc pc2=new Pc("pc2","192.168.2.2","44-45-53-54-00-00","192.168.2.1","255.255.255.0");
        Pc pc3=new Pc("pc3","192.168.1.3","26-CE-91-6F-8B-B9","192.168.1.1","255.255.255.0");
        Router r1=new Router("R1");
        r1.setRid("192.168.1.1");
        r1.setInterfacega00("192.168.1.1", "255.255.255.0","192.168.1.0","18-F3-9B-C0-30-59");
        r1.setInterfaces001("172.37.30.1", "255.255.255.0","172.37.30.0","9A-99-FA-79-76-A7");
        Router r2 =new Router("R2");
        r2.setRid("192.168.2.1");
        r2.setInterfacega00("192.168.2.1","255.255.255.0","192.168.2.0","FC-61-59-2D-F8-73");
        r2.setInterfaces001("172.37.30.2","255.255.255.0","172.37.30.0","50-01-B1-69-8B-50");
        r1.buildRotable();
        r2.buildRotable();
        setOspf(r1,r2);
        pingtest(pc1,pc3,r1,r2);
        pingtest(pc1,pc2,r1,r2);
        /*String intf=r1.searchLSDB_intip("192.168.2.1");
        System.out.println(intf);
        String net=r1.searchLSDB_network("192.168.2.1");
        System.out.println(net);
        String mask=r1.searchLSDB_mask("192.168.2.1");
        System.out.println(mask);
        */
        GUI gui=new GUI(r1,r2,pc1,pc2);
        


        
        
    }





        
 }





        


        


       

