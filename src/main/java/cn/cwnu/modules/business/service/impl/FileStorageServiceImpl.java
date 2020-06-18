package cn.cwnu.modules.business.service.impl;

import cn.cwnu.common.exception.FileStorageException;
import cn.cwnu.config.FileStorageProperties;
import cn.cwnu.modules.business.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 文件存储
 *
 * @author zhugl
 */
@Service(value = "fileService")
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;
    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getFileDirectory())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 上传文件保存到文件夹
     *
     * @param file 文件数据
     * @return 文件名+","+文件地址
     */
    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // 获取图片的扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //不保持文件名
        if (!fileStorageProperties.isKeepName()) {
            fileName = System.currentTimeMillis() + "." + extensionName;
        }
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName + "," + targetLocation.toString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 删除文件
     *
     * @param name 文件名
     */
    @Override
    public void deleteFile(String name) {
        String path = this.fileStorageLocation.resolve(name).toString();
        File file = new File(path);
        file.delete();
    }

}
