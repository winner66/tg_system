package cn.cwnu.modules.sys.service;

import cn.cwnu.modules.sys.entity.ClientUserEntity;
import org.apache.ibatis.annotations.Param;
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
    List<ClientUserEntity> queryByGroup(Long id);

    List<ClientUserEntity> queryByDept(Long id);
    List<ClientUserEntity> queryAllByDept(Long id);
    //通过账号查询
    ClientUserEntity queryByAccount(String account);

    ClientUserEntity queryByid(String id);
    ClientUserEntity update(ClientUserEntity entity);
    int updatePassword(Integer userId, String password, String newPassword);
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

    int delete(Integer id);

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

