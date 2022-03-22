package pingtest;
class Lsa{
    private String linkid;
    private String linkdata;
    private Lsaheader lsah;
    public Lsa(String li,String ld,Lsaheader lh){
        linkid=li;
        linkdata=ld;
        lsah=lh;
    }
    public Lsa(){
        linkid="";
        linkdata="";
    }
    public void setLinkid(String li){
        linkid=li;
    }
    public void setLinkdata(String ld){
        linkdata=ld;
    }
    public void setLsah(Lsaheader lh){
        lsah=lh;

    }
    public String getLinkid(){
        return linkid;
    }
    public String getLinkdata(){
        return linkdata;
    }
    public Lsaheader getLsah(){
        return lsah;
    }
    public void print(){
        System.out.println("LSA:");
        //lsah.print();
        System.out.println("Link id:"+linkid+" "+"Link Data:"+linkdata);
    }

}