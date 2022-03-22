package pingtest;
class Lsaheader{
    private int age;
    private int type;
    private String linkstateid;
    private String advrouter;
    public Lsaheader(){
        linkstateid="";
        advrouter="";
    }
    public Lsaheader(int a,int t,String l,String adv){
        age=a;
        type=t;
        linkstateid=l;
        advrouter=adv;
    }
    public int getAge(){
        return age;
    }
    public int getType(){
        return type;
    }
    public String getLinkstateid(){
        return linkstateid;
    }
    public String getAdv(){
        return advrouter;
    }
    public void setAge(int a){
        age=a;
        
    }
    public void setType(int t){
        type=t;
    }
    public void setLinkstateid(String l){
        linkstateid=l;
    }
    public void setAdv(String adv){
        advrouter=adv;
    }
    public void print(){
        System.out.println("LSA header:");
        System.out.println("age:"+age+" "+"type:"+type+" "+"Link state id:"+linkstateid+" "+"Advertising router:"+advrouter);
    }

}