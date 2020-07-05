package cn.cwnu.modules.business.controller;

import cn.cwnu.modules.business.entity.BuSpTagEntity;
import cn.cwnu.modules.business.service.BuSpTagService;
import cn.cwnu.modules.sys.controller.AbstractController;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/business/sptag")
public class BuSpTagController extends AbstractController {
    @Autowired
    BuSpTagService buSpTagService;

    //查看评论列表-任务列表的分类
    @RequestMapping("/tab")
    @RequiresPermissions("business:sptag:tab")
    public String tab() {
        List<BuSpTagEntity> list=buSpTagService.queryList();
        JSONArray data=new JSONArray();
        for(BuSpTagEntity b:list){
            JSONObject jo=new JSONObject();
            jo.put("buSptid",b.getBuSptid());
            jo.put("buSptname",b.getBuSptname());
            data.add(jo);
        }
        String s=JSON.toJSONString(data);
        return s;
    }

    //新增评论列表-任务列表的分类
    @RequestMapping("/addtab")
    @RequiresPermissions("business:sptag:addtab")
    public boolean addtab(@RequestParam String name) {
        if(name.equals("") || name==null){
            return false;
        }else{
            if(buSpTagService.canAdd(name)){
                return false;
            }else{
                return buSpTagService.addSpTag(name);
            }
        }
    }

    //评论列表-任务列表的分类删除
    @RequestMapping("/deltab")
    @RequiresPermissions("business:sptag:deltab")
    public boolean deltab(@RequestParam Integer id) {
        if(buSpTagService.deleteSpTag(id)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-任务列表的分类修改
    @RequestMapping("/updatetab")
    @RequiresPermissions("business:sptag:updatetab")
    public boolean deltab(@RequestParam String name,@RequestParam Integer id) {
        if(buSpTagService.canAdd(name)){
            return false;
        }else{
            if(buSpTagService.updateSpTag(id,name)){
                return true;
            }else{
                return false;
            }
        }
    }

    @GetMapping("/list/{id}")
    @RequiresPermissions("business:sptag:list")
    public String list(@PathVariable(name = "id")String id) {
        List<BuSpTagEntity> list=new ArrayList<>();
        list.add(new BuSpTagEntity(1,"asd"));
        list.add(new BuSpTagEntity(2,"asdsad"));

        JSONArray jsonArray=new JSONArray();
        JSONArray data=new JSONArray();
        for(BuSpTagEntity b:list){
            JSONObject jo=new JSONObject();
            jo.put("buSptid",b.getBuSptid());
            jo.put("buSptname",b.getBuSptname());
            data.add(jo);
        }
        JSONObject jj=new JSONObject();
        jj.put("count",2);
        jj.put("code",0);
        jj.put("msg","");
        jj.put("data",list);
        String s=JSON.toJSONString(jj);
        if(Integer.parseInt(id)==1){
            System.out.println(id);
        }
        return s;
    }

}
