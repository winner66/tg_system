package cn.cwnu.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * tg_system
 *
 * @author zgl
 * @date 2020/06/18 - 12:27
 */
@Configuration
public class EncryptionConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        DESEncrypt desEncrypt = new DESEncrypt();
        return desEncrypt;
    }
}