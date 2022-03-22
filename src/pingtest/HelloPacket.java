package pingtest;
class HelloPacket{
    private IPheader iph;
    private String rid;
    private String areaid;
    private String mask;
    private String neighbor;
    private int helloint;
    private int deadint;
    public HelloPacket(String sip, String dip,String r,String a, String m,String n,int h,int d){
        iph=new IPheader(sip,dip);
        rid=r;
        areaid=a;
        mask=m;
        neighbor=n;
        helloint=h;
        deadint=d;
    }
    public String getRid(){
        return rid;
    }
    public String getAreaid(){
        return areaid;
    }
    public String getMask(){
        return mask;
    }
    public int getHelloint(){
        return helloint;
    }
    public int getDeadint(){
        return deadint;
    }
    public void printHello(){
        iph.showIpheader();
        System.out.println("RID:"+rid+" "+"area id:"+areaid+" "+"network mask:"+mask+" "+"hello interval:"+helloint+" "+"dead interval:"+deadint+" "+"neighbor:"+neighbor);
    }

    
}
