package com.lc.util;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
/**
 *  @描述：RSA非对称加密(应用keypair随机生成密钥对er)
 *    description：
 ** @author LC
 */
@SuppressWarnings("all")
public class RSADemo {

    public RSADemo() {
    }
    PublicKey pbkey;
    PrivateKey prkey;
    public void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            pbkey = kp.getPublic();
            prkey = kp.getPrivate();
        } catch (Exception e) {
        }
    }

    //加密，需要公钥
    public byte[] encrypt(byte[] ptext) throws Exception {
        // 获取公钥及参数e,n
        RSAPublicKey pbk = (RSAPublicKey)pbkey;
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        // 获取明文m
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    //使用私钥进行解密
    public byte[] decrypt(byte[] ctext) throws Exception {
        // 读取密文
        BigInteger c = new BigInteger(ctext);
        // 读取私钥
        RSAPrivateKey prk = (RSAPrivateKey)prkey;
        BigInteger d = prk.getPrivateExponent();

        // 获取私钥参数及解密
        BigInteger n = prk.getModulus();
        BigInteger m = c.modPow(d, n);

        // 显示解密结果
        byte[] mt = m.toByteArray();
        return mt;
    }

    public static void main(String[] args) throws Exception {

        RSADemo rsaDemo = new RSADemo();
        rsaDemo.generateKey();
        String aa="asdasdasdasd";
        byte[] b=aa.getBytes();

        byte[] b1= rsaDemo.encrypt(b);
        System.out.println(new String(b1));


    }




}
