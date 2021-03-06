package cn.cwnu.modules.business.service;

import cn.cwnu.modules.business.entity.BuComTagEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
 * 评论投稿分类
 * */
public interface BuComTagService {
    /*查询所有*/
    List<BuComTagEntity> queryList();

    /*查询数量*/
    int queryAll();

    /*增加*/
    boolean canAdd(String buComtname);

    /*增加*/
    boolean addComTag(String buComtname);

    /*删除*/
    boolean deleteComTag(int buComtid);

    /*修改*/
    boolean updateComTag(@Param("buComtid") int buComtid, @Param("buComtname") String buComtname);

    /*查询列表*/
    List<BuComTagEntity> queryLists(Map<String, Object> map);

    /*通过id找标签名字*/
    String queryById(int buComtid);
}
