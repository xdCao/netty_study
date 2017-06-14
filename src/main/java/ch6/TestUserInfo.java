package ch6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by xdcao on 2017/6/14.
 */
public class TestUserInfo {

    public static void main(String[] args) throws IOException {
        UserInfo userInfo=new UserInfo();
        userInfo.buildUserName("welcome to netty").buildId(100);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(userInfo);
        oos.flush();
        oos.close();
        byte[] b=bos.toByteArray();
        System.out.println("The jdk serializable length is: "+b.length);
        bos.close();
        System.out.println("---------------------------------------------");
        System.out.println("The byte array serializable length is:"+userInfo.codeC().length);
    }

}
