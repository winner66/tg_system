package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 机构管理
 *
 * @author zgl
 */
public interface SysDeptService {

    SysDeptEntity queryObject(Long deptId);

    List<SysDeptEntity> queryList(Map<String, Object> map);

    List<SysDeptEntity> queryDropDownList(Map<String, Object> map);

    void save(SysDeptEntity sysDept);

    void update(SysDeptEntity sysDept);

    void delete(Long deptId);

    /**
     * 查询子机构ID列表
     *
     * @param parentId 上级机构ID
     */
    List<Long> queryDetpIdList(Long parentId);

    /**
     * 获取子机构ID(包含本机构ID)，用于数据过滤
     */
    String getSubDeptIdList(Long deptId);

    int queryAll();

    /**
     * 根据机构id获取机构编号
     *
     * @param deptId
     * @return
     */
    String getDepartNum(Integer deptId);
}
