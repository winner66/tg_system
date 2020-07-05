package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.SysGroupEntity;

import java.util.List;

public interface SysGroupService {

    SysGroupEntity queryObject(Long groupId);
    List<SysGroupEntity> queryObjectByDeptId(Long deptId);
    void save(SysGroupEntity item);
    void saveBatch(List<SysGroupEntity> list);
    void updateName(SysGroupEntity item);
    List<SysGroupEntity> queryGroupByPGid(Long pid);
    SysGroupEntity queryDeptByGid(Long groupId);
    List<SysGroupEntity> queryGroupByDid(Long deptId);

    Integer countGroupIdList(Long groupId);
    //    一共有多少组?
    Integer queryTotal();
    void delete(Long gid);
}
