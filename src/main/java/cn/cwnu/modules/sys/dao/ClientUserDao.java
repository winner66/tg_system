package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.ClientUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 人员管理
 *
 * @author zgl
 */
@Mapper
@Component
public interface ClientUserDao {

    /**
     * 查询所有信息
     *
     * @param searchText 查询条件
     * @return
     */
    List<ClientUserEntity>  queryInfoList(@Param("searchText") String searchText);

    List<ClientUserEntity>  queryByGroup(@Param("gid") Long gid);
    List<ClientUserEntity>  queryByDept(@Param("id") Long id);
    List<ClientUserEntity>  queryAllByDept(@Param("id") Long id);

    void save(ClientUserEntity user);
    /**
     * 批量导入数据
     *
     * @param manageEntities 数据
     */
    void batchInsert(List<ClientUserEntity> manageEntities);

    /**
     * 禁用用户
     *
     * @param id
     */
    void ban(@Param("id") Integer id);

    /**
     * 激活用户
     *
     * @param id
     */
    void use(@Param("id") Integer id);
}
