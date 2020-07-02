package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.common.annotation.DataFilter;
import cn.cwnu.common.exception.RRException;
import cn.cwnu.modules.sys.dao.SysRoleDao;
import cn.cwnu.modules.sys.entity.SysRoleEntity;
import cn.cwnu.modules.sys.service.SysRoleDeptService;
import cn.cwnu.modules.sys.service.SysRoleMenuService;
import cn.cwnu.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    @DataFilter(tableAlias = "r", user = false)
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    @DataFilter(tableAlias = "r", user = false)
    public int queryTotal(Map<String, Object> map) {
        return sysRoleDao.queryTotal(map);
    }

    @Override
    @Transactional(rollbackFor = RRException.class)
    public void save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        sysRoleDao.save(role);
        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = RRException.class)
    public void update(SysRoleEntity role) {
        sysRoleDao.update(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }
    @Override
    @Transactional(rollbackFor = RRException.class)
    public void deleteBatch(Long[] roleIds) {
        sysRoleDao.deleteBatch(roleIds);
    }

    /**
     * 查询指定管理者角色  0/1
     *
     * @return
     * @param identityId
     */
    @Override
    public List<SysRoleEntity> getList(Integer identityId) {
        return sysRoleDao.getList(identityId);
    }

}
