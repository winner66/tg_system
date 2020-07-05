window.onload = function() {
    var s;
    $.ajax({
        type: 'POST',
        url: baseURL + '/business/comtag/tab',//发送请求
        data: 'data',
        dataType: "json",
        success: function (res) {
            console.log(res.data)
            var vm = new Vue({
                el: '#app',
                data: {
                    sites: res
                },
            });
        }
    });
}

layui.use(['table','laypage','element'], function(){
    var $=layui.jquery;
    var table = layui.table;
    var page=layui.laypage;
    var element = layui.element;
    element.on('tab(docDemoTabBrief)', function() {
        var laystatus = this.getAttribute('lay-status');
        getPageC(laystatus);
    });
    function getPageC(status){
        //layui table组件
        table.render({
            elem: '#test'+status
            ,url:baseURL+'/business/comtag/list/'+status
            ,method:'get'
            ,limit:10
            ,line:200
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {field:'buComtid', title:'id', width:400, fixed: 'left', unresize: true, sort: true,align:'center'}
                ,{field:'buComtname', title:'name', width:400, edit: 'text',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
        });
    }
    //默认调用的table组件
    table.render({
        elem: '#test'+1
        ,url:baseURL+'/business/comtag/list/'+1
        ,method:'get'
        ,limit:10
        ,line:200
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            ,layEvent: 'LAYTABLE_TIPS'
            ,icon: 'layui-icon-tips'
        }]
        ,title: '用户数据表'
        ,cols: [[
            {field:'buComtid', title:'id', width:400, fixed: 'left', unresize: true, sort: true,align:'center'}
            ,{field:'buComtname', title:'name', width:400, edit: 'text',align:'center'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ]]
        ,page: true
    });
    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        /*暂时用不到的头工具行
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };*/
    });
    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        //obj.data是所有的数据
        if(obj.event === 'del'){
            alert("asd");
            //JavaScript POST到新的后台 进行删除
            // var parames=new Array();
            // parames.push({name:"bank_type_id",value:obj.data['bank_type_id']});
            // Post("__CONTROLLER__/bank_delete",parames);
        } else if(obj.event === 'edit'){
            //JavaScript POST到新的后台 进行更新
            // var parames=new Array();
            // parames.push({name:"bank_type_id",value:obj.data['bank_type_id']});
            // Post("__CONTROLLER__/bank_update",parames);
        }
    });
});