layui.use('form', function () {
    var form = layui.form;

    //监听提交
    form.on('submit(search)', function (data) {
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
layui.formSelects.maxTips('ex', function (id, vals, val, max) {
    //id:   点击select的id
    //vals: 当前select已选中的值
    //val:  当前select点击的值
    //max:  当天多选最大值
    alert("选超了, 选择的值: " + val.value);
});


var vm = new Vue({
    el: '#content',
    data: {},
    methods: {
        batchImport: function () {
            //判断是否选择文件
            var isNull = ("" == $("#inputFile").val()) ? true : false;
            if (!isNull) {
                var form = $("#myForm");
                form.ajaxSubmit({
                    url: baseURL + "sys/clientUser/importExcel",
                    type: "post",
                    headers: {
                        "token": token
                    },
                    dataType: "json",
                    success: function (r) {
                        if (r.code === 0) {
                            layer.msg(r.nums + ' 条数据保存成功！', {time: 2000}, function () {
                                $('#table_list').bootstrapTable("refresh");
                            });
                        }
                    }
                });
                $("#inputFile").attr("value", "");
            } else {
                layer.msg("请选择正确格式的excel文件");
            }
        }
    }
});

$(document).ready(function () {
    //初始化表格,动态从服务器加载数据
    $("#table_list").bootstrapTable({
        method: "POST",  //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded", //必须设置，不然request.getParameter获取不到请求参数
        url: baseURL + "sys/clientUser/list", //获取数据的Servlet地址
        striped: true, //表格显示条纹
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        showColumns: true,
        pagingType: "full_numbers",
        pagination: true, //启动分页
        pageSize: 100,//每页显示的记录数
        pageNumber: 1,  //当前第几页
        pageList: [100, 100, 150],//记录数可选列表
        search: true, //是否启用查询
        showRefresh: true, //显示刷新按钮
        clickToSelect: true,//是否启用点击选中行
        showExport: true,    //是否显示导出
        exportTypes: ['xls', 'xlsx', 'txt', 'csv'], //可选的导出文件类型
        exportDataType: "all",  //basic', 'all', 'selected'.
        exportOptions: {
            fileName: '人员信息',  //文件名称设置  
            worksheetName: 'sheet1',  //表格工作区名称  
            tableName: '人员信息'
        },
        // detailView: true,  //是否启用详细信息视图
        // detailFormatter: detailFormatter, //详细信息
        cardView: false,//是否显示详细视图
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        sidePagination: "client", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: this.queryParams, //传递参数（*）
        responseHandler: function (res) {  //json数据解析
            return {
                "data": res.data,
                "total": res.total
            };
        },
        columns: [{
            title: "序号",
            field: "empty",
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            title: "姓名",
            field: "username"
        }, {
            title: "账号",
            field: "account"
        }, {
            title: "电话",
            field: "phone"
        }, {
            title: "所在分组",
            field: "group"
        }, {
            title: "单位名称",
            field: "dept"
        }, {
            title: "人员等级",
            field: "level"
        }, {
            title: "性别",
            field: "sex"
        }, {
            title: "状态",
            field: "status",
            formatter: function (value, row, index) {
                if (value == 1)
                    return '<span class="label label-success">正常</span>';
                else
                    return '<span class="label label-warning">禁用</span>';
            }
        }, {
            title: "操作",
            field: "empty",
            formatter: function (value, row, index) {
                var operateHtml = "";
                if (row.status == 1) {
                    operateHtml = operateHtml + '<button class="btn btn-primary btn-xs" type="button" onclick="ban(\'' + row.id + '\')"><i class="fa fa-ban"></i>&nbsp;禁用</button> &nbsp;';
                } else {
                    operateHtml = operateHtml + '<button class="btn btn-primary btn-xs" type="button" onclick="use(\'' + row.id + '\')"><i class="fa fa-check"></i>&nbsp;激活</button> &nbsp;';
                }
                operateHtml = operateHtml + '<button class="btn btn-success btn-xs" type="button" onclick="show(\'' + row.id + '\')"><i class="fa fa-eye"></i>&nbsp;查看</button> &nbsp;';
                operateHtml = operateHtml + '<button class="btn  btn-info btn-xs" type="button" onclick="edit(\'' + row.id + '\')"><i class="fa fa-pencil"></i>&nbsp;修改</button> &nbsp;';
                operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
                return operateHtml;
            }
        }]
    });
    this.queryParams = function (params) {  //得到查询的参数
        var params = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.pageSize,   //页面大小
            pageNumber: (params.pageNumber - 1) * params.pageSize,
            searchText: params.searchText
        };
        return params;
    };
    return this;
});


/**
 * 详细信息
 * @param index
 * @param row
 * @returns {string}
 */
function detailFormatter(index, row) {
    var html = [];
    html.push('<p>' +
        '<b>更新时间 : </b> ' + (row.updateTime == null ? '' : row.updateTime) +
        '<b>&emsp;&emsp;&emsp;最近一次登录时间 : </b> ' + (row.loginTime == null ? '' : row.loginTime) +
        '<b>&emsp;&emsp;&emsp;备注 : </b> ' + (row.remarks == null ? '' : row.loginTime) +
        '</p>');
    return html.join('');
}


/**
 *禁用
 * @param id
 */
function ban(id) {
    layer.confirm('确定禁用改用户吗?', {icon: 5, title: '提示'}, function (index) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseURL + "sys/clientUser/ban/" + id,
            success: function (r) {
                if (r.code === 0) {
                    layer.msg('禁用成功', {time: 1500}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            }
        });
    });
}

/**
 *激活
 * @param id
 */
function use(id) {
    layer.confirm('确定激活该用户吗?', {icon: 1, title: '提示'}, function (index) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseURL + "sys/clientUser/use/" + id,
            success: function (r) {
                if (r.code === 0) {
                    layer.msg('激活成功', {time: 1500}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            }
        });
    });
}

/**
 * 数据删除
 * @param id
 */
function del(id) {
    layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseURL + "sys/clientUser/delete/" + id,
            success: function (r) {
                if (r.code === 0) {
                    layer.msg('删除成功', {time: 1500}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            }
        });
    });
}
