package cn.cwnu;

import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zgl
 * @date 2020/06/16 - 22:10
 */
@SpringBootApplication
public class TgSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //启动程序，关闭图标
        SpringApplication springApplication = new SpringApplication(TgSystemApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //配置jasyp环境
        return application.environment(new StandardEncryptableEnvironment()).sources(TgSystemApplication.class);
    }
}
