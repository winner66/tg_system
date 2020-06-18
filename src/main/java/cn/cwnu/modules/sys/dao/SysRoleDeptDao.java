package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysRoleDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色与部门对应关系
 */
@Mapper
@Component
public interface SysRoleDeptDao extends BaseDao<SysRoleDeptEntity> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long roleId);
}
