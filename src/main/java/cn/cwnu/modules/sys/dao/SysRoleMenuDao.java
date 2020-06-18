package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 */
@Mapper
@Component
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
