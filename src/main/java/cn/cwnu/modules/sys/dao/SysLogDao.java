package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 系统日志
 *
 */
@Mapper
@Component
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
