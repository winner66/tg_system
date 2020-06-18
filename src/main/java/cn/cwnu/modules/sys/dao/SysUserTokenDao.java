package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 系统用户Token
 */
@Mapper
@Component
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {

    SysUserTokenEntity queryByUserId(Long userId);

    SysUserTokenEntity queryByToken(String token);

}
