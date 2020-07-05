package cn.cwnu.modules.business.controller;

import cn.cwnu.modules.business.entity.BuComTagEntity;
import cn.cwnu.modules.business.service.BuComTagService;
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
@RequestMapping("/business/comtag")
public class BuComTagController extends AbstractController {
    @Autowired
    BuComTagService buComTagService;

    //查看评论列表-任务列表的分类的名字通过id
    @RequestMapping("/findname")
    @RequiresPermissions("business:comtag:findname")
    public String findname(@RequestParam Integer id) {
        return JSON.toJSONString(buComTagService.queryById(id));
    }

    //查看评论列表-任务列表的分类
    @RequestMapping("/tab")
    @RequiresPermissions("business:comtag:tab")
    public String tab() {
        List<BuComTagEntity> list=buComTagService.queryList();
        JSONArray data=new JSONArray();
        for(BuComTagEntity b:list){
            JSONObject jo=new JSONObject();
            jo.put("buComtid",b.getBuComtid());
            jo.put("buComtname",b.getBuComtname());
            data.add(jo);
        }
        String s=JSON.toJSONString(data);
        return s;
    }

    //新增评论列表-任务列表的分类
    @RequestMapping("/addtab")
    @RequiresPermissions("business:comtag:addtab")
    public boolean addtab(@RequestParam String name) {
        if(name.equals("") || name==null){
            return false;
        }else{
            if(buComTagService.canAdd(name)){
                return false;
            }else{
                return buComTagService.addComTag(name);
            }
        }
    }

    //评论列表-任务列表的分类删除
    @RequestMapping("/deltab")
    @RequiresPermissions("business:comtag:deltab")
    public boolean deltab(@RequestParam Integer id) {
        if(buComTagService.deleteComTag(id)){
            return true;
        }else{
            return false;
        }
    }

    //评论列表-任务列表的分类修改
    @RequestMapping("/updatetab")
    @RequiresPermissions("business:comtag:updatetab")
    public boolean deltab(@RequestParam String name,@RequestParam Integer id) {
        if(buComTagService.canAdd(name)){
            return false;
        }else{
            if(buComTagService.updateComTag(id,name)){
                return true;
            }else{
                return false;
            }
        }
    }

    @GetMapping("/list/{id}")
    @RequiresPermissions("business:comtag:list")
    public String list(@PathVariable(name = "id")String id) {
        List<BuComTagEntity> list=new ArrayList<>();
        list.add(new BuComTagEntity(1,"asd"));
        list.add(new BuComTagEntity(2,"asdsad"));

        JSONArray jsonArray=new JSONArray();
        JSONArray data=new JSONArray();
        for(BuComTagEntity b:list){
            JSONObject jo=new JSONObject();
            jo.put("buComtid",b.getBuComtid());
            jo.put("buComtname",b.getBuComtname());
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
