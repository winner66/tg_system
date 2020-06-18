package cn.cwnu.config;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * tg_system
 *
 * @author zgl
 * @date 2020/06/18 - 12:19
 */
public class DESEncrypt implements StringEncryptor {

    String password = "tg_system";

    @Override
    public String encrypt(String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));

        return encryptor.encrypt(value);
    }

    /**
     * 解密
     *
     * @param value 解密密文
     * @return
     */
    @Override
    public String decrypt(String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));

        return encryptor.decrypt(value);
    }

    public static SimpleStringPBEConfig cryptor(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

//    public static void main(String[] args) {
//        DESEncrypt desEncrypt = new DESEncrypt();
//        System.out.println(desEncrypt.encrypt("root"));
//        System.out.println(desEncrypt.decrypt("b23VDk+4wxd1D0EJ/F6JgQHYqg5iv7EjG+KMcVbajd8="));
//    }
}
