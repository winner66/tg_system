$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/user/list',
        datatype: "json",
        colModel: [
            {label: '用户ID', name: 'id', index: "id", width: 30, key: true},
            {label: '真实姓名', name: 'realName', width: 60},
            {label: '登录账号', name: 'username', width: 70},
            {label: '手机号码', name: 'mobile', width: 70},
            {label: '邮箱', name: 'email', width: 75},
            {label: '性别', name: 'sex', width: 30},
            {label: '单位名称', name: 'company', width: 75},
            {label: '用户身份', name: 'identity', width: 40},
            {label: '创建时间', name: 'createTime', index: "create_time", width: 80},
            {label: '在线状态', name: 'online', width: 40, formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">离线</span>' :
                        '<span class="label label-success">在线</span>';
                }
            }
        ],
        viewrecords: true,
        rowNum: 30,
        rowList: [30, 50, 70],
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            username: null
        },
        showList: true,
        title: null,
        roleList: {},
        user: {
            status: 1,
            deptId: null,
            deptName: null,
            identity: null,

            roleIdList: [2]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            //默认区域管理员
            vm.user = {deptName: null, deptId: null, status: 1, roleIdList: [2]};
            //获取角色信息
            vm.getRoleList();
            vm.getDept();

        },
        getDept: function () {
            //加载部门树
            $.get(baseURL + "sys/dept/list", function (r) {
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if (node != null) {
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
        },
        update: function () {
            var userId = getSelectedRow();
            if (userId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getUser(userId);
            //获取角色信息
            this.getRoleList();
        },
        ban: function () {  //禁用
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }
            layer.confirm('确定禁用该用户吗?', {icon: 4, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/ban",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function (r) {
                        if (r.code === 0) {
                            layer.msg('禁用成功', {time: 1500}, function () {
                                vm.reload();
                                layer.close(index);
                            });
                        }else {
                            layer.msg(r.msg);
                        }
                    }
                });
            });
        },
        use: function () { //激活
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }
            layer.confirm('确定激活该用户吗?', {icon: 1, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/use",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function (r) {
                        if (r.code === 0) {
                            layer.msg('激活成功', {time: 1500}, function () {
                                vm.reload();
                                layer.close(index);
                            });
                        }else {
                            layer.msg(r.msg);
                        }
                    }
                });
            });
        },
        del: function () { //删除
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }
            layer.confirm('确定要删除选中的记录?', {icon: 5, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function (r) {
                        if (r.code == 0) {
                            layer.msg('操作成功', {time: 1500}, function () {
                                vm.reload();
                                layer.close(index);
                            });
                        } else {
                            layer.msg(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            vm.user.roleIdList=[2];
            console.log(vm.user);

            var url = vm.user.id == null ? "sys/user/save" : "sys/user/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function (r) {
                    if (r.code === 0) {
                        layer.msg('操作成功', {time: 1500}, function () {
                            vm.reload();
                        });
                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        },
        getUser: function (userId) {
            $.get(baseURL + "sys/user/info/" + userId, function (r) {
                vm.user = r.user;
                vm.user.password = null;

                vm.getDept();
            });
        },
        getRoleList: function () {
            $.get(baseURL + "sys/role/select", function (r) {
                vm.roleList = r.list;
                console.log(r.list);
            });
        },
        deptTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'username': vm.q.username},
                page: page
            }).trigger("reloadGrid");
        }
    },
    create:function () {


}

});