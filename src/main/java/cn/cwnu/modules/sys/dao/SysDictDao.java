package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.SysDictEntity;
import cn.cwnu.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典
 * 
 * @author zhugl
 */
@Mapper
@Component
public interface SysDictDao extends BaseDao<SysDictEntity> {
	List<SysDictEntity> queryGroupList();
	List<String> selectDictsByCode(String code);
	void deleteByParentId(String id);
}
