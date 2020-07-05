//转到创建新任务页面
function createNewTask() {
    window.location.href='addTask.html';
}
window.onload = function() {
    var vm = new Vue({
        el: '#app',
        data: {
            //任务名称
            taskname:'',
            //用于修改传值的
            name:'',
            //用来循环输出tag的
            sites:'',
            //用来查询数据的
            tagid:0,
        },
        created:function(){
            //加载初始化的标签数据
            $.ajax({
                type: 'POST',
                url: baseURL + '/business/comtag/tab',//发送请求
                data: 'data',
                dataType: "json",
                success: function (res) {
                    vm.sites=res;
                }
            });
        },
        mounted:function(){
            //初始化下layui
            lay;
        },
        methods:{
            search:function(){
                var date1=$("#date1").val();
                var date2=$("#date2").val();
                var taskstatus=$("#taskstatus").find("option:selected").val();
                //console.log("tag:"+vm.tagid+"date1:"+date1+"date2:"+date2+"taskname:"+vm.taskname+"taskstatus"+taskstatus)
                lay.table.reload('test', {
                    url: baseURL + '/business/comtask/search?tag='+vm.tagid+'&taskname='+vm.taskname+'&starttime='+date1+'&endtime='+date2+'&taskstatus='+taskstatus
                    ,page:{
                        curr:1
                    }
                });
            },
            change:function(id){
                vm.tagid=id;
                //调用layui 重载数据表格
                lay.table.reload('test', {
                    url: baseURL + '/business/comtask/list/'+id
                    ,page:{
                        curr:1
                    }
                });
            },
            //增删改后刷新页面
            refresh:function(){
                window.parent.location.reload();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            updataTab:function(id){
                layer.prompt({title:'修改分类'},function(val, index){
                    $.post(baseURL + '/business/comtag/updatetab',{
                        'id':id,
                        'name':val
                    },function (data) {
                        if(data==true){
                            layer.msg('修改成功');
                            setTimeout(function () {vm.refresh();},1000);
                        }else{
                            layer.msg('修改失败');
                            setTimeout(function () {vm.refresh();},1000);
                        }
                    });
                    title:'请输入值',
                        layer.close(index);
                });
            },
            delTab:function(id){
                layer.confirm('是否要删除', {
                    title:'删除分类',
                    btn: ['删除','取消'] //按钮
                }, function(){
                    $.post(baseURL + '/business/comtag/deltab',{
                        'id':id
                    },function(data){
                        if(data==true){
                            layer.msg('删除成功');
                            setTimeout(function () {vm.refresh();},1000);
                        }else{
                            layer.msg('删除失败');
                            setTimeout(function () {vm.refresh();},1000);
                        }
                    });
                });
            },
            addTab:function (){
                layer.prompt({title:'添加分类'},function(val, index){
                    $.post(baseURL + '/business/comtag/addtab',{
                        'name':val
                    },function (data) {
                        if(data==true){
                            layer.msg('添加成功');
                            setTimeout(function () {vm.refresh();},1000);
                        }else{
                            layer.msg('添加失败');
                            setTimeout(function () {vm.refresh();},1000);
                        }
                    });
                    layer.close(index);
                });
            }
        }
    });
}
//layui 组件
var lay=layui.use(['table', 'element', 'laydate'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var element = layui.element;
    var date = layui.laydate;
    var form=layui.form;
    form.render();
    date.render({elem: '#date1',type: 'datetime'});
    date.render({elem: '#date2',type: 'datetime'});
    // //tab切换
    // element.on('tab(docDemoTabBrief)', function () {
    //     var laystatus = this.getAttribute('lay-status');
    //     //对应切换重载数据表格
    //     table.reload('test', {
    //         url: baseURL + '/business/comtag/list/' + laystatus
    //     });
    //     //laydate重载
    //     date.render({elem: '#date1'});
    //     date.render({elem: '#date2'});
    // });
    //layui table组件 初始化设置0
    table.render({
        elem: '#test'
        , url: baseURL + '/business/comtask/list/0'
        , method: 'get'
        , limit: 10
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , title: '用户数据表'
        , cols: [[
            {field: 'bid', title: '编号', width: 100, fixed: 'left', unresize: true, sort: true, align: 'center'}
            , {field: 'taskname', title: '任务名称', width: 100, edit: 'text', align: 'center'}
            , {field: 'fabup', title: '发布人', width: 100, edit: 'text', align: 'center'}
            , {field: 'countf', title: '发送人数', width: 100, edit: 'text', align: 'center'}
            , {field: 'countj', title: '接受人数', width: 100, edit: 'text', align: 'center'}
            , {field: 'counte', title: '完成量', width: 100, edit: 'text', align: 'center'}
            , {field: 'createtime', title: '发布时间', width: 200, edit: 'text', align: 'center'}
            , {field: 'taskstatus', title: '状态', width: 100, edit: 'text', align: 'center',
                templet: function(res) {  //回答数为0时:未回答 回答数>0且未解决时:未解决
                    if(res.taskstatus == '0') {
                        return '<span style="color:green;">已发布</span>'
                    } else if(res.taskstatus= '1') {
                        return '<span style="color:red;">已结束</span>';
                    }
                }
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 300}
        ]]
        , page: true
    });
    //table 监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        //obj.data是所有的数据
        if (obj.event === 'del') {
            //删除任务
            $.post(baseURL + '/business/comtask/del',{
                'id':data['id'],
            },function (data) {
                if(data==true){
                    layer.msg('修改成功');
                    lay.table.reload('test');
                }else{
                    layer.msg('修改失败');
                    lay.table.reload('test');
                }
            });
        } else if (obj.event === 'look') {
            //任务查看
            window.location.href="lookTask.html?id="+data['id'];
        }
        else if (obj.event === 'add') {
            //追加人员
            console.log(data['id']);
        }
        else if (obj.event === 'edit') {
            //任务修改
            window.location.href="updateTask.html?id="+data['id'];
        }
        else if (obj.event === 'send') {
            //发送短信
            console.log(data['id']);
        }
        else if (obj.event === 'stop') {
            //暂停任务
            $.post(baseURL + '/business/comtask/stop',{
                'id':data['id'],
            },function (data) {
                if(data==true){
                    layer.msg('修改成功');
                    lay.table.reload('test');
                }else{
                    layer.msg('修改失败');
                    lay.table.reload('test');
                }
            });
        }
    });
});













