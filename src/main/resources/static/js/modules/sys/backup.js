/**
 * 表格初始化
 */
$(document).ready(function () {
    //初始化表格,动态从服务器加载数据
    $("#table_list").bootstrapTable({
        method: "POST",  //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded", //必须设置，不然request.getParameter获取不到请求参数
        url: baseURL + "backup/recordInfoList", //获取数据的Servlet地址
        striped: true, //表格显示条纹
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        showColumns: true,
        pagingType: "full_numbers",
        pagination: true, //启动分页
        pageSize: 50,//每页显示的记录数
        pageNumber: 1,  //当前第几页
        pageList: [50, 100, 150],//记录数可选列表
        search: true, //是否启用查询
        showRefresh: true, //显示刷新按钮
        clickToSelect: true,//是否启用点击选中行
        showExport: false,    //是否显示导出
        cardView: false,//是否显示详细视图
        detailView: false,  //是否启用详细信息视图
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        sidePagination: "client", //表示服务端请求
        queryParamsType: "undefined",
        queryParams: this.queryParams, //传递参数（*）
        responseHandler: function (res) {  //json数据解析
            tmp = res;
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
            title: "文件名称",
            field: "backupName"
        }, {
            title: "所在主机",
            field: "host"
        }, {
            title: "所在端口",
            field: "port"
        }, {
            title: "备份时间",
            field: "time"
        }, {
            title: "备份位置",
            field: "url"
        }, {
            title: "操作",
            field: "empty",
            formatter: function (value, row, index) {
                var operateHtml = "";
                operateHtml = operateHtml + '<button class="btn btn-success btn-xs" type="button" onclick="databaseReduction(\'' + row.url + '\', \'' + row.backupName + '\')"><i class="fa fa-ban"></i>&nbsp;还原数据</button> &nbsp;';
                operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="deleteRecord(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;删除数据</button> &nbsp;';
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
 * 数据库备份
 */
function databaseBackup() {
    var index = layer.load(2, {
        shade: [0.2, '#b8b8b8'] //0.5透明度的白色背景
    });
    $.ajax({
        url: baseURL + "backup/databaseExport",
        type: "post",
        datatype: "json",
        success: function (r) {
            if (r.code === 0) {
                layer.msg('数据库备份成功');
                $('#table_list').bootstrapTable("refresh");
                layer.close(index);
            } else {
                layer.msg(r.msg);
            }
        }
    })
}

/**
 * 数据库还原
 */
function databaseReduction(url, name) {
    layer.msg("该功能暂停使用");
    // layer.confirm('确定还原该备份吗?', {icon: 2, title: '提示'}, function (index) {
    //     $.ajax({
    //         type: "POST",
    //         dataType: "json",
    //         data: {
    //             location: url,
    //             name: name
    //         },
    //         url: baseURL + "backup/reduction",
    //         success: function (r) {
    //             if (r.code === 0) {
    //                 layer.msg('还原成功', {time: 1500}, function () {
    //                     $('#table_list').bootstrapTable("refresh");
    //                     layer.close(index);
    //                 });
    //             }
    //         }
    //     });
    // });
}

/**
 * 删除数据
 * @param id
 */
function deleteRecord(id) {
    layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseURL + "backup/delete/" + id,
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