package pingtest;
class Lsr{
    private IPheader iph;
    private int type;
    private String linkstateid;
    private String advrouter;
    public Lsr(String sip,String dip,int t,String l, String a){
        type=t;
        linkstateid=l;
        advrouter=a;
        iph=new IPheader(sip,dip);

    }
    public void show(){
        iph.showIpheader();
        System.out.println("Type "+type+" Link state ID: "+linkstateid+" Advrouter: "+advrouter);
    }
}