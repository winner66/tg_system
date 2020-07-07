package cn.cwnu.modules.sys.service.impl;


import cn.cwnu.common.utils.R;
import cn.cwnu.modules.sys.dao.ClientUserTokenDao;
import cn.cwnu.modules.sys.entity.ClientUserTokenEntity;
import cn.cwnu.modules.sys.oauth2.TokenGenerator;
import cn.cwnu.modules.sys.service.ClientUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service("ClientUserTokenService")
public class ClientUserTokenServiceImpl implements ClientUserTokenService {

  @Autowired
  private ClientUserTokenDao clientUserTokenDao;
    //1小时后过期
    private final static int EXPIRE = 3600 * 1;

    @Override
    public ClientUserTokenEntity queryByUserId(Long userId) {
        return clientUserTokenDao.queryByUserId(userId);
    }

    @Override
    public ClientUserTokenEntity queryByToken(String token) {
        return clientUserTokenDao.queryByToken(token);
    }

    @Override
    public void save(ClientUserTokenEntity token){
        clientUserTokenDao.save(token);
    }

    @Override
    public void update(ClientUserTokenEntity token){
        clientUserTokenDao.update(token);
    }

    @Override
    public R createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        ClientUserTokenEntity tokenEntity = queryByUserId(userId);
        if(tokenEntity == null){
            tokenEntity = new ClientUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            save(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }
}
