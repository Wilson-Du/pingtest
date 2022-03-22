package pingtest;
class Dbd{
    private IPheader iph;
    private Lsaheader[] lsah=new Lsaheader[3];
    public Dbd(String sip,String dip){
        iph=new IPheader(sip,dip);
    }
    public void addLsah(Lsaheader lsa,int i){
        lsah[i-1]=lsa;
    }
    public Lsaheader getLsah(int i){
            return lsah[i-1];       
    }
}