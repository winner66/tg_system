package cn.cwnu.modules.business.service.impl;

import cn.cwnu.modules.business.dao.BuComTagDao;
import cn.cwnu.modules.business.dao.BuComTaskDao;
import cn.cwnu.modules.business.entity.BuComTagEntity;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import cn.cwnu.modules.business.service.BuComTagService;
import cn.cwnu.modules.business.service.BuComTaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 * 评论投稿分类
 * */
@Service("BuComTaskService")
public class BuComTaskServiceImpl implements BuComTaskService {

    @Autowired
    BuComTaskDao buComTaskDao;

    @Autowired
    BuComTaskService buComTaskService;

    @Override
    public boolean addComTask(BuComTaskEntity buComTaskEntity) {
        return buComTaskDao.addComTask(buComTaskEntity);
    }

    @Override
    public int queryMaxId() {
        return buComTaskDao.queryMaxId();
    }

    @Override
    public List<BuComTaskEntity> searchList(int tag, String taskname, int taskstatus, String starttime, String endtime,int offset,int limit) {
        return buComTaskDao.searchList(tag,taskname,taskstatus,starttime,endtime,offset,limit);
    }

    @Override
    public BuComTaskEntity queryById(int id) {
        return buComTaskDao.queryById(id);
    }

    @Override
    public boolean deleteComTask(int id) {
        return buComTaskDao.deleteComTask(id);
    }

    @Override
    public boolean updateComtask(BuComTaskEntity buComTaskEntity) {
        return buComTaskDao.updateComtask(buComTaskEntity);
    }

    @Override
    public List<Map<String,BuComTaskEntity>> pageList(int tag,int offset, int limit) {
        return buComTaskDao.pageList(tag,offset,limit);
    }

    @Override
    public int queryAll(int tag) {
        return buComTaskDao.queryAll(tag);
    }

    @Override
    public boolean stopComtask(int id) {
        return buComTaskDao.stopComtask(id);
    }

    @Override
    public List<Map<String, BuComTaskEntity>> queryAllList(String taskname, String starttime, String endtime) {
        return buComTaskDao.queryAllList(taskname,starttime,endtime);
    }

}
