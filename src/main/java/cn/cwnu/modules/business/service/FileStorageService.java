package cn.cwnu.modules.business.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储服务
 *
 * @author zhugl
 */
public interface FileStorageService {

    /**
     * 存储文件
     *
     * @param file
     * @return 存储文件的文件名
     */
    String storeFile(MultipartFile file);

    /**
     * 删除文件
     *
     * @param name 文件名
     */
    void deleteFile(String name);
}
