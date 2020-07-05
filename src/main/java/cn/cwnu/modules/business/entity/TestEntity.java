package cn.cwnu.modules.business.entity;

import lombok.Data;

import java.io.Serializable;

/*
* 评论投稿分类
* */
@Data
public class TestEntity implements Serializable {
   private int id;
   private String name;
   public TestEntity(){

   }
    public TestEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
