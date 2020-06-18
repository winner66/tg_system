package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.ClientUserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 人员管理
 *
 * @author zgl
 */

public interface ClientUserService {

    /**
     * 查询所有信息
     *
     * @param searchText 查询条件
     * @return
     */
    List<ClientUserEntity> queryList(String searchText);

    /**
     * 批量保存
     *
     * @param myFile
     * @return
     */
    Integer importExcel(MultipartFile myFile);

    /**
     * 保存信息
     *
     * @param entity
     */
    void save(ClientUserEntity entity);

    /**
     * 删除数据
     *
     * @param id
     */

    void delete(Integer id);

    /**
     * 禁用用户
     *
     * @param id
     */
    void ban(Integer id);

    /**
     * 激活用户
     *
     * @param id
     */
    void use(Integer id);
}

