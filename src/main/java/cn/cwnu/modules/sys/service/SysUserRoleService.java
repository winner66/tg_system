package cn.cwnu.modules.sys.service;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author zgl
 */
public interface SysUserRoleService {

    /**
     * 保存或者更改
     */
    void saveOrUpdate(Integer userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Integer userId);

    /**
     * 根据id删除
     */
    void delete(Integer userId);
}
