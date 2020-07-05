package cn.cwnu.modules.business.service.impl;

import cn.cwnu.modules.business.dao.BuComTagDao;
import cn.cwnu.modules.business.entity.BuComTagEntity;
import cn.cwnu.modules.business.service.BuComTagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/*
 * 评论投稿分类
 * */
@Service("BuComTagService")
public class BuComTagServiceImpl implements BuComTagService {

    @Autowired
    BuComTagDao buComTagDao;

    @Autowired
    BuComTagService buComTagService;

    @Override
    public List<BuComTagEntity> queryList() {
        return buComTagDao.queryList();
    }

    @Override
    public int queryAll() {
        return buComTagDao.queryAll();
    }

    @Override
    public boolean canAdd(String buComtname) {
        if(buComTagDao.canAdd(buComtname)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addComTag(String buComtname) {
        return buComTagDao.addComTag(buComtname);
    }

    @Override
    public boolean deleteComTag(int buComtid) {
        return buComTagDao.deleteComTag(buComtid);
    }

    @Override
    public boolean updateComTag(@Param("buComtid")int buComtid, @Param("buComtname")String buComtname) {
        return buComTagDao.updateComTag(buComtid,buComtname);
    }

    @Override
    public List<BuComTagEntity> queryLists(Map<String, Object> map) {
        return buComTagDao.queryLists(map);
    }

    @Override
    public String queryById(int buComtid) {
        return buComTagDao.queryById(buComtid);
    }

}
