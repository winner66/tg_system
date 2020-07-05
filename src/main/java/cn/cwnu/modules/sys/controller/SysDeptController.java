package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.annotation.SysLog;
import cn.cwnu.common.utils.Constant;
import cn.cwnu.common.utils.R;
import cn.cwnu.modules.sys.entity.SysDeptEntity;
import cn.cwnu.modules.sys.entity.organizationTree;
//import cn.cwnu.modules.sys.organization;
import cn.cwnu.modules.sys.service.SysDeptService;
import cn.cwnu.modules.sys.service.organTree;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 机构管理
 * @author zgl
 */
@RestController
@RequestMapping("/sys/dept")

public class SysDeptController extends AbstractController {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private organTree organTree;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("sys:dept:list")
    public List<SysDeptEntity> list() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<>());
        return deptList;
    }
    @ApiOperation("/deptTree")
    @RequestMapping("/deptTree/{pid}")
    public String deptTree(@PathVariable("pid") Long id){
        JSONObject result = new JSONObject();
//        System.out.println("1111---");
//        organizationTree tree=new organizationTree();
//        tree.setId(1L);
//        tree.setPid(0L);
//        tree.setTitle("123");
//        List<organizationTree> chils= new ArrayList<>();
//        organizationTree e1=new organizationTree();
//        e1.setId(2L);
//        e1.setPid(1L);
//        e1.setTitle("456");
//        chils.add(e1);
//        List<organizationTree> res =new ArrayList<organizationTree>();
//        res.add(tree);
//        tree.setChildren(chils);
//        result.put("data", res);
////        测试数据
        List<organizationTree> deptTree1;

        deptTree1=organTree.Json_GetDepartmentTree(id);
//        System.out.println("1111");
         result.put("data", deptTree1);

        System.out.println(result.toJSONString());
        return result.toJSONString();
    }



    /**
     * 选择机构(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public R select() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<>());

        //添加一级机构
        if (getUserId() == Constant.SUPER_ADMIN) {
            SysDeptEntity root = new SysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级机构");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 获取dropdownlist
     */
    @RequestMapping("/dropdown")
    public List<SysDeptEntity> dropDownList() {
        return sysDeptService.queryDropDownList(new HashMap<>());
    }

    /**
     * 上级机构Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public R info() {
        long deptId = 0;
        if (getUserId() != Constant.SUPER_ADMIN) {
            SysDeptEntity dept = sysDeptService.queryObject(getDeptId());
            deptId = dept.getParentId();
        }

        return R.ok().put("deptId", deptId);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public R info(@PathVariable("deptId") Long deptId) {
        SysDeptEntity dept = sysDeptService.queryObject(deptId);

        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @SysLog("机构添加")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody SysDeptEntity dept) {
        sysDeptService.save(dept);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("机构修改")
    @RequestMapping("/update")
//    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody SysDeptEntity dept) {
//        System.out.println("211update" + dept.getName()+"111");
        sysDeptService.update(dept);
        return R.ok();
    }
    /**
     * 删除
     */
    @SysLog("机构删除")
    @RequestMapping("/delete")
//    @RequiresPermissions("sys:dept:delete")
    public R delete(@RequestParam(value ="id") long deptId) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子机构");
        }
        sysDeptService.delete(deptId);
        return R.ok();
    }
    /**
     * 根据机构id获取机构编号
     *
     * @param deptId
     * @return
     */
    @GetMapping("/getDepartNum")
    public R getDepartNum(Integer deptId) {
        String departNum = sysDeptService.getDepartNum(deptId);
        return R.ok().put("departNum", departNum);
    }

}
