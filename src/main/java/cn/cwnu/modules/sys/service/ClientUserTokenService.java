package cn.cwnu.modules.sys.service;

import cn.cwnu.common.utils.R;
import cn.cwnu.modules.sys.entity.ClientUserTokenEntity;


public interface ClientUserTokenService {
    ClientUserTokenEntity queryByUserId(Long userId);

    ClientUserTokenEntity queryByToken(String token);

    void save(ClientUserTokenEntity token);

    void update(ClientUserTokenEntity token);

    /**
     * 生成token
     * @param userId  用户ID
     */
    R createToken(long userId);
}
