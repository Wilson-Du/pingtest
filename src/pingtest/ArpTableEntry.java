package pingtest;
class ArpTableEntry{
    private String ipadd;
    private String macadd;
    public ArpTableEntry(){
        ipadd="";
        macadd="";
    }
    public void setIpadd(String ipadd){
        this.ipadd=ipadd;
    }
    public void setMacadd(String macadd){
        this.macadd=macadd;
    }
    public String getIpadd(){
        return ipadd;
    }
    public String getMacadd(){
        return macadd;
    }
}