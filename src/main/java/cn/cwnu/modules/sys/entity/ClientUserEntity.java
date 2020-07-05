package cn.cwnu.modules.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员信息实体
 *
 * @author zgl
 */
@Data
public class ClientUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    //用户名
    private String username;
    private String realname;
    //账号
    private String account;
    //手机号
    private String phone;
    //所在分组 id
    private Long groupId;
    private Long deptId;
    private int type;
    //单位名称
    private String dept;
    //人员等级
    private int level;
    //性别
    private String sex;

    //状态  0：禁用   1：正常
    private Integer status;
    //邮箱
    private String email;
    //开户银行
    private String bank;
    //银行账号
    private String bankNum;
    //详细地址
    private String address;
    //密码
    private String password;
    //盐
    private String salt;
    //创建时间
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    //更新时间
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
    //最近一次登录时间
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String loginTime;
    //备注
    private String remarks;
}
