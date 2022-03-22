package pingtest;
class RoTableEntry{
    private String intname;
    private String type;
    private String status;
    private String ipaddress;
    private String network;
    private String subnetmask;
    public RoTableEntry(){
        intname="";
        type="";
        status="";
        ipaddress="";
        network="";
        subnetmask="";
    }
    public void setIntname(String intname){
        this.intname=intname;
    }
    public void setIpddress(String ipaddress){
        this.ipaddress=ipaddress;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setNetwork(String n){
        network=n;
    }
    public void setSub(String s){
        subnetmask=s;
    }
    public String getIntname(){
        return intname;
    }
    public String getIpaddress(){
        return ipaddress;
    }
    public String getType(){
        return type;
    }
    public String getStatus(){
        return status;
    }
    public String getNetwork(){
        return network;
    }
    public String getSub(){
        return subnetmask;
    }
    public void show(){
        System.out.println(type+"  "+network+" "+ipaddress+" "+subnetmask+" "+status+" "+intname);

    }
}



