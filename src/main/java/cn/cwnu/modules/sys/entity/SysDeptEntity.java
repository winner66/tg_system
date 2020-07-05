package cn.cwnu.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 机构管理
 * @author zgl
 */

public class SysDeptEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //机构ID
    private Long deptId;
    //上级机构ID，一级机构为0
    private Long parentId;
    //机构名称
    private String name;
    //上级机构名称
    private String parentName;
    //机构编号
    private Integer departNum;
    /**
     * ztree属性
     */
    private Boolean open;

    private List<?> list;

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getDepartNum() {
        return departNum;
    }

    public void setDepartNum(Integer departNum) {
        this.departNum = departNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public SysDeptEntity() {
    }
}
