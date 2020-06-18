package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.modules.sys.service.SysUserRoleService;
import cn.cwnu.modules.sys.dao.SysUserRoleDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户与角色对应关系
 *
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public void saveOrUpdate(Integer id, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		//先删除用户与角色关系
		sysUserRoleDao.delete(id);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("roleIdList", roleIdList);
		sysUserRoleDao.save(map);
	}

	@Override
	public List<Long> queryRoleIdList(Integer userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void delete(Integer userId) {
		sysUserRoleDao.delete(userId);
	}
}
