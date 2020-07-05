package cn.cwnu.modules.business.service;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
 * 评论投稿分类
 * */
public interface BuComTaskService {
    /*增加*/
    boolean addComTask(BuComTaskEntity buComTaskEntity);
    /*查询最大的id*/
    int queryMaxId();
    /*搜索查询*/
    List<BuComTaskEntity> searchList(@Param("tag") int tag, @Param("taskname") String taskname, @Param("taskstatus") int taskstatus, @Param("starttime") String starttime, @Param("endtime") String endtime, @Param("offset") int offset, @Param("limit") int limit);
    /*根据id查询*/
    BuComTaskEntity queryById(int id);
    /*删除*/
    boolean deleteComTask(int id);
    /*修改*/
    boolean updateComtask(BuComTaskEntity buComTaskEntity);
    /*分页*/
    List<Map<String,BuComTaskEntity>> pageList(@Param("tag") int tag,@Param("offset") int offset, @Param("limit") int limit);
    /*查询数量*/
    int queryAll(int tag);
    /*暂停任务*/
    boolean stopComtask(int id);
    /*为稿件查询所有*/
    List<Map<String,BuComTaskEntity>> queryAllList(@Param("taskname") String taskname,@Param("starttime") String starttime, @Param("endtime") String endtime);

}
