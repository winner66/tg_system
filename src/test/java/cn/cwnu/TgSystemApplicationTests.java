package cn.cwnu;

import cn.cwnu.modules.business.entity.BuComTaskEntity;
import cn.cwnu.modules.business.service.BuComTagService;
import cn.cwnu.modules.business.service.BuComTalkService;
import cn.cwnu.modules.business.service.BuComTaskService;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.xmlunit.util.Linqy.count;


@SpringBootTest
class TgSystemApplicationTests{

    @Autowired
    BuComTagService buComTagService;

    @Autowired
    BuComTaskService buComTaskService;

    @Autowired
    BuComTalkService buComTalkService;

    @Test
    void contextLoads() {
        //待测试
        System.out.println(buComTalkService.pass(1));

        //查询测&&输出json
//        List<Map<String,BuComTaskEntity>> list=buComTaskService.pageList(0,0,10);
//        JSONObject data=new JSONObject();
//        String s=JSON.toJSONString(list);
//        data.put("count",2);
//        data.put("code",0);
//        data.put("msg","");
//        data.put("data",s);
//        System.out.println(data.toString());

//        //新增任务
//        BuComTaskEntity buComTaskEntity=new BuComTaskEntity();
//        buComTaskEntity.setId(1);
//        buComTaskEntity.setTag(1);
//        buComTaskEntity.setCounte(0);buComTaskEntity.setCountf(10);buComTaskEntity.setCountj(0);
//        buComTaskEntity.setTaskstatus(0);
//        buComTaskEntity.setTaskname("test");buComTaskEntity.setFabup("超级管理员");
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy");
//        Date dNow = new Date( );
//        Integer bid=Integer.parseInt(ft1.format(dNow));
//        bid=bid*10000;
//        int bidadd=buComTaskService.queryMaxId();
//        buComTaskEntity.setBid(bidadd+1+bid);
//        String time=ft.format(dNow);
//        buComTaskEntity.setCreatetime(time);
//        buComTaskEntity.setEndtime(time);
//        buComTaskEntity.setStarttime(time);
//        buComTaskEntity.setTaskreq("more");
//        buComTaskEntity.setReward(10);
//        buComTaskEntity.setFilename("sdf.jpg,asfd.doc");
//        buComTaskEntity.setTitlex(0);
//        buComTaskEntity.setTitleb(0);
//        buComTaskEntity.setHrefx(1);
//        buComTaskEntity.setHrefb(0);
//        buComTaskEntity.setContentx(1);
//        buComTaskEntity.setContentb(0);
//        buComTaskEntity.setFilex(1);
//        buComTaskEntity.setFileb(1);
//        buComTaskEntity.setMajiax(1);
//        buComTaskEntity.setMajiab(1);
//        System.out.println(buComTaskEntity);
//        System.out.println(buComTaskService.updateComtask(buComTaskEntity));
//        System.out.println(buComTaskService.addComTask(buComTaskEntity));

        //日期试验
//        Date dNow = new Date( );
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time="2020-7-7 15:52:20";
//        ParsePosition pos = new ParsePosition(0);
//        String s=ft.format(ft.parse(time,pos));
//        System.out.println("当前时间为: " + ft.format(dNow));
//        System.out.println(s);

        //Json转换试验
//        List<BuComTagEntity> list=buComTagService.queryList();
//        JSONArray data=new JSONArray();
//        for(BuComTagEntity b:list){
//            JSONObject jo=new JSONObject();
//            jo.put("buComtid",b.getBuComtid());
//            jo.put("buComtname",b.getBuComtname());
//            data.add(jo);
//        }
//        String s=JSON.toJSONString(data);
//        System.out.println(s);
    }
}
