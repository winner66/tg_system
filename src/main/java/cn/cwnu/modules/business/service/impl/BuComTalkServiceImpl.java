package cn.cwnu.modules.business.service.impl;

import cn.cwnu.modules.business.dao.BuComTalkDao;
import cn.cwnu.modules.business.dao.BuComTaskDao;
import cn.cwnu.modules.business.entity.BuComTalkEntity;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import cn.cwnu.modules.business.service.BuComTalkService;
import cn.cwnu.modules.business.service.BuComTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 * 评论投稿分类
 * */
@Service("BuComTalkService")
public class BuComTalkServiceImpl implements BuComTalkService {

    @Autowired
    BuComTalkDao buComTalkDao;

    @Autowired
    BuComTalkService buComTalkService;

    @Override
    public boolean pass(int id) {
        return buComTalkDao.pass(id);
    }

    @Override
    public boolean stop(int id, String reason) {
        return buComTalkDao.stop(id,reason);
    }

    @Override
    public boolean reward(int id, int reward) {
        return buComTalkDao.reward(id,reward);
    }

    @Override
    public int queryAll(int taskid) {
        return buComTalkDao.queryAll(taskid);
    }

    @Override
    public BuComTalkEntity queryById(int id) {
        return buComTalkDao.queryById(id);
    }

    @Override
    public List<Map<String, BuComTalkEntity>> queryAllList(String taskname, String starttime, String endtime) {
        return buComTalkDao.queryAllList(taskname,starttime,endtime);
    }

    @Override
    public List<Map<String, BuComTalkEntity>> pageList(int taskid, int offset, int limit) {
        return buComTalkDao.pageList(taskid, offset, limit);
    }

    @Override
    public List<BuComTalkEntity> searchList(int taskid, String name, String title, int status, String starttime, String endtime, int offset, int limit) {
        return buComTalkDao.searchList(taskid,name,title,status,starttime,endtime,offset,limit);
    }

    @Override
    public int queryMAX() {
        return buComTalkDao.queryMAX();
    }
}
