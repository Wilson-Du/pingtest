package pingtest;
class IcmpPacket{
    private int type;
    private int code;
    private String checksum;
    public IcmpPacket(){}
    public IcmpPacket( int ty, int co,String ch){
        type=ty;
        code=co;
        checksum=ch;
    }
    /*public int getType(){
        return type;

    }
    public int getCode(){
        return code;

    }
    public String getChecksum(){
        return checksum;

    }
    */
    public void printReq(){
        System.out.println("Type:"+type+" "+"Code:"+code+" "+"Checksum:" + checksum);

    }
    public void printReply(){
        System.out.println("IcmpPacket echo-reply packet\n"+"Type:"+type+" "+"Code:"+code+" "+"Checksum:" + checksum);
    }
}