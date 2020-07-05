package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.modules.sys.dao.SysGroupDao;
import cn.cwnu.modules.sys.entity.SysGroupEntity;
import cn.cwnu.modules.sys.service.SysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SysGroupService")
public class SysGroupServiceimpl implements SysGroupService {
    @Autowired
    private SysGroupDao sysGroupDao;

    @Override
    public SysGroupEntity queryObject(Long groupId) {
        return sysGroupDao.queryObject(groupId);
    }
    public void delete(Long gid){
        sysGroupDao.delete(gid);
    }

    @Override
    public List<SysGroupEntity> queryObjectByDeptId(Long deptId) {
        return sysGroupDao.queryObjectByDeptId(deptId);
    }

    @Override
    public void save(SysGroupEntity item) {
        sysGroupDao.save(item);
    }

    @Override
    public void saveBatch(List<SysGroupEntity> list) {
        sysGroupDao.saveBatch(list);
    }

    @Override
    public void updateName(SysGroupEntity item) {
        sysGroupDao.updateName(item);
    }

    @Override
    public SysGroupEntity queryDeptByGid(Long groupId) {

        return sysGroupDao.queryDeptByGid(groupId);
    }

    @Override
    public List<SysGroupEntity> queryGroupByDid(Long deptId) {
        return sysGroupDao.queryGroupByDid(deptId);
    }
    @Override
    public List<SysGroupEntity>  queryGroupByPGid(Long pid) {
        return sysGroupDao.queryGroupByPGid(pid);
    }

    @Override
    public Integer countGroupIdList(Long groupId) {
        return sysGroupDao.countGroupIdList(groupId);
    }

    @Override
    public Integer queryTotal() {
        return sysGroupDao.queryTotal();
    }
}
