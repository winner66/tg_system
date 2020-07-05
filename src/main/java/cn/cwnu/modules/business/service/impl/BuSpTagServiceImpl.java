package cn.cwnu.modules.business.service.impl;

import cn.cwnu.modules.business.dao.BuSpTagDao;
import cn.cwnu.modules.business.entity.BuSpTagEntity;
import cn.cwnu.modules.business.service.BuSpTagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service("BuSpTagService")
public class BuSpTagServiceImpl implements BuSpTagService {

    @Autowired
    BuSpTagDao buSpTagDao;

    @Autowired
    BuSpTagService buSpTagService;

    @Override
    public List<BuSpTagEntity> queryList() {
        return buSpTagDao.queryList();
    }

    @Override
    public int queryAll() {
        return buSpTagDao.queryAll();
    }

    @Override
    public boolean canAdd(String buSptname) {
        if(buSpTagDao.canAdd(buSptname)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addSpTag(String buSptname) {
        return buSpTagDao.addSpTag(buSptname);
    }

    @Override
    public boolean deleteSpTag(int buSptid) {
        return buSpTagDao.deleteSpTag(buSptid);
    }

    @Override
    public boolean updateSpTag(@Param("buSptid")int buSptid, @Param("buSptname")String buSptname) {
        return buSpTagDao.updateSpTag(buSptid,buSptname);
    }

    @Override
    public List<BuSpTagEntity> queryLists(Map<String, Object> map) {
        return buSpTagDao.queryLists(map);
    }

}
