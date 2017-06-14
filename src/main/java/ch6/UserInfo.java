package ch6;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by xdcao on 2017/6/14.
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID=1l;

    private String userName;

    private int userID;

    public UserInfo buildUserName(String userName){
        this.userName=userName;
        return this;
    }

    public UserInfo buildId(int userID){
        this.userID=userID;
        return this;
    }

    public final String getUserName(){
        return userName;
    }

    public final void setUserName(String userName){
        this.userName=userName;
    }

    public final int getUserID(){
        return this.userID;
    }

    public final void setUserID(int userID){
        this.userID=userID;
    }

    public byte[] codeC(){
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byte[] value=this.userName.getBytes();
        byteBuffer.putInt(value.length);
        byteBuffer.put(value);
        byteBuffer.putInt(this.userID);
        byteBuffer.flip();
        value=null;
        byte[] result=new byte[byteBuffer.remaining()];
        byteBuffer.get(result);
        return result;
    }

}
