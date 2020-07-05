package cn.cwnu.modules.business.service;
import cn.cwnu.modules.business.entity.BuComTalkEntity;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
 * 评论投稿分类
 * */
public interface BuComTalkService {
    /*通过*/
    boolean pass(int id);

    /*拒绝*/
    boolean stop(int id,String reason);

    /*给奖励*/
    boolean reward(int id,int reward);

    /*查询数量*/
    int queryAll(int taskid);

    /*根据id查询*/
    BuComTalkEntity queryById(int id);

    /*为稿件查询所有*/
    List<Map<String,BuComTalkEntity>> queryAllList(@Param("taskname") String taskname, @Param("starttime") String starttime, @Param("endtime") String endtime);

    /*分页*/
    List<Map<String,BuComTalkEntity>> pageList(@Param("taskid") int taskid, @Param("offset") int offset, @Param("limit") int limit);

    /*搜索查询*/
    List<BuComTalkEntity> searchList(@Param("taskid") int taskid, @Param("name") String name, @Param("title") String title, @Param("status") int status, @Param("starttime") String starttime, @Param("endtime") String endtime, @Param("offset") int offset, @Param("limit") int limit);

    /*返回最大的id*/
    int queryMAX();
}
