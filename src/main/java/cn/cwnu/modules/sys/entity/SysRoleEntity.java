package cn.cwnu.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 */
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 所属一级角色，若不选则为0，该值为一级角色id   0：表示一级角色
	 */
	private Integer subsidiary;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 部门ID
	 */
	private Long deptId;

	/**
	 * 部门名称
	 */
	private String deptName;
	
	private List<Long> menuIdList;

	private List<Long> deptIdList;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 设置：
	 * @param roleId 
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取：
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}

	public Integer getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(Integer subsidiary) {
		this.subsidiary = subsidiary;
	}

	/**
	 * 设置：角色名称
	 * @param roleName 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取：角色名称
	 * @return String
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/**
	 * 设置：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Long> getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(List<Long> deptIdList) {
		this.deptIdList = deptIdList;
	}

	@Override
	public String toString() {
		return "SysRoleEntity{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", subsidiary=" + subsidiary +
				", remark='" + remark + '\'' +
				", deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", menuIdList=" + menuIdList +
				", deptIdList=" + deptIdList +
				", createTime=" + createTime +
				'}';
	}
}
