package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.organizationTree;

import java.util.List;

public interface organTree {

    List<organizationTree> Json_GetDepartmentTree(Long pid);
}
