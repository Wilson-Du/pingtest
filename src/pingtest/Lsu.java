package pingtest;
class Lsu{
    private IPheader iph;
    private int numlsa;
    private Lsa[] lsa=new Lsa[30];
    public Lsu(String sip,String dip){
        iph=new IPheader(sip,dip);
    }
    public void setNumlsa(int n){
        numlsa=n;
    }
    public void setLsa(Lsa lsa1,int i){
        lsa[i-1]=lsa1;

    }
    public void printLsu(){
        System.out.println("LSU:");
        System.out.println("number of LSA:"+numlsa);
        for(int n=0;n<numlsa;n++){

            lsa[n].print();
        }
    }
    public Lsa getLsa(int i){
        return lsa[i-1];
    }
    public int getNumlsa(){
        return numlsa;
    }
    public Lsa[] getLsa(){
        return lsa;
    }
    

}
