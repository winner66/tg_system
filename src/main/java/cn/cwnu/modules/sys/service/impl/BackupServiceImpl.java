package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.common.utils.DateUtils;
import cn.cwnu.modules.sys.dao.BackupDao;
import cn.cwnu.modules.sys.service.BackupService;
import cn.cwnu.modules.sys.entity.BackupEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 数据库备份还原记录
 *
 * @author zgl
 */
@Service("backupService")
public class BackupServiceImpl implements BackupService {
    @Autowired
    private BackupDao backupDao;

    /**
     * 查询所有备份信息
     *
     * @param searchText 查询条件
     * @return
     */
    @Override
    public List<BackupEntity> queryList(String searchText) {
        if (StringUtils.isBlank(searchText)) {
            searchText = "";
        }
        return backupDao.queryList(searchText);
    }

    /**
     * 保存备份记录
     *
     * @param fileName 文件名
     * @param hostIP   ip
     * @param hostPort port
     * @param savePath 位置
     */
    @Override
    public void save(String fileName, String hostIP, String hostPort,  String savePath) {
        backupDao.save(new BackupEntity(fileName,hostIP,hostPort,savePath, DateUtils.formatDate(new Date())));
    }

    /**
     * 数据库信息删除
     *
     * @param id
     * @return
     */
    @Override
    public void delete(Integer id) {
        backupDao.delete(id);
    }
}
