package cn.cwnu.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

//@Data
public class SysGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private  Long deptId;
    private  Long groupId;
    private String groupName;
    //    没有用
    private  Long preGroupId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getPreGroupId() {
        return preGroupId;
    }

    public void setPreGroupId(Long preGroupId) {
        this.preGroupId = preGroupId;
    }
}
