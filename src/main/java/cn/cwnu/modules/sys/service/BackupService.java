package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.BackupEntity;

import java.util.Date;
import java.util.List;

/**
 * 数据库备份还原记录
 *
 * @author zgl
 */
public interface BackupService {

    /**
     * 查询所有备份信息
     *
     * @param searchText 查询条件
     * @return
     */
    List<BackupEntity> queryList(String searchText);

    /**
     * 保存备份记录
     *
     * @param fileName 文件名
     * @param hostIP   ip
     * @param hostPort port
     * @param savePath 位置
     */
    void save(String fileName, String hostIP, String hostPort,  String savePath);

    /**
     * 数据库信息删除
     *
     * @param id
     * @return
     */
    void delete(Integer id);
}
