package cn.cwnu.modules.sys.service.impl;

import cn.cwnu.common.utils.ReadExcelUtils;
import cn.cwnu.modules.sys.dao.ClientUserDao;
import cn.cwnu.modules.sys.dao.SysGroupDao;
import cn.cwnu.modules.sys.entity.ClientUserEntity;
import cn.cwnu.modules.sys.entity.SysGroupEntity;
import cn.cwnu.modules.sys.service.ClientUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员管理
 *
 * @author zgl
 */
@Service("clientUserService")
public class ClientUserServiceImpl implements ClientUserService {
    @Autowired
    private ClientUserDao clientUserDao;
    private SysGroupDao sysGroupDao;

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

//        entity.setLoginTime();
        clientUserDao.save(entity);
    }
    @Override
    public ClientUserEntity queryByAccount(String account){
        return  clientUserDao.queryByAccount(account);
    }

    @Override
    public List<ClientUserEntity> queryByGroup(Long id) {
        List<ClientUserEntity> resust;
//        当前组的人员
        resust= clientUserDao.queryByGroup(id);
//        下属组的人员
        System.out.println(id);

        List<SysGroupEntity> groups = sysGroupDao.queryGroupByPGid(id);
        System.out.println(groups.size());
        List<ClientUserEntity> group2List=queryGroup(groups);
        resust.addAll(group2List);
        return resust;
    }
    private List<ClientUserEntity> queryGroup(List<SysGroupEntity> groups){
        List<ClientUserEntity> resust= new ArrayList<ClientUserEntity>();
        for (SysGroupEntity id:groups){
            List<ClientUserEntity> list;
            list= clientUserDao.queryByGroup(id.getGroupId());
            resust.addAll(list);
//            下属组de下属组的人员
            List<SysGroupEntity> groups2= sysGroupDao.queryGroupByPGid(id.getGroupId());
            List<ClientUserEntity> group2List=queryGroup(groups2);
            resust.addAll(group2List);
        }

        return resust;
    }

//通过查询 机构人员再查询 组成员
    @Override
    public List<ClientUserEntity> queryByDept(Long id) {
        List<ClientUserEntity> resust;
//       当前机构
        resust= clientUserDao.queryByDept(id);

//        机构下的组
        List<SysGroupEntity> groups= sysGroupDao.queryGroupByDid(id);

        List<ClientUserEntity> group2List=queryGroup(groups);
        resust.addAll(group2List);
        return resust;
    }
//    queryAllByDept
//直接查询 机构下的人员


    @Override
    public ClientUserEntity queryByid(String id) {
        return clientUserDao.queryByid(id);
    }

    @Override
    public ClientUserEntity update(ClientUserEntity entity) {

        return clientUserDao.update(entity);
    }

    @Override
    public int updatePassword(Integer userId, String password, String newPassword) {
//        默认错误
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return clientUserDao.updatePassword(map);
    }

    @Override
    public List<ClientUserEntity> queryAllByDept(Long id){
        return clientUserDao.queryAllByDept(id);
    }
    /**
     * 删除数据
     *
     * @param id
     */
    @Override
    public int delete(Integer id) {
        return clientUserDao.delete(id);
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

