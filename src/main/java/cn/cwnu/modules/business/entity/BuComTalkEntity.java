package cn.cwnu.modules.business.entity;

import lombok.Data;

import java.io.Serializable;

/*
* 评论投稿稿件
* */
@Data
public class BuComTalkEntity implements Serializable {
    private Integer id;
    //任务id
    private Integer taskid;
    //标题
    private String title;
    //提交人
    private String name;
    //分组
    private Integer fenzu;
    //提交时间
    private String createtime;
    //链接
    private String url;
    //文件地址
    private String filename;
    //状态 0 审核中1已通过 2已拒绝
    private int status;
    //马甲
    private String majia;
    //奖励
    private Integer reward;
    //拒接理由
    private String reason;

    public BuComTalkEntity() {
    }

    public BuComTalkEntity(Integer id,Integer taskid, String title, String name, Integer fenzu, String createtime, String url, String filename, int status, String majia, Integer reward, String reason) {
        this.id = id;
        this.taskid=taskid;
        this.title = title;
        this.name = name;
        this.fenzu = fenzu;
        this.createtime = createtime;
        this.url = url;
        this.filename = filename;
        this.status = status;
        this.majia = majia;
        this.reward = reward;
        this.reason = reason;
    }
}
