package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.common.annotation.DataFilter;
import cn.cwnu.common.exception.RRException;
import cn.cwnu.common.utils.Constant;
import cn.cwnu.common.utils.DateUtils;
import cn.cwnu.modules.sys.dao.SysUserDao;
import cn.cwnu.modules.sys.entity.SysUserEntity;
import cn.cwnu.modules.sys.service.SysUserRoleService;
import cn.cwnu.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<String> queryAllPerms(Integer userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Integer userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserEntity queryObject(Integer userId) {
        return sysUserDao.queryObject(userId);
    }

    /**
     * 用户列表
     *
     * @param map
     * @return
     */
    @Override
    @DataFilter(tableAlias = "u", user = false)
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    /**
     * 保存用户
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = RRException.class)
    public void save(SysUserEntity user) {
        user.setCreateTime(DateUtils.formatDate(new Date()));
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(Constant.SALT_LENGTH);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setIdentity(Constant.adminType(user.getIdentityId()));
        user.setOnline(0);

        System.out.println(user);
        sysUserDao.save(user);
        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    /**
     * 修改用户
     */
    @Override
    @Transactional(rollbackFor = RRException.class)
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysUserDao.update(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    /**
     * 删除用户
     */
    @Override
    @Transactional(rollbackFor = RRException.class)
    public void deleteBatch(Integer[] userId) {
        sysUserDao.deleteBatch(userId);
    }

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    @Override
    public int updatePassword(Integer userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    /**
     * 禁用用户
     *
     * @param userIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = RRException.class)
    public void banBatch(Integer[] userIds) {
        sysUserDao.banBatch(userIds);
    }
    public void ban(Integer id) {
        sysUserDao.ban(id);
    }
    /**
     * 激活用户
     *
     * @param userIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = RRException.class)
    public void useBatch(Integer[] userIds) {
        sysUserDao.useBatch(userIds);
    }
    public void use(Integer id) {
        sysUserDao.use(id);
    }
    /**
     * 条件查询用户
     *
     * @param deptId
     * @param identityId
     * @return
     */
    @Override
    public List<SysUserEntity> getList(Long deptId, Integer identityId) {
        return sysUserDao.getList(deptId,identityId);
    }

    /**
     * 更新用户在线状态
     *
     * @param id
     * @param i
     */
    @Override
    public void updateOnline(Integer id, int i) {
        sysUserDao.updateOnline(id,i);
    }

}
