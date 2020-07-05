package cn.cwnu.modules.business.entity;

import lombok.Data;
import java.io.Serializable;

/*
 * 特殊投稿分类
 * */
@Data
public class BuSpTagEntity implements Serializable {
    //id
    private Integer buSptid;
    //分类名
    private String buSptname;

    public BuSpTagEntity() {
    }

    public BuSpTagEntity(Integer buSptid, String buSptname) {
        this.buSptid = buSptid;
        this.buSptname = buSptname;
    }
}
