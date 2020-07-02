package cn.cwnu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置信息
 *
 * @author zhugl
 */
@Component
@ConfigurationProperties(prefix = "upload")
public class FileStorageProperties {
    private String fileDirectory;
    private boolean keepName;
    public boolean isKeepName() {
        return keepName;
    }

    public void setKeepName(boolean keepName) {
        this.keepName = keepName;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }
}
