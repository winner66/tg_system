package cn.cwnu.modules.sys;

import cn.cwnu.modules.sys.dao.SysDeptDao;
import cn.cwnu.modules.sys.entity.organizationTree;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("organization")
public class organization {

        private  static  SysDeptDao deptDao;

        public static List<organizationTree> Json_GetDepartmentTree(Long pid)
        {
            System.out.println(pid);
            List<organizationTree> Data =deptDao.queryObjectByParentId(pid);
            organizationTree model = new organizationTree();
            List<organizationTree> childs=creatTheTree(pid, Data);
            model.setChildren(childs); //根节点的parentBh值为"0"
            return childs;
        }

        public static List<organizationTree> creatTheTree(Long pid, List<organizationTree> Data)
        {
            List<organizationTree> child = Data.stream().filter((organizationTree s) ->
                    pid==s.getId()).collect(Collectors.toList());
//            pid.equals(s.getId())
            List<organizationTree> Children = new ArrayList<organizationTree>();

            for(organizationTree item :  child)
            {
                List<organizationTree> child2=creatTheTree(item.getId(), Data);
                item.setChildren(child2);
                //递归循环

            }
            return Children;
        }
    }

