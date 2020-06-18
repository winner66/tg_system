package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.annotation.SysLog;
import cn.cwnu.common.utils.Constant;
import cn.cwnu.common.utils.PageUtils;
import cn.cwnu.common.utils.Query;
import cn.cwnu.common.utils.R;
import cn.cwnu.common.validator.Assert;
import cn.cwnu.common.validator.ValidatorUtils;
import cn.cwnu.common.validator.group.AddGroup;
import cn.cwnu.common.validator.group.UpdateGroup;
import cn.cwnu.modules.sys.entity.SysUserEntity;
import cn.cwnu.modules.sys.service.SysUserRoleService;
import cn.cwnu.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 管理者账号
 *
 * @author zhugl
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {

        //查询列表数据
        Query query = new Query(params);
        //如果不是超级管理员，则只查询自己创建的用户列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            if (getIdentityId() == Constant.COMPANY_IDENTITY_ID || getIdentityId() == Constant.USER_IDENTITY_ID) {
                List<SysUserEntity> list = sysUserService.getList(getDeptId(), getIdentityId());
                PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());

                return R.ok().put("page", pageUtil);
            }
        }
        List<SysUserEntity> list = sysUserService.queryList(query);
        PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");
        //sha256加密
        password = new Sha256Hash(password, getUser().getSalt()).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();
        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }
        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Integer userId) {
        SysUserEntity user = sysUserService.queryObject(userId);
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        sysUserService.save(user);
        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        sysUserService.update(user);
        return R.ok();
    }

    /**
     * 禁用用户
     *
     * @param userIds
     * @return
     */
    @SysLog("禁用用户")
    @RequestMapping("/ban")
    @RequiresPermissions("sys:user:ban")
    public R ban(@RequestBody Integer[] userIds) {
        if (ArrayUtils.contains(userIds, 1)) {
            return R.error("系统管理员不能禁用");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能禁用");
        }
        sysUserService.banBatch(userIds);
        return R.ok();
    }

    /**
     * 激活用户
     *
     * @param userIds
     * @return
     */
    @SysLog("激活用户")
    @RequestMapping("/use")
    @RequiresPermissions("sys:user:use")
    public R use(@RequestBody Integer[] userIds) {
        if (ArrayUtils.contains(userIds, 1)) {
            return R.error("系统管理员不能激活");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能激活");
        }
        sysUserService.useBatch(userIds);
        return R.ok();
    }

    /**
     * 删除用户,不允许删除超级管理员和后台管理员
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Integer[] userIds) {
        if (ArrayUtils.contains(userIds, 1)) {
            return R.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return R.ok();
    }

}
