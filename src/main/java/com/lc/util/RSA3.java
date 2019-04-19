package com.lc.util;


import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;
import java.util.HashMap;
/**
 *  @描述：RSA非对称加密  kepair生成密钥对er
 *    description：公钥加密 私钥解密  针对生成加密处理过程
 ** @author LC
 */
@SuppressWarnings("all")
public class RSA3 {


    public static void main(String[] args) {
        try {
            testRsa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加解密测试２
     * 采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
     * @throws Exception
     */
    static void testRsa() throws Exception {
        // TODO Auto-generated method stub
        //生成公钥和私钥 (也就是随机生成的密码)
        HashMap<String, Object> map = getKeys();

        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");

        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
         //模  
        String modulus = publicKey.getModulus().toString();
        System.out.println("modulus: " + modulus);
        //公钥指数  
        String public_exponent = publicKey.getPublicExponent().toString();
         System.out.println("public_exponent: " + public_exponent);
        //私钥指数  
         String private_exponent = privateKey.getPrivateExponent().toString();
         System.out.println("private_exponent: " + private_exponent);
         //明文  
         String plainText = "账号 密码 xixix";
        System.out.println("明文为："+plainText);
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = getPublicKey(modulus, public_exponent);
        RSAPrivateKey priKey = getPrivateKey(modulus, private_exponent);
        //加密后的密文 : 下面采用分组加密的方式，明文可以比较长

        //公钥加密
        String encrypted = encryptByPublicKey(plainText, pubKey);

        encrypted = Base64.getEncoder().encodeToString(encrypted.getBytes());
//        System.err.println("encrypted hex str:\n" + encrypted);

        //通过网络等等传到客户端/服务端.............
//        encrypted = new String(Base64.getDecoder().decode(encrypted));
//        System.out.println("加密后明文为:"+encrypted);
//        //解密后的明文  
//        plainText = decryptByPrivateKey(encrypted, priKey);
//                System.out.println("解密后明文: " + plainText);
    }

    /**
     * 生成公钥和私钥
     * 可以理解为随机生成密码，用户只需要保存好对应的密钥对，不用关心密码到底是什么.
     * @throws NoSuchAlgorithmException
     * 
     */
    public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {
        HashMap<String, Object> meys = new HashMap<String, Object>();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        meys.put("public", publicKey);
        meys.put("private", privateKey);
        return meys;
    }


/**
     * X509解析，将字符串形式的公钥转换为公钥对象
     * @param publicKeyStr 公钥字符串
     * @return
     */
    public static RSAPublicKey keyStrToPublicKey(String publicKeyStr) {
        RSAPublicKey publicKey = null;
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }


    /**
     * X509解析，将字符串形式的私钥，转换为私钥对象
     * @param privateKeyStr s私钥字符串
     * @return
     */
    public static RSAPrivateKey keyStrToPrivate(String privateKeyStr) {
        RSAPrivateKey privateKey = null;
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }


    /**
     * 使用模和指数生成RSA公钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，
     * 不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
     * 
     * @param modulus
     *            模
     * @param exponent
     *            指数
     * @return
     */
    public static RSAPublicKey getPublicKey(String modulus, String exponent) {
        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 使用模和指数生成RSA私钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，
     * 不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
     * 
     * @param modulus
     *            模
     * @param exponent
     *            指数
     * @return
     */
    public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 公钥加密
     * 注：采用分组加密的方式，明文可以比较长，理论上无线长，但是太耗费时间
     * @param plainText 明文字符串
     * @param publicKey　公钥
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String plainText, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 模长
        int key_len = publicKey.getModulus().bitLength() / 8;
        // 加密数据长度 <= 模长-11
        String[] array = splitAsStringArray(plainText, key_len - 11);
//        System.out.println("encrypt key_len= " + key_len + ", plainText arrays= " + array.length);
        StringBuffer enBuff = new StringBuffer();
        // 如果明文长度大于模长-11则要分组加密
        for (String s : array) {
            byte[] ecData = cipher.doFinal(s.getBytes());
            //enBuff.append(bcd2Str(ecData));
            enBuff.append(byte2HexStr(ecData));
        }
        return enBuff.toString();
    }

    /**
     * 私钥解密
     * @param encryptedText 密文字符串(十六进制的刻度字符串)
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptedText, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 模长
        int key_len = privateKey.getModulus().bitLength() / 8;
        //十六进制的字符串转化为对应的byte数组
        //绝对不能直接encryptedText.getBytes()
        byte[] bytes = str2ByteArray(encryptedText);
        // 如果密文长度大于模长则要分组解密
        StringBuffer deBuff = new StringBuffer();
        byte[][] arrays = splitAsByteArray(bytes, key_len);
//        System.out.println("decrypt bytes length= " + bytes.length
//                + ",key_len= " + key_len + ", arrays= " + arrays.length);
        for (byte[] arr : arrays) {
            byte[] deData = cipher.doFinal(arr);
            deBuff.append(new String(deData));
        }
        return deBuff.toString();
    }

    /**
     * byte[]数据加密方式，整体加密，没有分组.
     * 注：data太长，117 byres以内，否则会出现异常：
     * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
     * @param data　　max 117 bytes
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * byte[]数据解密方式，整体解密，没有分组.
     * 注：data太长，117 byres以内，否则会出现异常：
     * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }


    /**
     * 拆分字符串: 按照给定的长度，把字符串拆分为数组
     */
    public static String[] splitAsStringArray(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring(i * len, i * len + y);
            } else {
                str = string.substring(i * len, i * len + len);
            }
            strings[i] = str;
        }
        return strings;
    }


    /**
     * 拆分数组
     */
    public static byte[][] splitAsByteArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }

    /**
          * 字节数组转化为大写16进制字符串
          *
          * @param b
          * @return
          */
         public static String byte2HexStr(byte[] b) {
             StringBuilder sb = new StringBuilder();
         for (int i = 0; i < b.length; i++) {
             String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
             sb.append("0");
             }
            sb.append(s.toUpperCase());
           }
         return sb.toString();
         }


    /**
          * 16进制字符串转为对应的字节数组
          * 注：一个byte可以存放两个英文字符
          * @param s
          * @return
          */
          public static byte[] str2ByteArray(String s) {
             int byteArrayLength = s.length() / 2;
             byte[] b = new byte[byteArrayLength];
            for (int i = 0; i < byteArrayLength; i++) {
                String bi = s.substring(i * 2, i * 2 + 2);
             byte b0 = (byte) Integer.valueOf(bi, 16).intValue();
             b[i] = b0;
            }
             return b;
         }


}
