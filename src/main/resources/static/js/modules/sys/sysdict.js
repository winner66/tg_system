
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: 0
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
        showList: true,
        title: null,
        editable:false,
        sysDict: {
            parentId: 0,
            parentName: null,
            name: null,
            dictType: 0,
            remarks: null,
            parentName: null
        }
    },
    methods: {
        getDict: function () {
            //加载分类树
            $.get(baseURL + "sys/sysdict/select", function (r) {
                ztree = $.fn.zTree.init($("#dictTree"), setting, r.sysDictList);
                var node = ztree.getNodeByParam("id", vm.sysDict.parentId);
                ztree.selectNode(node);
                vm.sysDict.parentName = node.name;
                vm.sysDict.parentId = node.id;
            })
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.sysDict = {name: null, parentId: 0, parentName:null, dictType: 0};
            vm.getDict();
        },
        update: function (event) {
            var dictId = getDictId();
            if (dictId == null) {
                return;
            }

            $.get(baseURL + "sys/sysdict/info/" + dictId, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.sysDict = r.sysDict;
                vm.editable = true;
                vm.getDict();
            });
        },
        del: function () {
            var dictId = getDictId();
            if (dictId == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/sysdict/delete",
                    data: "id=" + dictId,
                    success: function (r) {
                        if (r.code === 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            if(vm.editable == true)
                vm.editable == false;
            var url = vm.sysDict.id == null ? "sys/sysdict/save" : "sys/sysdict/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.sysDict),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        dictTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择字典类型",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#dictLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择字典类型
                    vm.sysDict.parentId = node[0].id;
                    vm.sysDict.parentName = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.editable = false;
            vm.showList = true;
            Dict.table.refresh();
        }
    }
});
var Dict = {
    id: "dictTable",
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
Dict.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '代码', field: 'code', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {
            title: '类型',
            field: 'dictType',
            align: 'center',
            valign: 'middle',
            sortable: true,
            width: '100px',
            formatter: function (item, index) {
                if (item.dictType === 0) {
                    return '<span class="label label-primary">分组</span>';
                }
                if (item.dictType === 1) {
                    return '<span class="label label-success">成员</span>';
                }
            }
        },
        {title: '备注', field: 'remarks', align: 'center', valign: 'middle', sortable: true, width: '180px'}]
    return columns;
};


function getDictId() {
    var selected = $('#dictTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}


$(function () {
    var colunms = Dict.initColumn();
    var table = new TreeTable(Dict.id, baseURL + "sys/sysdict/list", colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();
    Dict.table = table;
});