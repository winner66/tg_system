package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.BackupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据库备份还原记录
 *
 * @author zgl
 */
@Mapper
@Component
public interface BackupDao {

    /**
     * 查询所有备份信息
     *
     * @param searchText 查询条件
     * @return
     */
    List<BackupEntity> queryList(@Param("searchText") String searchText);

    /**
     * 保存备份记录
     *
     * @param entity
     */
    void save(BackupEntity entity);

    /**
     * 数据库信息删除
     *
     * @param id
     * @return
     */
    void delete(Integer id);
}
