package top.zerotop.blog.util;

import org.springframework.util.DigestUtils;

import java.util.Base64;

public class EncryptUtils {

    public static String MD5(String str) {
        if (null == str|| str.equals("")){
            return null;
        }
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String Base64(String str) {
        if (null == str|| str.equals("")){
            return null;
        }
        byte[] bytes = str.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

}
