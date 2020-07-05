//转到创建新任务页面
function createNewTask() {
    window.location.href='addTask.html';
}
window.onload = function() {
    var vm = new Vue({
        el: '#app',
        data: {
            //任务名称
            title:'',
            //用于修改传值的
            name:'',
            //用来循环输出tag的
            sites:'',
            //用来查询数据的
            taskid:0,
        },
        created:function(){
            //加载初始化的标签数据
            $.post(baseURL + '/business/comtask/tab',{
                'taskname':"",
                'starttime':"",
                'endtime':"",
            },function (data) {
                vm.sites=data;
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
                var status=$("#status").find("option:selected").val();
                //console.log("tag:"+vm.tagid+"date1:"+date1+"date2:"+date2+"taskname:"+vm.taskname+"taskstatus"+taskstatus)
                lay.table.reload('test', {
                    url: baseURL + '/business/comtalk/search?taskid='+vm.taskid+'&name='+vm.name+'&starttime='+date1+'&endtime='+date2+'&status='+status+'&title='+vm.title
                    ,page:{
                        curr:1
                    }
                });
            },
            change:function(id){
                vm.taskid=id;
                //调用layui 重载数据表格
                lay.table.reload('test', {
                    url: baseURL + '/business/comtalk/list/'+id
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
        , url: baseURL + '/business/comtalk/list/0'
        , method: 'get'
        , limit: 10
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , title: '用户数据表'
        , cols: [[
            {field: 'xuhao', title: '序号', width: 100, fixed: 'left',type:'numbers', unresize: true, sort: true, align: 'center'}
            , {field: 'name', title: '人员姓名', width: 100, edit: 'text', align: 'center'}
            , {field: 'majia', title: '马甲', width: 100, edit: 'text', align: 'center'}
            , {field: 'title', title: '标题/内容', width: 100, edit: 'text', align: 'center'}
            , {field: 'createtime', title: '发布时间', width: 100, edit: 'text', align: 'center'}
            , {field: 'reward', title: '任务奖励', width: 100, edit: 'text', align: 'center'}
            , {field: 'status', title: '状态', width: 100, edit: 'text', align: 'center',
                templet: function(res) {  //回答数为0时:未回答 回答数>0且未解决时:未解决
                    if(res.status == '0') {
                        return '<span style="color:yellow;">待审核</span>'
                    } else if(res.status== '1') {
                        return '<span style="color:green;">合格</span>';
                    } else{
                        return '<span style="color:red;">驳回</span>';
                    }
                }
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
        ]]
        , page: true
    });
    //table 监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        //obj.data是所有的数据
        if (obj.event === 'reword') {
            layer.prompt({title:'设置奖励'},function(val, index){
                $.post(baseURL + '/business/comtalk/reward',{
                    'id':data['id'],
                    'reward':val
                },function (data) {
                    if(data==true){
                        layer.msg('设置成功');
                        setTimeout(function () {vm.refresh();},1000);
                        table.reload("test");
                    }else{
                        layer.msg('设置失败');
                        setTimeout(function () {vm.refresh();},1000);
                    }
                });
                title:'请输入值',
                    layer.close(index);
            });
        } else if (obj.event === 'stop') {
            layer.prompt({title:'驳回理由'},function(val, index){
                $.post(baseURL + '/business/comtalk/stop',{
                    'id':data['id'],
                    'reason':val
                },function (data) {
                    if(data==true){
                        layer.msg('已驳回');
                        setTimeout(function () {vm.refresh();},1000);
                        table.reload("test");
                    }else{
                        layer.msg('驳回失败');
                        setTimeout(function () {vm.refresh();},1000);
                    }
                });
                title:'请输入值',
                    layer.close(index);
            });
        } else if (obj.event === 'look') {
            //任务查看
            window.location.href="lookTalk.html?id="+data['id'];
        }
    });
});













