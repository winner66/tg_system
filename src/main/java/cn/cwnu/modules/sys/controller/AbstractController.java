package cn.cwnu.modules.sys.controller;

import cn.cwnu.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getIdentityId() {
        return getUser().getIdentityId();
    }

    protected Integer getUserId() {
        return getUser().getId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
