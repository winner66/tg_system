// $('#searchBtn').on('click',function(){
//     var type = $(this).data('type');
//     active[type] ? active[type].call(this) : '';
// });
// // 点击获取数据
// var  active = {
//     getInfo: function () {
//         var orderId=$('#search_dept').val();
//         if (orderId) {
//             var index = layer.msg('查询中，请稍候...',{icon: 16,time:false,shade:0});
//             setTimeout(function(){
//                 table.reload('LAY-app-message-all', { //表格的id
//                     url:'/Order/selectByOrderId',
//                     where: {
//                         'orderId':$.trim(orderId)
//                     }
//                 });
//                 layer.close(index);
//             },800);
//         } else {
//             layer.msg("请输入");
//         }
//     },
// };
// //监听回车事件,扫描枪一扫描或者按下回车键就直接执行查询
// $("#search_dept").bind("keyup", function (e) {
//     if (e.keyCode == 13) {
//         var type = "getInfo";
//         active[type] ? active[type].call(this) : '';
//     }
// });
//
var show_add=true;
// var vm=new Vue({
//     el:'#dept_main',
//     data:{
//
//     },
//     methods:{
//         addDept: function(othis){
//             $.get('dept/edit', function(data) {
//                 layer.open({
//                     type: 1,
//                     title: '新增',
//                     area: ['530px'],
//                     content: data,
//                     btn: ['提交', '退出'],
//                     yes: function () {
//                     },
//                     success: function (layero, index) {
//                         layui.use('form', function () {
//                             var form = layui.form;
//                             layero.addClass('layui-form');
//                             var submitBtn = layero.find('.layui-layer-btn0');
//                             submitBtn.attr('lay-filter', 'formVerify').attr('lay-submit', '');
//                             layero.keydown(function (e) {
//                                 if (e.keyCode == 13) {
//                                     submitBtn.click();
//                                 }
//                             });
//
//                             form.on('submit(formVerify)', function (data) {
//                                 $.post('dept/save', data.field, function (result) {
//                                     if (result.success) {
//                                         layer.close(index);
//                                         tree.reload('treeId', {data: getData()});
//                                     }
//                                     layer.msg(result.msg, {offset: 'rb'});
//                                 });
//                                 return false;
//                             });
//                         });
//                     }
//                 })
//             })
//         },
//         addGroup: function(othis) {
//         }
//
//         // gain: function () {
//         //     var checkData = tree.getChecked('treeId');
//         //     var str = JSON.stringify(checkData);
//         //     $.post('dept/checkedGain', {data: str}, function () {
//         //     });
//         //     layer.alert(JSON.stringify(checkData), {shade: 0});
//         //
//         // }
//     }
// })
var edit;
var Ad_type;
if(parseInt(userInfo.deptId)>parseInt("0")){
    edit= ['update','del'];
    Ad_type=1;
}else {
    Ad_type=0;
    edit=['add','update','del'] ;
}

var vm_right= new Vue({
    el:'#dept_main',
    data:{
        deptID:userInfo.deptId,
        dept_name:userInfo.deptName,
    },
    methods:{
        getDeptName:function (id) {
            $.ajax({
                url: baseURL+"/info/"+id,    //后台数据请求地址
                type: "post",
                async: false,
                success: function (resut) {
                    data = resut.dept;
                    console.log(data);
                    vm_right.dept_name=data.name;
                }
            });
        }

    },
    create:function () {
        // vm_right.getDeptName(vm_right.deptID);
    }

})
layui.use(['table','layer','tree', 'util'], function(){
    var table = layui.table;
    var $ = layui.jquery; //独立版的layer无需执行这一句
    table.render({
        elem: '#test'
        ,url:baseURL + "sys/clientUser/list"
        ,method:'post'
        ,toolbar: '#toolbarDemo'
        ,title: '用户数据表'
        ,limit:10
        ,width : 1000
        ,height : 800
        ,totalRow: true
        ,cols: [[
            {type: 'checkbox', fixed: 'left' ,unresize: true, sort: true, totalRowText: '合计'},
            {title: '用户ID', field: 'id', width: 100,fixed: 'left',totalRow: true },
            {title: '真实姓名', field: 'username',  edit: 'text' ,width: 100},
            {title: '马甲', field: 'realname',  edit: 'text' ,width: 100},
            {title: '登录账号', field: 'account', edit: 'text',width: 100},
            {title: '手机号码', field: 'mobile',edit: 'text', width:100},
            {title: '邮箱', field: 'email', edit: 'text', templet: function(res){
                    return '<em>'+ res.email +'</em>'
                } ,width:100},
            {title: '性别', field: 'sex',edit: 'text', width: 80},
            {title: '单位名称', field: 'company',edit: 'text', width: 100},
            {title: '用户身份', field: 'identity', edit: 'text',width: 100},
            {title: '创建时间', field: 'createTime',edit: 'text',  width: 100},
            // {title: '是否会员', field: 'isvip',edit: 'text',  width: 100},
            // {title: '会员服务期', field: 'createTime',edit: 'text',  width: 100},
            {title: '在线状态', field: 'online',edit: 'text', width: 100,
                templet: function (res) {
                  if(res.online== 0){
                      return '<span style="color:green;">离线</span>'
                  }else if(res.online= '1') {
                      return '<span style="color:red;">在线</span>';
                  }
                }
            },
            {fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ]]
        ,page: true
    });

    //工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        var data= obj.data;
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
                // layer.msg("增加")
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: ['80%','80%']
                    ,shade: 0.8
                    ,id: 'LAY_add' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: $('#lay_add')
                    // ,  content: $("#test"),//支持获取DOM元素
                    ,btn: ['确定', '取消'] //按钮组
                    ,scrollbar: false //屏蔽浏览器滚动条
                    ,yes: function(index){//layer.msg('yes');    //点击确定回调

                        // showToast();
                        var status=$("input[name='status']:checked").val();
                        var sex=$("input[name='sex']:checked").val();
                        if(sex==1){
                            sex="男"
                        }else{
                            sex="女"
                        }
                        var username=$("input[name='username']").val()
                        var account=$("input[name='account']").val()
                        var password=$("input[name='password']").val()
                        var mobile=$("input[name='mobile']").val()
                        var email=$("input[name='email']").val()
                        var address= $("input[name='address']").val()
                        var bank=$("input[name='bank']").val()
                        var bankNum=$("input[name='bankNum']").val()
                        var remarks=$("input[name='remarks']").val()
                        var level=$("input[name='level']").val()
                        var accountdata={"account":account}
                        //验证电话号码
                        if(mobile==""){
                            layer.msg("请输入电话号码")
                        }
                        if(password==""){
                            layer.msg("请输入登录密码")
                        }
                        if(account==""){
                            layer.msg("请输入登录账号")
                        }
                        if(level==""){
                            level=1;
                        }
                        if(username==""){
                            layer.msg("请输入用户名")
                        }
                        var data={"status":status,
                            "sex":sex,
                            //登录名
                            "username":username,
                            //账号
                            "account":account,
                            //马甲
                            // "realname":realName,
                            "password":password,
                            "phone":mobile,
                            "email":email,
                            "address":address,
                            "bank":bank,
                            "bankNum":bankNum,
                            "remarks":remarks,
                            "level":level,
                            //    从当前的状态获取

                            "deptId":userInfo.deptId,
                            "dept":userInfo.deptName,
                            //    1: 机构-人员
                            "type":1,
                            //    组成员
                            //     "type":0,
                            //     "groupId"=
                        };
                        $.ajax({
                            type: 'POST',
                            url: baseURL + "sys/clientUser/accountVail",
                            async: false,
                            // headers: {"token": token},
                            contentType: 'application/json',
                            dataType: 'json',
                            data: JSON.stringify(accountdata),
                            success: function (data, status, xhr) {
                                // console.log(data);
                                // layer.close(index);
                                //登录名不重复
                                if(data.code==0){
                                    $.ajax({
                                        type: 'POST',
                                        url: baseURL + "sys/clientUser/save",
                                        async: false,
                                        // headers: {"token": token},
                                        contentType: 'application/json',
                                        dataType: 'json',
                                        data: JSON.stringify(data),
                                        success: function (data, status, xhr) {
                                            console.log(data);
                                            layer.close(index);
                                            table.reload('test');
                                        },
                                        error: function (xhr, type) {
                                            layer.msg("失败")
                                        }
                                    })
                                }
                            },
                            error: function (xhr, type) {
                                layer.msg("失败")
                            }
                        });
                    },
                    btn2: function(){//layer.alert('aaa',{title:'msg title'});  ////点击取消回调
                        // layer.msg('bbb');//layer.closeAll();
                    }
                });
                break;

        };
    });
    table.on("tool(test)",function (obj) {
        // var checkStatus = table.checkStatus(obj.config.id);
        var data= obj.data;
        switch(obj.event) {
            case 'edit':
                $("input[name='status']:checked").val(data.status);
                $("input[name='sex']:checked").val(data.sex);
                $("input[name='username']").val(data.username);
                $("input[name='account']").val(data.account);
                $("input[name='password']").val(data.password);
                $("input[name='mobile']").val(data.phone);
                $("input[name='email']").val(data.email);
                $("input[name='address']").val(data.address);
                $("input[name='bank']").val(data.bank);
                $("input[name='bankNum']").val(data.bankNum);
                $("input[name='remarks']").val(data.remarks);
                $("input[name='level']").val(data.level);
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: ['80%','80%']
                    ,shade: 0.8
                    ,id: 'LAY_add' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: $('#lay_add')
                    // ,  content: $("#test"),//支持获取DOM元素
                    ,btn: ['确定', '取消'] //按钮组
                    ,scrollbar: false //屏蔽浏览器滚动条
                    ,yes: function(index){//layer.msg('yes');    //点击确定回调

                        // showToast();
                        var status=$("input[name='status']:checked").val();
                        var sex=$("input[name='sex']:checked").val();
                        if(sex==1){
                            sex="男"
                        }else{
                            sex="女"
                        }
                        var username=$("input[name='username']").val()
                        var account=$("input[name='account']").val()
                        var password=$("input[name='password']").val()
                        var mobile=$("input[name='mobile']").val()
                        var email=$("input[name='email']").val()
                        var address= $("input[name='address']").val()
                        var bank=$("input[name='bank']").val()
                        var bankNum=$("input[name='bankNum']").val()
                        var remarks=$("input[name='remarks']").val()
                        var level=$("input[name='level']").val()

                        //验证电话号码
                        if(mobile==""){
                            layer.msg("请输入电话号码")
                        }
                        else if(password==""){
                            layer.msg("请输入登录密码")
                        }
                        else if(account==""){
                            layer.msg("请输入登录账号")
                        }
                        else if(level==""){
                            level=1;
                        }
                        else if(username==""){
                            layer.msg("请输入用户名")
                        }else{
                            ;
                        }
                        var data2={"status":status,
                            "sex":sex,
                            //登录名
                            "username":username,
                            //账号
                            "account":account,
                            //马甲
                            // "realname":realName,
                            "password":password,
                            "phone":mobile,
                            "email":email,
                            "address":address,
                            "bank":bank,
                            "bankNum":bankNum,
                            "remarks":remarks,
                            "level":level,
                            //    从当前的状态获取
                            "id":data.id,

                        };
                        var accountdata={"account":account}
                        $.ajax({
                            type: 'POST',
                            url: baseURL + "sys/clientUser/accountVail",
                            async: false,
                            // headers: {"token": token},
                            contentType: 'application/json',
                            dataType: 'json',
                            data: JSON.stringify(accountdata),
                            success: function (data, status, xhr) {
                                console.log(data);

                                if(data.code==0){
                                    $.ajax({
                                        type: 'POST',
                                        url: baseURL + "sys/clientUser/update",
                                        async: false,
                                        contentType: 'application/json',
                                        dataType: 'json',
                                        data: JSON.stringify(data),
                                        success: function (data, status, xhr) {
                                            console.log(data);
                                            layer.close(index);
                                            table.reload('test');
                                        },
                                        error: function (xhr, type) {
                                            layer.msg("失败")
                                        }
                                    })
                                }
                            },
                            error: function (xhr, type) {
                                layer.msg("失败")
                            }
                        });
                    },
                    btn2: function(){//layer.alert('aaa',{title:'msg title'});  ////点击取消回调
                        // layer.msg('bbb');//layer.closeAll();
                    }
                });
                break;
            case 'del':
                // var data = checkStatus.data;
                console.log(data);
                // layer.msg(data);
                $.post(baseURL + '/sys/clientUser/delete/'+data.id,
                    function (data) {
                    console.log(data);
                    if(parseInt(data.count)==parseInt("1")){
                        layer.msg('删除成功');
                        table.reload('test');
                    }else{
                        layer.msg('删除失败');
                        table.reload('test');
                    }
                });
                break;
        }


    })
    var tree = layui.tree
        ,layer = layui.layer
        ,util = layui.util
    //按钮事件
    util.event('lay-demo', {
        getChecked: function(othis){
            var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据
            // layer.alert(JSON.stringify(checkedData), {shade:0});
            //阔以再  右边得table上操作 -显示 选中得内容
            // console.log(checkedData);
        }
        ,setChecked: function(){
            // tree.setChecked('demoId1', [12, 16]); //勾选指定节点
        }
        ,addDept:function (e) {

            data = {"name": "新机构","departNum":90,"parentName":vm_right.dept_name, "parentId": vm_right.deptID};
            $.ajax({
                type: 'POST',
                url: baseURL + "sys/dept/save",
                async: false,
                // headers: {"token": token},
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data, status, xhr) {
                    console.log(data);
                    // tree.reload('demoId1', {data: getData()});
                },
                error: function (xhr, type) {
                    layer.msg("失败")
                    tree.reload('demoId1', {data: getData()});
                }
            })

        }
        ,addgroup:function (e) {
            data = {"groupName": "新建组","deptId":vm_right.deptID,"preGroupId":0};
            $.ajax({
                type: 'POST',
                url: baseURL + "sys/group/saveGroup",
                async: false,
                // headers: {"token": token},
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data, status, xhr) {
                    console.log(data);
                    // tree.reload('demoId1', {data: getData()});
                },
                error: function (xhr, type) {
                    layer.msg("失败")
                    tree.reload('demoId1', {data: getData()});
                }
            })
        }
        ,search:function (e) {

        }
        ,

        // reload: function(){
        //     //重载实例
        //     tree.reload('demoId1', {
        //     });
        //
        // }
    });

    var data=getData();
    // console.log(data);
    tree.render({
        elem: '#test9'
        ,data: data
        ,showCheckbox: false  //是否显示复选框
        ,id: 'demoId1'
        ,edit: edit//操作节点的图标
        ,onlyIconControl:false
        ,showSearch:true
        ,nodeAddLevel:1
        ,nodeDelLevel:1
        ,customOperate:false
        ,isJump: false//是否允许点击节点时弹出新窗口跳转
        ,nodeIcon:""
        ,text: {
            defaultNodeName: '新节点' //节点默认名称
            ,none: '无数据' //数据为空时的提示文本
        }
        ,click: function(obj){
            var data = obj.data;  //获取当前点击的节点数据
            if(data.type==0 && Ad_type==1){
                //    组 查看某组的人员信息
                // layer.msg(+name + JSON.stringify(data.title));
                table.reload('test'
                    ,{url: baseURL+'sys/clientUser/listByGroup/'+data.id
                    })

            }else if(data.type==1 && Ad_type==0){
                //超管
                table.reload('test'
                    ,{url: baseURL+'sys/clientUser/listByDept/'+data.id
                    })

            }else{
                layer.msg(+name + JSON.stringify(data.title));
            }
        }

        ,operate: function (obj) {
            var type = obj.type; //得到操作类型：add、edit、del
            var data = obj.data; //得到当前节点的数据
            console.log(obj);
            if (type === 'update') { //修改节点
                if (data.type == 1  && Ad_type ==0 ) {
                    data = {"deptId": data.id, "name": data.title, "parentId": data.pid};
                    $.ajax({
                        type: 'POST',
                        url: baseURL + "sys/dept/update",
                        async: false,
                        // headers: {"token": token},
                        contentType: 'application/json',
                        dataType: 'json',
                        data: JSON.stringify(data),
                        success: function (data, status, xhr) {
                            // console.log(data);
                            tree.reload('demoId1', {data: getData()});
                        },
                        error: function (xhr, type) {
                            // layer.msg("更新失败")
                            tree.reload('demoId1', {data: getData()});
                        }
                    })
                } else  if(data.type == 0&& Ad_type ==1 ){

                    data = {"groupId": data.id, "groupName": data.title, "deptId": data.pid,"preGroupId":0};
                    $.ajax({
                        type: 'POST',
                        url: baseURL + "sys/group/updataGroup",
                        async:false,
                        // headers: {"token": token},
                        contentType: 'application/json',
                        dataType: 'json',
                        data: JSON.stringify(data),
                        success: function (data, status, xhr) {
                            // console.log(data);
                            tree.reload('demoId1', {data: getData()});
                        },
                        error: function (xhr, type) {
                            // layer.msg("更新失败")
                            tree.reload('demoId1', {data: getData()});
                        }
                    })
                }else{
                    layer.msg("更新失败--没有权限")
                }
            } else if (type === 'del') { //删除节点
                if (data.type == 1 && Ad_type == 0 ) {
                    data = {"id": data.id};
                    $.ajax({
                        type: 'POST',
                        url: baseURL + "sys/dept/delete",
                        async: false,
                        // headers: {"token": token},
                        // contentType : 'application/json',
                        contentType : 'application/x-www-form-urlencoded',
                        dataType: 'json',
                        data: data,
                        success: function (data, status, xhr) {
                            // console.log(data);

                            tree.reload('demoId1', {data: getData()});
                        },
                        error: function (xhr, type) {
                            // layer.msg("失败");
                            tree.reload('demoId1', {data: getData()});
                        }
                    })
                }else if(data.type==0 && Ad_type == 1){
                    data = {"id": data.id};
                    $.ajax({
                        type: 'POST',
                        url: baseURL + "sys/group/deleteGroup/" + data.id,
                        async: false,
                        // headers: {"token": token},
                        contentType : 'application/x-www-form-urlencoded',
                        dataType: 'json',
                        data: data,
                        success: function (data, status, xhr) {

                            tree.reload('demoId1', {data: getData()});
                        },
                        error: function (xhr, type) {
                            // layer.msg("失败");
                            tree.reload('demoId1', {data: getData()});
                        }
                    })
                }else{
                    layer.msg("删除失败--没有权限")
                }
            } else if (type === 'add'){
                if(data.type==1 && Ad_type == 0){
                    // 机构
                    var pid= data.id;
                    var title=data.title;
                    data = {"name": "新机构","departNum":90,"parentName":title, "parentId": pid};
                    $.ajax({
                        type: 'POST',
                        url: baseURL + "sys/dept/save",
                        async: false,
                        // headers: {"token": token},
                        contentType: 'application/json',
                        dataType: 'json',
                        data: JSON.stringify(data),
                        success: function (data, status, xhr) {
                            // console.log(data);
                            tree.reload('demoId1', {data: getData()});
                        },
                        error: function (xhr, type) {
                            // layer.msg("失败")
                            tree.reload('demoId1', {data: getData()});
                        }
                    })


                }else if(   data.type==0 && Ad_type == 1){
                    var pid= data.id;
                    data = {"groupName": "新建子组","deptId":vm_right.deptID,"preGroupId":pid};
                    $.ajax({
                        type: 'POST',
                        url: baseURL + "sys/group/saveGroup",
                        async: false,
                        // headers: {"token": token},
                        contentType: 'application/json',
                        dataType: 'json',
                        data: JSON.stringify(data),
                        success: function (data, status, xhr) {
                            // console.log(data);
                            tree.reload('demoId1', {data: getData()});
                        },
                        error: function (xhr, type) {
                            // layer.msg("失败")
                            tree.reload('demoId1', {data: getData()});
                        }
                    })
                }else{
                    layer.msg("没有权限--增加机构")
                }

            } else {
                ;
            }

        },

    });
    $('#search').click(function () {
        var name = $("#searchTree").val(); //搜索值
        var elem = $("#demoId1").find('.layui-tree-txt').css('color', ''); //搜索文本与设置默认颜色
        if (!name) {
            return; //无搜索值返回
        }
        elem.filter(':contains(' + name + ')').css('color', '#FFB800'); //搜索文本并设置标志颜色
        elem.parents('.layui-tree-pack').prev().find('.layui-tree-iconClick').click(); //展开选项

    })
});
//////////以上属于table
function getData() {
    var data = [];
    // System.out.print(userInfo.deptId)
    // console.log(userInfo.deptId);
    var id=userInfo.deptId;
    $.ajax({
        url: baseURL+"/sys/dept/deptTree/"+id,    //后台数据请求地址
        type: "post",
        async: false,
        success: function (resut) {
            data = resut.data;
        }
    });
    return data;
}

var vm_from =new Vue({
    el:"from_add",
    data:{
        show_add:true,
    },
    methods:{
    },
    create:function () {

    }
})
