package cn.cwnu.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典
 */
@Data
public class SysDictEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //分组
    private Long parentId;
    //分组名称
    private String parentName;
    //名称
    private String name;
    //分类： 0：分组 1：分组成员
    private Integer dictType;
    //备注
    private String remarks;
    //编号
    private String code;

}
