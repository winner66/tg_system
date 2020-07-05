package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.modules.sys.dao.SysDeptDao;
import cn.cwnu.modules.sys.dao.SysGroupDao;
import cn.cwnu.modules.sys.entity.SysDeptEntity;
import cn.cwnu.modules.sys.entity.SysGroupEntity;
import cn.cwnu.modules.sys.entity.organizationTree;
import cn.cwnu.modules.sys.service.ClientUserService;
import cn.cwnu.modules.sys.service.SysGroupService;
import cn.cwnu.modules.sys.service.organTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service("organTree")
public class organTreeService implements organTree {
    @Autowired
    private  SysDeptDao deptDao;
    @Autowired
    private SysGroupDao sysGroupDao;
    @Override
    public  List<organizationTree> Json_GetDepartmentTree(Long pid)
    {
//        System.out.println(pid);
//        String ptitle
//        SysDeptEntity SysDept1=deptDao.queryObject(pid);
//        查找子机构
        List<SysDeptEntity> SysDept =deptDao.queryObjectByParentId(pid);

        List<organizationTree> Data =new ArrayList<organizationTree>();
        for(SysDeptEntity item:SysDept){
            organizationTree tree = new organizationTree();
            tree.setTitle(item.getName());
            tree.setPid(item.getParentId());
            tree.setId(item.getDeptId());
            tree.setDisabled(false);
            tree.setType(1);
            Data.add(tree);
        }
//        System.out.println(Data.size());
        organizationTree model = new organizationTree();
        List<organizationTree> childs=creatTheTree(pid, Data);

        //        查找该机构的组  --  组没有 下属组
        List<SysGroupEntity> SysGroup =sysGroupDao.queryObjectByDeptId(pid);
//        System.out.println(SysGroup.size() );
//        System.out.println(SysGroup);
        for(SysGroupEntity item:SysGroup){
            organizationTree tree = new organizationTree();
//
            tree.setTitle(item.getGroupName());
            tree.setPid(item.getPreGroupId());
            tree.setId(item.getGroupId());
            tree.setDisabled(false);
//            标记 组
            tree.setType(0);
            childs.add(tree);
        }
        return childs;
    }
    public  List<organizationTree> creatTheTree(Long pid, List<organizationTree> Data)
    {
//        List<organizationTree> child = Data.stream().filter((organizationTree s) ->
//                pid==s.getPid()).collect(Collectors.toList());
////            pid.equals(s.getId())
//孙节点
        List<organizationTree> Children = new ArrayList<organizationTree>();

        for(organizationTree item :  Data)
        {
            List<SysDeptEntity> SysDept =deptDao.queryObjectByParentId(item.getId());

            List<organizationTree> Data2 =new ArrayList<organizationTree>();
            for(SysDeptEntity item2:SysDept){
                organizationTree tree = new organizationTree();
                tree.setTitle(item2.getName());
                tree.setPid(item2.getParentId());
                tree.setId(item2.getDeptId());
//                禁用
                tree.setDisabled(false);
//                机构
                tree.setType(1);
                Data2.add(tree);
            }
            List<organizationTree> child2=creatTheTree(item.getId(), Data2);
            item.setChildren(child2);
            Children.add(item);
            //递归循环
        }
        return Children;
    }
}
