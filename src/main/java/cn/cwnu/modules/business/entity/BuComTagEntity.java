package cn.cwnu.modules.business.entity;

import lombok.Data;
import java.io.Serializable;

/*
* 评论投稿分类
* */
@Data
public class BuComTagEntity implements Serializable {
    //id
    private Integer buComtid;
    //分类名
    private String buComtname;

    public BuComTagEntity() {
    }

    public BuComTagEntity(Integer buComtid, String buComtname) {
        this.buComtid = buComtid;
        this.buComtname = buComtname;
    }
}
