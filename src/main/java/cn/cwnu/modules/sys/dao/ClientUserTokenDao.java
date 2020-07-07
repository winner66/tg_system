package cn.cwnu.modules.sys.dao;

import cn.cwnu.modules.sys.entity.ClientUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ClientUserTokenDao extends BaseDao<ClientUserTokenEntity>{
    ClientUserTokenEntity queryByUserId(Long userId);
    ClientUserTokenEntity queryByToken(String token);
}
