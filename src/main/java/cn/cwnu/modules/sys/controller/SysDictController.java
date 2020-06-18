package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.utils.R;
import cn.cwnu.common.validator.ValidatorUtils;
import cn.cwnu.common.validator.group.UpdateGroup;
import cn.cwnu.modules.sys.entity.SysDictEntity;
import cn.cwnu.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 字典
 */
@RestController
@RequestMapping("/sys/sysdict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:sysdict:list")
    public List<SysDictEntity> list() {
        List<SysDictEntity> sysDictList = sysDictService.queryList(new HashMap<>());
        return sysDictList;
    }

    @RequestMapping("/select")
    @RequiresPermissions("sys:sysdict:select")
    public R select() {
        List<SysDictEntity> sysDictList = sysDictService.queryGroupList();
        return R.ok().put("sysDictList", sysDictList);
    }

    /**
     * 获取下拉列表数据
     */
    @RequestMapping("/selectbycode/{code}")
    public List<String> selectByCode(@PathVariable("code") String code) {
        return sysDictService.selectDictsByCode(code);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:sysdict:info")
    public R info(@PathVariable("id") String id) {
        SysDictEntity sysDict = sysDictService.queryObject(id);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sysdict:save")
    public R save(@RequestBody SysDictEntity sysDict) {
        ValidatorUtils.validateEntity(sysDict, UpdateGroup.class);
        sysDictService.save(sysDict);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysdict:update")
    public R update(@RequestBody SysDictEntity sysDict) {
        ValidatorUtils.validateEntity(sysDict, UpdateGroup.class);
        sysDictService.update(sysDict);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sysdict:delete")
    public R delete(String id) {
        sysDictService.delete(id);
        return R.ok();
    }

}
