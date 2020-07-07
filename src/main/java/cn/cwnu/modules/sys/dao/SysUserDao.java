package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@Mapper
@Component
public interface SysUserDao extends BaseDao<SysUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Integer userId);
    int delete(Integer id);
    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Integer userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);

    /**
     * 激活用户
     *
     * @param userIds
     * @return
     */
    void useBatch(Integer[] userIds);
    void use(Integer id);


    /**
     * 禁用用户
     *
     * @param userIds
     * @return
     */
    void banBatch(Integer[] userIds);
    void ban(Integer id);
    /**
     * 条件查询用户
     *
     * @param deptId
     * @param identityId
     * @return
     */
    List<SysUserEntity> getList(@Param("deptId") Long deptId,@Param("identityId") Integer identityId);

    /**
     * 更新用户在线状态
     *
     * @param id
     * @param i
     */
    void updateOnline(@Param("id") Integer id,@Param("i") int i);
}
