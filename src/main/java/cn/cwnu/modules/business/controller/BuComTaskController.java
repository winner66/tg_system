package cn.cwnu.modules.business.controller;

import cn.cwnu.modules.business.entity.BuComTagEntity;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import cn.cwnu.modules.business.entity.TestEntity;
import cn.cwnu.modules.business.service.BuComTagService;
import cn.cwnu.modules.business.service.BuComTaskService;
import cn.cwnu.modules.sys.controller.AbstractController;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/business/comtask")
public class BuComTaskController extends AbstractController {
    @Autowired
    BuComTaskService buComTaskService;

    //查看评论列表-任务列表的分类
    @RequestMapping("/tab")
    @RequiresPermissions("business:comtask:tab")
    public String tab(@RequestParam String taskname,@RequestParam String starttime,@RequestParam String endtime) {
        List<Map<String,BuComTaskEntity>> list=buComTaskService.queryAllList(taskname,starttime,endtime);
        return JSON.toJSONString(list);
    }

    //评论列表-任务列表的任务修改
    @RequestMapping("/update")
    @RequiresPermissions("business:comtask:update")
    public String update(@RequestBody Map<String,Object> map) {
        BuComTaskEntity buComTaskEntity = JSON.parseObject(JSON.toJSONString(map), BuComTaskEntity.class);
        //System.out.println(buComTaskEntity);
//        //设置分类
//        buComTaskEntity.setTag(Integer.parseInt(map.get("tags").toString()));
//        //设置三个上下限
//        buComTaskEntity.setCounte(0);buComTaskEntity.setCountf(10);buComTaskEntity.setCountj(0);
//        //状态，任务名，发布人
//        buComTaskEntity.setTaskstatus(0);buComTaskEntity.setTaskname(map.get("taskname").toString());
//        buComTaskEntity.setFabup(map.get("fabup").toString());
//        //日期相关
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dNow = new Date( );
//        //计算序号
//        buComTaskEntity.setBid(Integer.parseInt(map.get("bid").toString()));
//        //三个日期设定
//        String time=ft.format(dNow);
//        buComTaskEntity.setCreatetime(time);
//        buComTaskEntity.setEndtime(map.get("starttime").toString());buComTaskEntity.setStarttime(map.get("endtime").toString());
//        //设置任务要求，上下限，奖励和附件
//        buComTaskEntity.setTaskreq(map.get("taskreq").toString());
//        buComTaskEntity.setTaskbelimit(Integer.parseInt(map.get("pertoplimit").toString()));
//        buComTaskEntity.setPertoplimit(Integer.parseInt(map.get("pertoplimit").toString()));
//        buComTaskEntity.setReward(Integer.parseInt(map.get("reward").toString()));
//        buComTaskEntity.setFilename(map.get("filename").toString());
//        //高级选项
//        buComTaskEntity.setTitlex(Integer.parseInt(map.get("titlex").toString()));
//        buComTaskEntity.setTitleb(Integer.parseInt(map.get("titleb").toString()));
//        buComTaskEntity.setHrefx(Integer.parseInt(map.get("hrefx").toString()));
//        buComTaskEntity.setHrefb(Integer.parseInt(map.get("hrefb").toString()));
//        buComTaskEntity.setContentx(Integer.parseInt(map.get("contentx").toString()));
//        buComTaskEntity.setContentb(Integer.parseInt(map.get("contentb").toString()));
//        buComTaskEntity.setFilex(Integer.parseInt(map.get("filex").toString()));
//        buComTaskEntity.setFileb(Integer.parseInt(map.get("fileb").toString()));
//        buComTaskEntity.setMajiax(Integer.parseInt(map.get("majiax").toString()));
//        buComTaskEntity.setMajiab(Integer.parseInt(map.get("majiab").toString()));
//        buComTaskEntity.setId(Integer.parseInt(map.get("id").toString()));
        if(buComTaskService.updateComtask(buComTaskEntity)){
            return JSON.toJSONString("success");
        }else{
            return JSON.toJSONString("failed");
        }
    }

    //评论列表-任务列表的任务新增
    @RequestMapping("/add")
    @RequiresPermissions("business:comtask:add")
    public String add(@RequestBody Map<String,Object> map) {
        BuComTaskEntity buComTaskEntity = JSON.parseObject(JSON.toJSONString(map), BuComTaskEntity.class);
//        //设置分类
//        buComTaskEntity.setTag(Integer.parseInt(map.get("tags").toString()));
//        //设置三个上下限
//        buComTaskEntity.setCounte(0);buComTaskEntity.setCountf(10);buComTaskEntity.setCountj(0);
//        //状态，任务名，发布人
//        buComTaskEntity.setTaskstatus(0);buComTaskEntity.setTaskname(map.get("taskname").toString());
//        buComTaskEntity.setFabup(map.get("fabup").toString());
//        //日期相关
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dNow = new Date( );
//        //计算序号
//        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy");
//        int bid=Integer.parseInt(ft1.format(dNow));
//        bid=bid*10000;
//        int bidadd=buComTaskService.queryMaxId();
//        buComTaskEntity.setBid(bid+bidadd+1+Integer.parseInt(map.get("bid").toString()));
//        //三个日期设定
//        String time=ft.format(dNow);
//        buComTaskEntity.setCreatetime(time);
//        buComTaskEntity.setEndtime(map.get("starttime").toString());buComTaskEntity.setStarttime(map.get("endtime").toString());
//        //设置任务要求，上下限，奖励和附件
//        buComTaskEntity.setTaskreq(map.get("taskreq").toString());
//        buComTaskEntity.setTaskbelimit(Integer.parseInt(map.get("pertoplimit").toString()));
//        buComTaskEntity.setPertoplimit(Integer.parseInt(map.get("pertoplimit").toString()));
//        buComTaskEntity.setReward(Integer.parseInt(map.get("reward").toString()));
//        buComTaskEntity.setFilename(map.get("filename").toString());
//        //高级选项
//        buComTaskEntity.setTitlex(Integer.parseInt(map.get("titlex").toString()));
//        buComTaskEntity.setTitleb(Integer.parseInt(map.get("titleb").toString()));
//        buComTaskEntity.setHrefx(Integer.parseInt(map.get("hrefx").toString()));
//        buComTaskEntity.setHrefb(Integer.parseInt(map.get("hrefb").toString()));
//        buComTaskEntity.setContentx(Integer.parseInt(map.get("contentx").toString()));
//        buComTaskEntity.setContentb(Integer.parseInt(map.get("contentb").toString()));
//        buComTaskEntity.setFilex(Integer.parseInt(map.get("filex").toString()));
//        buComTaskEntity.setFileb(Integer.parseInt(map.get("fileb").toString()));
//        buComTaskEntity.setMajiax(Integer.parseInt(map.get("majiax").toString()));
//        buComTaskEntity.setMajiab(Integer.parseInt(map.get("majiab").toString()));
//        buComTaskEntity.setId(1);
        if(buComTaskService.addComTask(buComTaskEntity)){
            return JSON.toJSONString("success");
        }else{
            return JSON.toJSONString("failed");
        }
    }

    //评论列表-任务列表的任务搜索
    @RequestMapping("/search")
    @RequiresPermissions("business:comtask:search")
    public String search(@RequestParam("tag") Integer tag, @RequestParam("taskname") String taskname,
                         @RequestParam("taskstatus") Integer taskstatus, @RequestParam("starttime") String starttime,
                         @RequestParam("endtime") String endtime,@RequestParam("page")int page,
                         @RequestParam("limit")int limit) {
        JSONObject data=new JSONObject();
        String s=JSON.toJSONString(buComTaskService.searchList(tag,taskname,taskstatus,starttime,endtime,(page-1)*limit,limit));
        data.put("count",buComTaskService.searchList(tag,taskname,taskstatus,starttime,endtime,(page-1)*limit,0).size());
        data.put("code",0);
        data.put("msg","");
        data.put("data",s);
        return data.toString();
    }

    //评论列表-任务列表的任务删除
    @RequestMapping("/del")
    @RequiresPermissions("business:comtask:del")
    public boolean del(@RequestParam Integer id) {
        if(buComTaskService.deleteComTask(id)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-任务列表的任务暂停
    @RequestMapping("/stop")
    @RequiresPermissions("business:comtask:stop")
    public boolean stop(@RequestParam Integer id) {
        if(buComTaskService.stopComtask(id)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-任务列表的查看任务
    @RequestMapping("/look")
    @RequiresPermissions("business:comtask:look")
    public String look(@RequestParam Integer id) {
        BuComTaskEntity buComTaskEntity=buComTaskService.queryById(id);
        return JSON.toJSONString(buComTaskEntity);
    }

    //评论列表-任务数据表
    @RequestMapping("/list/{tag}")
    @RequiresPermissions("business:comtask:list")
    public String list(@PathVariable(name = "tag")String tag,@RequestParam("page")int page,
                       @RequestParam("limit")int limit) {
        List<Map<String,BuComTaskEntity>> list=buComTaskService.pageList(Integer.parseInt(tag),(page-1)*limit,limit);
        JSONObject data=new JSONObject();
        String s=JSON.toJSONString(list);
        data.put("count",buComTaskService.queryAll(Integer.parseInt(tag)));
        data.put("code",0);
        data.put("msg","");
        data.put("data",JSON.toJSONString(list));
        return data.toString();
    }

}
