package cn.cwnu.modules.business.dao;

import cn.cwnu.modules.business.entity.BuSpTagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/*
 * 特殊投稿分类
 * */
@Mapper
@Component
public interface BuSpTagDao {
    /*查询所有*/
    List<BuSpTagEntity> queryList();

    /*查询数量*/
    int queryAll();

    /*可以新增*/
    BuSpTagEntity canAdd(String buSptname);

    /*增加*/
    boolean addSpTag(String buSptname);

    /*删除*/
    boolean deleteSpTag(int buSptid);

    /*修改*/
    boolean updateSpTag(@Param("buSptid") int buSptid, @Param("buSptname") String buSptname);

    /*查询列表*/
    List<BuSpTagEntity> queryLists(Map<String, Object> map);
}
