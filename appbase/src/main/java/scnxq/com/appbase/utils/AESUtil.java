package scnxq.com.appbase.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 * AES加密模式:ECB 填充为:PKCS5Padding 字符集:utf-8 无偏移量
 * 验证网站：http://tool.chacuo.net/cryptaes
 * </pre>
 */
public class AESUtil {
    public static final Charset CHARSET = Charset.forName("UTF-8");
    // 填充方法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密字符串
     *
     * @return base64后的字符串
     */
    public static String encrypt(String aesPwd, String plainText) {
        try {
            SecretKeySpec skey = new SecretKeySpec(aesPwd.getBytes(CHARSET), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            byte[] encrypt = cipher.doFinal(plainText.getBytes(CHARSET));
            return Base64.encodeBase64String(encrypt).trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密字符串
     */
    public static String decrypt(String aesPwd, String base64EncryptStr) {
        try {
            SecretKeySpec skey = new SecretKeySpec(aesPwd.getBytes(CHARSET), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, skey);
            byte[] decrypt = cipher.doFinal(Base64.decodeBase64(base64EncryptStr));
            return new String(decrypt).trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
