package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色管理
 *
 */
@Mapper
@Component
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    List<SysRoleEntity> getList(Integer identityId);
}
