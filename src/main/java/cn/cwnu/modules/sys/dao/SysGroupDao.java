package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.ClientUserEntity;
import cn.cwnu.modules.sys.entity.SysGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysGroupDao extends BaseDao<SysGroupEntity> {
    SysGroupEntity queryObject(Long groupId);
    List<SysGroupEntity> queryObjectByDeptId(Long deptId);
    void save(SysGroupEntity item);
    void saveBatch(List<SysGroupEntity> list);
    void updateName(SysGroupEntity item);

    SysGroupEntity queryDeptByGid(Long groupId);

    List<SysGroupEntity> queryGroupByPGid(Long groupId);

    List<SysGroupEntity> queryGroupByDid(Long deptId);

    Integer countGroupIdList(Long groupId);
//    一共有多少组?
    Integer queryTotal();

}
