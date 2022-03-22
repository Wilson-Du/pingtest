package pingtest;
class Interface{
    private String name;
    private String ipaddress;
    private String macaddress;
    private String submask;
    private String network;
    public Interface(){
        name="";
        ipaddress="";
        macaddress="";
        submask="";
        network="";
     }
    public void setName(String name){
        this.name=name;
    }
    public void setIp(String ipaddress){
        this.ipaddress=ipaddress;
    }
    public void setMask(String submask){
        this.submask=submask;
    }
    public void distributemac(String macaddress){
        this.macaddress=macaddress;
    }
    public void setNetwork(String network){
        this.network=network;
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
    public String getMask(){
        return submask;
    }
    public String getNetwork(){
        return network;
    }
    

}