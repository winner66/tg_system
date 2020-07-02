package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author zgl
 */
public interface SysUserService {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Integer userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Integer userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    SysUserEntity queryObject(Integer userId);

    /**
     * 查询用户列表
     */
    List<SysUserEntity> queryList(Map<String, Object> map);

    /**
     * 保存用户
     */
    void save(SysUserEntity user);

    /**
     * 修改用户
     */
    void update(SysUserEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Integer[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Integer userId, String password, String newPassword);

    /**
     * 禁用用户
     *
     * @param userIds
     * @return
     */
    void banBatch(Integer[] userIds);
    void ban(Integer id);
    /**
     * 激活用户
     *
     * @param userIds
     * @return
     */
    void useBatch(Integer[] userIds);
    void use(Integer id);
    /**
     * 条件查询用户
     *
     * @param deptId
     * @param identityId
     * @return
     */
    List<SysUserEntity> getList(Long deptId, Integer identityId);

    /**
     * 更新用户在线状态
     *
     * @param id
     * @param i
     */
    void updateOnline(Integer id, int i);
}
