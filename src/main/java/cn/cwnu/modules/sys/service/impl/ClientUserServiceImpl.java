package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.common.utils.ReadExcelUtils;
import cn.cwnu.modules.sys.dao.ClientUserDao;
import cn.cwnu.modules.sys.entity.ClientUserEntity;
import cn.cwnu.modules.sys.service.ClientUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 人员管理
 *
 * @author zgl
 */
@Service("clientUserService")
public class ClientUserServiceImpl implements ClientUserService {
    @Autowired
    private ClientUserDao clientUserDao;

    /**
     * 查询所有客户端用户信息
     *
     * @param searchText 查询条件
     * @return
     */
    @Override
    public List<ClientUserEntity> queryList(String searchText) {
        if (StringUtils.isBlank(searchText)) {
            searchText = "";
        }
        return clientUserDao.queryInfoList(searchText);
    }

    /**
     * 批量保存
     *
     * @param myFile
     * @return
     */
    @Override
    public Integer importExcel(MultipartFile myFile) {
        List<ClientUserEntity> manageEntities = new ReadExcelUtils().getUserInfoLists(myFile);
        long startTime = System.currentTimeMillis();
        // 批量插入
        clientUserDao.batchInsert(manageEntities);
        long endTime = System.currentTimeMillis();
        System.out.println("【消耗时间为】{} " + (endTime - startTime));
        return manageEntities.size();
    }

    /**
     * 保存信息
     *
     * @param entity
     */
    @Override
    public void save(ClientUserEntity entity) {
       // clientUserDao.save(entity);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //clientUserDao.delete(id);
    }

    /**
     * 禁用用户
     *
     * @param id
     */
    @Override
    public void ban(Integer id) {
        clientUserDao.ban(id);
    }

    /**
     * 激活用户
     *
     * @param id
     */
    @Override
    public void use(Integer id) {
        clientUserDao.use(id);
    }
}

