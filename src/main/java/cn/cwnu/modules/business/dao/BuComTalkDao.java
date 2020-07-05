package cn.cwnu.modules.business.dao;

import cn.cwnu.modules.business.entity.BuComTalkEntity;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
 * 评论投稿稿件
 * */
@Mapper
@Component
public interface BuComTalkDao {
    /*通过*/
    boolean pass(int id);

    /*拒绝*/
    boolean stop(int id,String reason);

    /*给奖励*/
    boolean reward(int id,int reward);

    /*查询数量*/
    int queryAll(int taskid);

    /*返回最大的id*/
    int queryMAX();

    /*根据id查询*/
    BuComTalkEntity queryById(int id);

    /*为稿件查询所有*/
    List<Map<String,BuComTalkEntity>> queryAllList(@Param("taskname") String taskname, @Param("starttime") String starttime, @Param("endtime") String endtime);

    /*分页*/
    List<Map<String,BuComTalkEntity>> pageList(@Param("taskid") int taskid, @Param("offset") int offset, @Param("limit") int limit);

    /*搜索查询*/
    List<BuComTalkEntity> searchList(@Param("taskid") int taskid, @Param("name") String name, @Param("title") String title, @Param("status") int status, @Param("starttime") String starttime, @Param("endtime") String endtime, @Param("offset") int offset, @Param("limit") int limit);
}
