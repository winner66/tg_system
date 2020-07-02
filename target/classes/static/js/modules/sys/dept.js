var vm=new Vue({
    el:'#dept_main',
    data:{

    },
    methods:{
        addDept: function(othis){
            $.get('dept/edit', function(data) {
                layer.open({
                    type: 1,
                    title: '新增',
                    area: ['530px'],
                    content: data,
                    btn: ['提交', '退出'],
                    yes: function () {
                    },
                    success: function (layero, index) {
                        layui.use('form', function () {
                            var form = layui.form;
                            layero.addClass('layui-form');
                            var submitBtn = layero.find('.layui-layer-btn0');
                            submitBtn.attr('lay-filter', 'formVerify').attr('lay-submit', '');
                            layero.keydown(function (e) {
                                if (e.keyCode == 13) {
                                    submitBtn.click();
                                }
                            });

                            form.on('submit(formVerify)', function (data) {
                                $.post('dept/save', data.field, function (result) {
                                    if (result.success) {
                                        layer.close(index);
                                        tree.reload('treeId', {data: getData()});
                                    }
                                    layer.msg(result.msg, {offset: 'rb'});
                                });
                                return false;
                            });
                        });
                    }
                })
            })
        },
        // gain: function () {
        //     var checkData = tree.getChecked('treeId');
        //     var str = JSON.stringify(checkData);
        //     $.post('dept/checkedGain', {data: str}, function () {
        //     });
        //     layer.alert(JSON.stringify(checkData), {shade: 0});
        //
        // }
    }
})
// var vm_right= new Vue({
// //     el:'#dept_main',
// //     data:{
// //
// //     },
// //     methods:{}
// //
// // })
layui.use('table', function(){
    var table = layui.table;
    table.render({
        elem: '#test'
        ,        // url:baseURL+'sys/user/list'
        url:baseURL + "sys/clientUser/list"
        ,method:'post'
        ,toolbar: '#toolbarDemo'
        ,title: '用户数据表'
        ,limit:10
        ,width : 1000
        ,height : 650
        ,totalRow: true
        ,cols: [[
            {type: 'checkbox', fixed: 'left' ,unresize: true, sort: true, totalRowText: '合计'},
            {title: '用户ID', field: 'id', width: 100,fixed: 'left',totalRow: true },
            {title: '真实姓名', field: 'realname',  edit: 'text' ,width: 100},
            {title: '登录账号', field: 'username', edit: 'text',width: 100},
            {title: '手机号码', field: 'mobile',edit: 'text', width:100},
            {title: '邮箱', field: 'email', edit: 'text', templet: function(res){
                    return '<em>'+ res.email +'</em>'
                } ,width:100},
            {title: '性别', field: 'sex',edit: 'text', width: 80},
            {title: '单位名称', field: 'company',edit: 'text', width: 100},
            {title: '用户身份', field: 'identity', edit: 'text',width: 100},
            {title: '创建时间', field: 'createTime',edit: 'text',  width: 100},
            {title: '在线状态', field: 'online',edit: 'text', width: 100, templet: function (res) {
                    return res.online=== 0 ?
                        '<em>离线</em>' :
                        '<em>在线</em>';
                }
            },
            {fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ]]
        ,page: true
    });

    //工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
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
                layer.msg(checkStatus.isAll ? '全选': '未全选')
                break;

            case 'batchAdd':
                layer.msg("批量增加")
                break;
            case 'batchdelete':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));

                layer.msg("批量删除")
                break;
            case 'add':
                layer.msg("增加")
                break;

        };
    });
});
//////////以上属于table
function getData() {
    var data = [];
    $.ajax({
        url: baseURL+"/sys/dept/deptTree/0",    //后台数据请求地址
        type: "post",
        async: false,
        success: function (resut) {
            data = resut.data;
        }
    });
    return data;
}
layui.use(['tree', 'util'], function(){
    var tree = layui.tree
        ,layer = layui.layer
        ,util = layui.util

        //模拟数据1
        ,data1 = [{
            title: '江西'
            ,id: 1
            ,children: [{
                title: '南昌'
                ,id: 1000
                ,children: [{
                    title: '青山湖区'
                    ,id: 10001
                },{
                    title: '高新区'
                    ,id: 10002
                }]
            },{
                title: '九江'
                ,id: 1001
            },{
                title: '赣州'
                ,id: 1002
            }]
        },{
            title: '广西'
            ,id: 2
            ,children: [{
                title: '南宁'
                ,id: 2000
            },{
                title: '桂林'
                ,id: 2001
            }]
        },{
            title: '陕西'
            ,id: 3
            ,children: [{
                title: '西安'
                ,id: 3000
            },{
                title: '延安'
                ,id: 3001
            }]
        }];
    //按钮事件
    util.event('lay-demo', {
        getChecked: function(othis){
            var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据

            layer.alert(JSON.stringify(checkedData), {shade:0});
            console.log(checkedData);
        }
        ,setChecked: function(){
            tree.setChecked('demoId1', [12, 16]); //勾选指定节点
        }
        ,reload: function(){
            //重载实例
            tree.reload('demoId1', {

            });

        }

    });
    var data=getData();
    console.log(data);
    tree.render({
        elem: '#test9'
        ,data: data
        ,showCheckbox: true  //是否显示复选框
        ,id: 'demoId1'
        ,edit: ['add', 'update', 'del'] //操作节点的图标
        ,isJump: true //是否允许点击节点时弹出新窗口跳转
        ,click: function(obj){
            var data = obj.data;  //获取当前点击的节点数据
            layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
            // var id = obj.data.id;  //阔以查看   某机构的人员信息
            // $("#dept_home").load("dept/show?id="+id);

        },
        operate: function(obj) {
            var type = obj.type; //得到操作类型：add、edit、del
            var data = obj.data; //得到当前节点的数据
            var elem = obj.elem; //得到当前节点元素

            var id = data.id;
            var name = data.title;
            if (type === 'add') { //增加节点
                $.post("dept/save", {parentId: id, name: "未命名"}, function (result) {
                    tree.reload('treeId', {data: getData()});
                })

                return;
            } else if (type === 'update') { //修改节点
                $.post("dept/update", {id: id, name: name}, function () {
                    tree.reload('treeId', {data: getData()});
                })
            } else if (type === 'del') { //删除节点
                $.post("dept/delete", {id: id}, function () {
                    tree.reload('treeId', {data: getData()});
                });
            };
        }

    });

});