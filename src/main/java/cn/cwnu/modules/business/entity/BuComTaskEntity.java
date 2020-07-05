package cn.cwnu.modules.business.entity;

import lombok.Data;
import java.io.Serializable;
/*
* 评论投稿任务
* */
@Data
public class BuComTaskEntity implements Serializable {
    private Integer id;
    //编号
    private Integer bid;
    //任务分类
    private Integer tag;
    //接收人数
    private Integer countj;
    //发送人数
    private Integer countf;
    //发布人
    private String fabup;
    //完成量
    private Integer counte;
    private String taskname;
    private String createtime;
    private String starttime;
    private String endtime;
    //任务要求
    private String taskreq;
    //单人上限
    private int pertoplimit;
    //任务下限
    private int taskbelimit;
    //结构任务上限
    private int orgtoplimit;
    //机构任务下限
    private int orgbelimit;
    //奖励设置
    private int reward;
    //附件地址
    private String filename;
    //高级选项
    //贴文标题
    private Integer titlex;
    private Integer titleb;
    //贴文链接
    private Integer hrefx;
    private Integer hrefb;
    //贴文内容
    private Integer contentx;
    private Integer contentb;
    //贴文附件
    private Integer filex;
    private Integer fileb;
    //马甲
    private Integer majiax;
    private Integer majiab;
    //待做
    private Integer userIds;
    //任务状态
    private Integer taskstatus;

    public BuComTaskEntity() {
    }

    public BuComTaskEntity(Integer id, Integer bid,String fabup, Integer tag, Integer countj, Integer countf, Integer counte, String taskname, String createtime, String starttime, String endtime, String taskreq, int pertoplimit, int taskbelimit, int reward, String filename, Integer titlex, Integer titleb, Integer hrefx, Integer hrefb, Integer contentx, Integer contentb, Integer filex, Integer fileb, Integer majiax, Integer majiab,Integer taskstatus) {
        this.id = id;
        this.bid = bid;
        this.fabup=fabup;
        this.tag = tag;
        this.countj = countj;
        this.countf = countf;
        this.counte = counte;
        this.taskname = taskname;
        this.createtime = createtime;
        this.starttime = starttime;
        this.endtime = endtime;
        this.taskreq = taskreq;
        this.pertoplimit = pertoplimit;
        this.taskbelimit = taskbelimit;
        this.reward = reward;
        this.filename = filename;
        this.titlex = titlex;
        this.titleb = titleb;
        this.hrefx = hrefx;
        this.hrefb = hrefb;
        this.contentx = contentx;
        this.contentb = contentb;
        this.filex = filex;
        this.fileb = fileb;
        this.majiax = majiax;
        this.majiab = majiab;
        this.taskstatus=taskstatus;
    }
}
