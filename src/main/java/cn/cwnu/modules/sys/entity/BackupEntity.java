package cn.cwnu.modules.sys.entity;

import lombok.Data;

import java.util.Date;

/**
 * 备份记录信息
 *
 * @author zhugl
 */
@Data
public class BackupEntity {

    private Integer id;
    //备份所属实例的id
    private Integer instanceId;
    //备份方法(物理/逻辑)
    private String type;
    //备份文件大小
    private String size;
    //备份策略(实例/单库)
    private String strategy;
    //备份结束时间
    private String time;
    //备份所在主机
    private String host;
    //所在端口
    private String port;
    //备份文件名称
    private String backupName;
    //备份文件url
    private String url;

    public BackupEntity() {
    }

    public BackupEntity(String fileName, String hostIP, String hostPort, String savePath, String date) {
        this.backupName = fileName;
        this.host = hostIP;
        this.port = hostPort;
        this.url = savePath;
        this.time = date;
    }
}