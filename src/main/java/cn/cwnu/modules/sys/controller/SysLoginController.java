package cn.cwnu.modules.sys.controller;

import cn.cwnu.common.utils.R;
import cn.cwnu.common.utils.ShiroUtils;
import cn.cwnu.modules.sys.entity.SysUserEntity;
import cn.cwnu.modules.sys.service.SysUserService;
import cn.cwnu.modules.sys.service.SysUserTokenService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 * @author zhugl
 */
@RestController
public class SysLoginController extends AbstractController {

    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 生成验证码
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public Map<String, Object> login(String username, String password) {
//        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if (!captcha.equalsIgnoreCase(kaptcha)) {
//            return R.error("验证码不正确");
//        }
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(username.trim());
        //账号不存在、密码错误
        if (null == user || !user.getPassword().equals(new Sha256Hash(password.trim(), user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }
        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }
        //客户端对象转为json
        String str = JSONSerializer.toJSON(user).toString();
        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getId());

        return R.ok().put("userToken", r).put("userInfo", str);
    }

    /**
     * 回话结束时更新用户在线状态
     *
     * @return
     */
    @GetMapping("/lineOut")
    public R lineOut() {
        // 获取数据
        //String value = (String) redisTemplate.opsForValue().get("value");
        //System.out.println("获取缓存中key为 " + "id" + " 的值为： " + value);
        //更新用户在线状态
        sysUserService.updateOnline(getUserId(), 0);
        return R.ok();
    }

    @GetMapping("/lineOn")
    public R lineOn() {
        sysUserService.updateOnline(getUserId(), 1);
        return R.ok();
    }
}
