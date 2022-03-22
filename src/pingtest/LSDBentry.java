package pingtest;
class LSDBentry{
    private Lsaheader lsah;
    private int numlsa;
    private Lsa[] lsa=new Lsa[30];
    public LSDBentry(){
        lsah=new Lsaheader();
        numlsa=0;
        for(int i=0;i<lsa.length;i++){
            lsa[i]=new Lsa();
        }
    }
    public void setNumlsa(int n){
        numlsa=n;
    }
    public void setLsa(Lsa lsa1){
        int value=0;
        if(lsa[0].getLinkid()!=""){
            for(int m=0;m<lsa.length;m++){
                if(lsa[m].getLinkid()==""){
                    value=m;
                    break;
                }
            }
            
        }
        lsa[value]=lsa1;

    }
    public int getNumlsa(){
        return numlsa;
    }
    public String getLsah_linkstateid(){
        return lsah.getLinkstateid();
    }
    public int getLsah_type(){
        return lsah.getType();
    }
    public String getLsah_adv(){
        return lsah.getAdv();
    }
    public void setLsah(Lsaheader l){
        lsah=l;
    }
    public Lsaheader getLsah(){
        return lsah;
    }
    public String getLsalinkid(int i){
        return lsa[i].getLinkid();
    }
    public String getLsalinkdata(int j){
        return lsa[j].getLinkdata();
    }
    public void setLsas(Lsa[] l){
        lsa=l;
    }
    public void printLsah(){
        lsah.print();
    }
    public void printLsa(){
        for(int i=0;i<numlsa;i++){
            lsa[i].print();
        }
    }
}