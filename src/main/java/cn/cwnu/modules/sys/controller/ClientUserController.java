package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.annotation.SysLog;
import cn.cwnu.common.utils.R;

import cn.cwnu.modules.sys.entity.ClientUserEntity;
import cn.cwnu.modules.sys.service.ClientUserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 人员管理
 *
 * @author zgl
 */
@RestController
@RequestMapping("/sys/clientUser")
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;

    /**
     * 查询所有客户端用户信息
     *
     * @param searchText 查询条件
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:clientUser:list")
    public String defaultList(String searchText) {
        //获取列表数据
        List<ClientUserEntity> clientUserList = clientUserService.queryList(searchText);
        //封装json对象返回,bootstrap-table client分页方式需要data字符
        JSONObject result = new JSONObject();
        result.put("data", clientUserList);
        result.put("total", clientUserList.size());
        return result.toJSONString();
    }

    /**
     * xxxxxxxxxxxxxxxxxxx
     * @param searchText
     * @return
     */
    @PostMapping("/info")
    @RequiresPermissions("sys:clientUser:info")
    public String info(String searchText) {
//
//        List<ClientUserEntity> clientUserList = clientUserService.queryList(searchText);
//        //封装json对象返回,bootstrap-table client分页方式需要data字符
//        JSONObject result = new JSONObject();
//        result.put("data", clientUserList);
//        result.put("total", clientUserList.size());
        return "";
    }

    @PostMapping("/importExcel")
    @RequiresPermissions("sys:clientUser:add")
    public R batchSave(@RequestParam("myfile") MultipartFile myFile) {
        //获取保存数据条数
        Integer nums = clientUserService.importExcel(myFile);
        return R.ok().put("nums", nums);
    }

    /**
     * 保存单条信息
     *
     * @param entity
     * @return
     */
//    @PostMapping("/save")
//    @RequiresPermissions("sys:clientUser:add")
//    public R save(@RequestBody ClientUserEntity entity) {
//        clientUserService.save(entity);
//        return R.ok();
//    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("用户删除")
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:clientUser:delete")
    public R delete(@PathVariable Integer id) {
        clientUserService.delete(id);
        return R.ok();
    }

    /**
     * 禁用
     *
     * @param id
     * @return
     */
    @SysLog("用户禁用")
    @PostMapping("/ban/{id}")
    @RequiresPermissions("sys:clientUser:ban")
    public R ban(@PathVariable Integer id) {
        clientUserService.ban(id);
        return R.ok();
    }

    /**
     * 激活
     *
     * @param id
     * @return
     */
    @SysLog("用户激活")
    @PostMapping("/use/{id}")
    @RequiresPermissions("sys:clientUser:use")
    public R use(@PathVariable Integer id) {
        clientUserService.use(id);
        return R.ok();
    }
}
