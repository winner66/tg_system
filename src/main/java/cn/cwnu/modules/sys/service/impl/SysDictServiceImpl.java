package cn.cwnu.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.cwnu.modules.sys.dao.SysDictDao;
import cn.cwnu.modules.sys.entity.SysDictEntity;
import cn.cwnu.modules.sys.service.SysDictService;



@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService {
	@Autowired
	private SysDictDao sysDictDao;
	
	@Override
	public SysDictEntity queryObject(String id){
		return sysDictDao.queryObject(id);
	}
	
	@Override
	public List<SysDictEntity> queryList(Map<String, Object> map){
		return sysDictDao.queryList(map);
	}

	@Override
	public List<SysDictEntity> queryGroupList() {
		return sysDictDao.queryGroupList();
	}

	@Override
	public List<String> selectDictsByCode(String code) {
		return sysDictDao.selectDictsByCode(code);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return sysDictDao.queryTotal(map);
	}
	
	@Override
	public void save(SysDictEntity sysDict){
		sysDictDao.save(sysDict);
	}
	
	@Override
	public void update(SysDictEntity sysDict){
		sysDictDao.update(sysDict);
	}
	
	@Override
	public void delete(String id){
		deleteByParentId(id);
		sysDictDao.delete(id);
	}

	@Override
	public void deleteByParentId(String id) {
		sysDictDao.deleteByParentId(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		sysDictDao.deleteBatch(ids);
	}
	
}
