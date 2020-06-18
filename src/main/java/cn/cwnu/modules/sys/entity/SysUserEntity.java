package cn.cwnu.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 机构管理者
 */
@Data
public class SysUserEntity implements Serializable {

    private Integer id;
    //真实姓名
    private String realName;
    // 账号
    private String username;
    // 密码
    private String password;
    //盐
    private String salt;
    // 手机号
    private String mobile;
    //办公号码
    private String workPhone;
    //邮箱
    private String email;
    //详细地址
    private String address;
    //性别
    private String sex;
    //单位名称
    private String company;
    //机构名称
    private String deptName;
    //机构ID
    private Long deptId;
    //开户银行
    private String bank;
    //银行账号
    private String bankNum;
    //使用状态  0：禁用   1：正常
    private Integer status;
    //在线状态  0：离线   1：在线
    private Integer online;
    // 角色ID列表
    private List<Long> roleIdList;
    // 创建时间
    private String createTime;
    //用户身份
    private String identity;
    // 用户身份id  0:超级管理员  1：机构管理员 2：用户
    private Integer identityId;
    //备注
    private String remarks;
}
