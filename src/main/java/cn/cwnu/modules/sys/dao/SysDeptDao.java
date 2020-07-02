package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysDeptEntity;
import cn.cwnu.modules.sys.entity.organizationTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 机构管理
 *
 * @author zgl
 */
@Mapper
@Component
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    /**
     * 查询子机构ID列表
     *
     * @param parentId 上级机构ID
     */
    List<Long> queryDetpIdList(Long parentId);

    List<organizationTree> queryObjectByParentId(Long parentId);
    /**
     * 查询子机构数量
     *
     * @param parentId 上级机构ID
     */
    int countDetpIdList(Long parentId);

    /**
     * 获取机构dropdown list
     */
    List<SysDeptEntity> queryDropDownList(Map<String, Object> map);

    /**
     * 根据机构名称获取机构编号
     *
     * @param name
     */
    String queryNumByName(@Param("name") String name);

    /**
     * 根据机构id获取机构编号
     *
     * @param deptId
     * @return
     */
    String getDepartNum(@Param("id") Integer deptId);
}
