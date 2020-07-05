package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.annotation.SysLog;
import cn.cwnu.common.utils.DatabaseBackupUtils;
import cn.cwnu.common.utils.IPUtils;
import cn.cwnu.common.utils.R;
import cn.cwnu.modules.sys.entity.BackupEntity;
import cn.cwnu.modules.sys.service.BackupService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;

/**
 * 数据库备份还原
 *
 * @author zhugl
 */
@RestController
@RequestMapping("/backup")
@Api("BackupController")
public class BackupController {
/*
    @Autowired
    private BackupService backupService;

    //获取配置 - MySQL数据库所在服务器地址IP
    @Value("${spring.datasource.url}")
    public String url;
    //获取配置 - 进入数据库所需要的用户名
    @Value("${spring.datasource.username}")
    public String username;
    //获取配置 - 进入数据库所需要的密码
    @Value("${spring.datasource.password}")
    public String password;
    //数据库导出文件保存路径
    private String savePath = "/backupDatabase";


    *//**
     * 数据库导出备份
     *
     * @return
     *//*
    @SysLog("数据库信息备份")
    @PostMapping(value = "/databaseExport")
    @RequiresPermissions("sys:backup:export")
    public R databaseExport() {
        //获取参数
        String[] params = DatabaseBackupUtils.getParams(url);
        //执行导出备份
        boolean flag = DatabaseBackupUtils.exportDatabaseTool(params[0], params[1], username, password, savePath, params[3], params[2]);
        //保存备份记录
        if (flag) {
            try {
                backupService.save(params[3], IPUtils.getLocalIP(), params[1], savePath);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } else {
            return R.error("数据库备份失败");
        }
        return R.ok();
    }

    *//**
     * 数据库信息还原
     *
     * @param location 备份位置
     * @param name     文件名
     * @return
     *//*
    @SysLog("数据库信息还原")
    @PostMapping("/reduction")
    @RequiresPermissions("sys:backup:reduction")
    public R reduction(String location, String name) {
        System.out.println(location + "      " + name);
        //获取参数
        String[] params = DatabaseBackupUtils.getParams(url);
        //执行还原
        boolean flag = DatabaseBackupUtils.importDatabase(params[0], params[1], username, password, location, params[3], name);
        if (flag) {
            return R.ok();
        }else {
            return R.error("还原失败");
        }
    }

    *//**
     * 查询所有备份信息
     *
     * @param searchText 查询条件
     * @return
     *//*
    @PostMapping("/recordInfoList")
    @RequiresPermissions("sys:backup:list")
    public String recordInfoList(String searchText) {
        //获取列表数据
        List<BackupEntity> entities = backupService.queryList(searchText);
        //封装json对象返回,bootstrap-table client分页方式需要data字符
        JSONObject result = new JSONObject();
        result.put("data", entities);
        result.put("total", entities.size());
        return result.toJSONString();
    }

    *//**
     * 数据库信息删除
     *
     * @param id
     * @return
     *//*
    @SysLog("数据库信息删除")
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:backup:delete")
    public R delete(@PathVariable Integer id) {
        backupService.delete(id);
        return R.ok();
    }*/
}
