//转到创建新任务页面
function createNewTask() {
    window.location.href='addTask.html';
}
window.onload = function() {
    var vm = new Vue({
        el: '#app',
        data: {
            //用于修改传值的
            name:'',
            //用来循环输出tag的
            sites:'',
            //用来查询数据的
            tagid:'',
        },
        created:function(){
            //加载初始化的标签数据
            $.ajax({
                type: 'POST',
                url: baseURL + '/business/sptag/tab',//发送请求
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
            change:function(id){
                vm.tagid=id;
                //调用layui 重载数据表格
                lay.table.reload('test', {
                    url: baseURL + '/business/sptag/list/' + id
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
                    $.post(baseURL + '/business/sptag/updatetab',{
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
                    $.post(baseURL + '/business/sptag/deltab',{
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
                    $.post(baseURL + '/business/sptag/addtab',{
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
var lay=layui.use(['table', 'laypage', 'element', 'laydate'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var page = layui.laypage;
    var element = layui.element;
    var date = layui.laydate;
    var form=layui.form;
    form.render();
    date.render({elem: '#date1'});
    date.render({elem: '#date2'});
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
        , url: baseURL + '/business/sptag/list/0'
        , method: 'get'
        , limit: 10
        , line: 200
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , title: '用户数据表'
        , cols: [[
            {field: 'buSptid', title: 'id', width: 100, fixed: 'left', unresize: true, sort: true, align: 'center'}
            , {field: 'buSptname', title: 'name', width: 100, edit: 'text', align: 'center'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
        ]]
        , page: true
    });
    //table 监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        //obj.data是所有的数据
        if (obj.event === 'del') {
            console.log(data['buSptid']);
            //JavaScript POST到新的后台 进行删除
            // var parames=new Array();
            // parames.push({name:"bank_type_id",value:obj.data['bank_type_id']});
            // Post("__CONTROLLER__/bank_delete",parames);
        } else if (obj.event === 'edit') {
            console.log(data['buSptname']);
            //JavaScript POST到新的后台 进行更新
            // var parames=new Array();
            // parames.push({name:"bank_type_id",value:obj.data['bank_type_id']});
            // Post("__CONTROLLER__/bank_update",parames);
        }
    });
});













