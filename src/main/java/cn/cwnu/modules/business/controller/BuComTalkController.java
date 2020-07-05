package cn.cwnu.modules.business.controller;

import cn.cwnu.modules.business.entity.BuComTalkEntity;
import cn.cwnu.modules.business.entity.BuComTaskEntity;
import cn.cwnu.modules.business.service.BuComTalkService;
import cn.cwnu.modules.business.service.BuComTaskService;
import cn.cwnu.modules.sys.controller.AbstractController;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/business/comtalk")
public class BuComTalkController extends AbstractController {
    @Autowired
    BuComTalkService buComTalkService;

    //评论列表-稿件列表的稿件奖励
    @RequestMapping("/reward")
    @RequiresPermissions("business:comtalk:reward")
    public boolean reward(@RequestParam Integer id,@RequestParam Integer reward) {
        if(buComTalkService.reward(id,reward)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-稿件列表的稿件拒绝
    @RequestMapping("/stop")
    @RequiresPermissions("business:comtalk:stop")
    public boolean reward(@RequestParam Integer id,@RequestParam String reason) {
        if(buComTalkService.stop(id,reason)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-稿件列表的稿件最大id
    @RequestMapping("/maxid")
    @RequiresPermissions("business:comtalk:maxid")
    public int maxid() {
        return buComTalkService.queryMAX();
    }

    //评论列表-稿件列表的稿件通过
    @RequestMapping("/pass")
    @RequiresPermissions("business:comtalk:pass")
    public boolean reward(@RequestParam Integer id) {
        if(buComTalkService.pass(id)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-稿件列表的查看任务
    @RequestMapping("/look")
    @RequiresPermissions("business:comtalk:look")
    public String look(@RequestParam Integer id) {
        BuComTalkEntity buComTaskEntity=buComTalkService.queryById(id);
        return JSON.toJSONString(buComTaskEntity);
    }

    //评论列表-稿件列表的任务搜索
    @RequestMapping("/search")
    @RequiresPermissions("business:comtalk:search")
    public String search(@RequestParam("taskid") Integer taskid, @RequestParam("title") String title,@RequestParam("name") String name,
                         @RequestParam("status") Integer status, @RequestParam("starttime") String starttime,
                         @RequestParam("endtime") String endtime,@RequestParam("page")int page,
                         @RequestParam("limit")int limit) {
        JSONObject data=new JSONObject();
        String s=JSON.toJSONString(buComTalkService.searchList(taskid,title,name,status,starttime,endtime,(page-1)*limit,limit));
        data.put("count",buComTalkService.searchList(taskid,title,name,status,starttime,endtime,(page-1)*limit,0).size());
        data.put("code",0);
        data.put("msg","");
        data.put("data",s);
        return data.toString();
    }

    //评论列表-稿件数据表
    @RequestMapping("/list/{taskid}")
    @RequiresPermissions("business:comtalk:list")
    public String list(@PathVariable(name = "taskid")String taskid,@RequestParam("page")int page,
                       @RequestParam("limit")int limit) {
        List<Map<String,BuComTalkEntity>> list=buComTalkService.pageList(Integer.parseInt(taskid),(page-1)*limit,limit);
        JSONObject data=new JSONObject();
        String s=JSON.toJSONString(list);
        data.put("count",buComTalkService.queryAll(Integer.parseInt(taskid)));
        data.put("code",0);
        data.put("msg","");
        data.put("data",JSON.toJSONString(list));
        return data.toString();
    }

}
