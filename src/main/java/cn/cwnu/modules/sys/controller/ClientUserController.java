package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.annotation.SysLog;
import cn.cwnu.common.utils.Constant;
import cn.cwnu.common.utils.DateUtils;
import cn.cwnu.common.utils.R;

import cn.cwnu.modules.sys.entity.ClientUserEntity;
import cn.cwnu.modules.sys.service.ClientUserService;
import cn.cwnu.modules.sys.service.ClientUserTokenService;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 人员管理
 *
 * @author zgl
 */
@RestController
@RequestMapping("/sys/clientUser")
public class ClientUserController extends AbstractController {
    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private ClientUserTokenService clientUserTokenService;

    @PostMapping("/login")
    public R login(@RequestParam("name")String name,@RequestParam("passwd")String pswd){
     ClientUserEntity clientUserEntity;
        try {
            clientUserEntity = clientUserService.queryByAccount(name);
            String time =DateUtils.formatDate(new Date());
            clientUserEntity.setLoginTime(time);
            clientUserService.update(clientUserEntity);
        } catch (Exception e) {
            return R.error("数据操作失败");
        }
        if (null ==  clientUserEntity) {
            return R.error("用户不存在");
        }
        if (! clientUserEntity.getPassword().equals(new Sha256Hash(pswd,  clientUserEntity.getSalt()).toHex())) {
            return R.error("密码不正确");
        }
        //生成token，并保存到数据库
        R r = clientUserTokenService.createToken( clientUserEntity.getId());
        //客户端对象转为json
        String str = JSONSerializer.toJSON(clientUserEntity).toString();
        return R.ok().put("userInfo", str).put("userToken", r);
    }
//    验证账号是否重复
    @PostMapping("/accountVail")
    public R accountVail(String account){
        ClientUserEntity clientUserEntity;
        clientUserEntity = clientUserService.queryByAccount(account);
        if(clientUserEntity!=null){
            R.error("登录账号重新，请重新输入");
        }
        return R.ok();

    }

//    评论投稿
    @PostMapping("/comtalk/{id}")
    public R comtalk( @PathVariable("id") Long id){

        return R.ok();
    }
//    特约投稿
    @PostMapping("/sp/{id}")
    public R getspByid(@PathVariable("id") Long id) {

        return R.ok();
    }


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
        result.put("code", 0);
        result.put("msg", "");
        result.put("data", clientUserList);
        result.put("count", clientUserList.size());
        return result.toJSONString();
    }

    @PostMapping("/listByDept/{id}")
    @RequiresPermissions("sys:clientUser:list")
    public String queryByDept(@PathVariable("id") Long id) {
        //获取列表数据
        List<ClientUserEntity> clientUserList = clientUserService.queryAllByDept(id);
        //封装json对象返回,bootstrap-table client分页方式需要data字符
        System.out.println(id+clientUserList.size());
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        result.put("data", clientUserList);
        result.put("count", clientUserList.size());
        return result.toJSONString();
    }
    @PostMapping("/listByGroup/{id}")
    @RequiresPermissions("sys:clientUser:list")
    public String queryByGroup(@PathVariable("id") Long id) {
        //获取列表数据
        List<ClientUserEntity> clientUserList = clientUserService.queryByGroup(id);
        //封装json对象返回,bootstrap-table client分页方式需要data字符

        System.out.println(id+clientUserList.size());
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        result.put("data", clientUserList);
        result.put("count", clientUserList.size());
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
    @PostMapping("/save")
//    @RequiresPermissions("sys:clientUser:add")
    public R save(@RequestBody ClientUserEntity entity) {
        String time =DateUtils.formatDate(new Date());
        entity.setCreateTime(time);
        entity.setUpdateTime(time);
        entity.setLoginTime(time);
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(Constant.SALT_LENGTH);
        entity.setPassword(new Sha256Hash(entity.getPassword(), salt).toHex());
        entity.setSalt(salt);
        clientUserService.save(entity);
        return R.ok();
    }

    @PostMapping("/update")
//    @RequiresPermissions("sys:clientUser:update")
    public R update(@RequestBody ClientUserEntity entity){
        String time =DateUtils.formatDate(new Date());
        entity.setUpdateTime(time);
        entity.setLoginTime(time);

        clientUserService.update(entity);
        return R.ok();
    }
    @PostMapping("/password")
    public R rePSWD(String password, String newPassword){
        //sha256加密
        password = new Sha256Hash(password, getUser().getSalt()).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();
        //更新密码
        int count = clientUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }
        return R.ok();

    }
    @PostMapping("/getInfoByid/{id}")
    public R getInfoByid(@PathVariable("id") String id){
        ClientUserEntity clientUserEntity = clientUserService.queryByid(id);
        String str = JSONSerializer.toJSON(clientUserEntity).toString();
        return R.ok().put("Info",str);
    }
    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("用户删除")
    @PostMapping("/delete/{id}")
//    @RequiresPermissions("sys:clientUser:delete")
    public R delete(@PathVariable("id") Integer id) {
        int count=clientUserService.delete(id);
        return R.ok().put("count",count);
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
