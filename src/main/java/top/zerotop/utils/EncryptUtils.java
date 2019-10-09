package top.zerotop.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.UUID;

public class EncryptUtils {
    public static String MD5(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String Base64(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
