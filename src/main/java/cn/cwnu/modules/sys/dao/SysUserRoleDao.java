package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 */
@Mapper
@Component
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Integer userId);
}
