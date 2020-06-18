package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.common.annotation.DataFilter;
import cn.cwnu.modules.sys.dao.SysDeptDao;
import cn.cwnu.modules.sys.entity.SysDeptEntity;
import cn.cwnu.modules.sys.service.SysDeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 机构业务逻辑实现类
 *
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public SysDeptEntity queryObject(Long deptId) {
        return sysDeptDao.queryObject(deptId);
    }

    @Override
    @DataFilter(tableAlias = "d", user = false)
    public List<SysDeptEntity> queryList(Map<String, Object> map) {
        return sysDeptDao.queryList(map);
    }

    @Override
    @DataFilter(tableAlias = "d", user = false)
    public List<SysDeptEntity> queryDropDownList(Map<String, Object> map) {
        return sysDeptDao.queryDropDownList(map);
    }

    @Override
    public void save(SysDeptEntity sysDept) {
        sysDeptDao.save(sysDept);
    }

    @Override
    public void update(SysDeptEntity sysDept) {
        sysDeptDao.update(sysDept);
    }

    @Override
    public void delete(Long deptId) {
        sysDeptDao.delete(deptId);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return sysDeptDao.queryDetpIdList(parentId);
    }

    @Override
    public String getSubDeptIdList(Long deptId) {
        //部门及子机构ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子机构ID
        List<Long> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本机构
        deptIdList.add(deptId);

        String deptFilter = StringUtils.join(deptIdList, ",");
        return deptFilter;
    }

    @Override
    public int queryAll() {
        return sysDeptDao.queryAll();
    }

    @Override
    public String getDepartNum(Integer deptId) {
        return sysDeptDao.getDepartNum(deptId);
    }

    /**
     * 递归
     */
    private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }
            deptIdList.add(deptId);
        }
    }
}
