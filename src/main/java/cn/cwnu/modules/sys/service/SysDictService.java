package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 字典
 * 
 * @author zhugl
 */
public interface SysDictService {
	
	SysDictEntity queryObject(String id);
	
	List<SysDictEntity> queryList(Map<String, Object> map);

	List<SysDictEntity> queryGroupList();

	List<String> selectDictsByCode(String code);

	int queryTotal(Map<String, Object> map);
	
	void save(SysDictEntity sysDict);
	
	void update(SysDictEntity sysDict);
	
	void delete(String id);

	void deleteByParentId(String id);
	
	void deleteBatch(String[] ids);
}
