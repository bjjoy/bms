package cn.bjjoy.bms.util;


import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author bjjoy 2017/11/05
 */
public class EncryptUtils {

    /**
     * 对字符串进行MD5进行加密处理
     * @param msg 待加密的字符串
     * @return 加密后字符串
     */
    public static String encryptMD5(String msg){
        return encrypt(msg, null);
    }

    /**
     * 基本加密处理
     * @param msg
     * @param type
     * @return
     */
    private static String encrypt(String msg, String type){
        MessageDigest md;
        StringBuilder password = new StringBuilder();

        try {
            md = MessageDigest.getInstance("MD5");

            if(StringUtils.isNotBlank(type)){
                md.update(type.getBytes());
            }else {
                md.update(msg.getBytes());
            }

            byte[] bytes = md.digest();
            for (int i = 0; i < bytes.length; i++) {
                String param = Integer.toString((bytes[i] & 0xff) + 0x100, 16);
                password.append(param.substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return password.toString();
    }

    /**
     * 盐值的原理非常简单，就是先把密码和盐值指定的内容合并在一起，再使用md5对合并后的内容进行演算，
     * 这样一来，就算密码是一个很常见的字符串，再加上用户名，最后算出来的md5值就没那么容易猜出来了。
     * 因为攻击者不知道盐值的值，也很难反算出密码原文。
     * @param msg
     * @return
     */
    public static String encryptSalt(String msg) {
        String salt = "";
        return encrypt(msg, salt);
    }

    /**
     * SHA（Secure Hash Algorithm，安全散列算法）是消息摘要算法的一种，被广泛认可的MD5算法的继任者。
     * SHA算法家族目前共有SHA-0、SHA-1、SHA-224、SHA-256、SHA-384和SHA-512五种算法，
     * 通常将后四种算法并称为SHA-2算法
     * @param msg
     * @return
     */
    public static String encryptSHA(String msg) {
        String salt = getSaltSHA1();
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(msg.getBytes());
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        }catch(Exception e){

        }

        return sb.toString();
    }

    /**
     * PBKDF2加密
     * @param msg
     * @return
     */
    public static String encryptPBKDF2(String msg) {
        try {
            int iterations = 1000;
            char[] chars = msg.toCharArray();
            byte[] salt = getSalt().getBytes();

            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            return iterations + toHex(salt) + toHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 转化十六进制
     * @param array
     * @return
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    /**
     * [list]
     *  [*]SHA-1 (Simplest one – 160 bits Hash)

     *  [*]SHA-256 (Stronger than SHA-1 – 256 bits Hash)

     *  [*]HA-384 (Stronger than SHA-256 – 384 bits Hash)

     *  [*]SHA-512 (Stronger than SHA-384 – 512 bits Hash)

     * [/list]
     * @return
     */
    private static String getSaltSHA1(){
        SecureRandom sr;
        byte[] salt = new byte[16];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return salt.toString();
    }


    /**
     * 先把密码和盐值指定的内容合并在一起，再使用sha1对合并后的内容进行演算，
     * @return
     */
    private static String getSalt(){
        SecureRandom sr;
        byte[] salt = new byte[16];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            sr.nextBytes(salt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return salt.toString();
    }

    /**
     * @param length
     * 获取一个随机字符串
     */
    public static String randomCode(int length) {
        String str = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        String str2[] = str.split(",");//将字符串以,分割
        Random rand = new Random();//创建Random类的对象rand
        int index = 0;
        String randStr = "";//创建内容为空字符串对象randStr
        randStr = "";//清空字符串对象randStr中的值
        for (int i = 0; i < length; ++i) {
            index = rand.nextInt(str2.length - 1);//在0到str2.length-1生成一个伪随机数赋值给index
            randStr += str2[index];//将对应索引的数组与randStr的变量值相连接
        }
        return randStr;//输出所求的验证码的值

    }

}