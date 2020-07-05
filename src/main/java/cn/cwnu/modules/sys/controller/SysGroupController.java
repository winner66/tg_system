package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.annotation.SysLog;
import cn.cwnu.modules.sys.entity.ClientUserEntity;
import cn.cwnu.modules.sys.entity.SysDeptEntity;
import cn.cwnu.modules.sys.entity.SysGroupEntity;
import cn.cwnu.modules.sys.service.SysGroupService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/group")


public class SysGroupController {
    @Autowired
     private  SysGroupService sysGroupService;

    @PostMapping("/groupListByDeptId/{id}")
    public String groupListByDeptId(@PathVariable("id") Long did) {
        //获取列表数据
        List<SysGroupEntity> groupList =sysGroupService.queryObjectByDeptId(did);
        //封装json对象返回,bootstrap-table client分页方式需要data字符
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("data", groupList);
        result.put("count",groupList.size());
        return result.toJSONString();
    }
//    新增组
    @ApiOperation("saveGroup")
    @PostMapping("/saveGroup")
    public void saveGroup(@RequestBody SysGroupEntity item){
        sysGroupService.save(item);
    }

//    修改组名

//    @PostMapping("/updataGroup")
@SysLog("组修改")
    @RequestMapping("/updataGroup")
    public void updataGroup(@RequestBody SysGroupEntity item){
//        System.out.println("211update"+ item.getGroupName()+"111");
        sysGroupService.updateName(item);
    }
//    删除组

    @RequestMapping("/deleteGroup/{id}")
    public  void deleteGroup(@PathVariable("id") Long Gid){
//        System.out.println("delete "+ Gid+"  111");
        sysGroupService.delete(Gid);
    }
}
