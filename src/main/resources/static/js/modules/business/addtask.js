window.onload = function() {
    var vm = new Vue({
        el: '#app',
        data: {
            //任务名
            taskname:'',
            //任务要求
            taskreq:'',
            //个人上限
            pertoplimit:0,
            //任务下限
            taskbelimit:0,
            //机构任务上下限
            orgtoplimit:0,
            orgbelimit:0,
            //奖励设置
            reward:0,
            //高级选项：
            titlex:0,
            titleb:0,
            hrefx:0,
            hrefb:0,
            contentx:0,
            contentb:0,
            filex:0,
            fileb:0,
            majiax:0,
            majiab:0,
            //用来循环输出tag的
            sites:'',
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
            //获取用户信息
            getUser: function () {
                $.getJSON(baseURL + "sys/user/info", function (r) {
                    vm.user = r.user;
                });
            },
            //返回上一页
            back:function(){
                window.history.go(-1);
            },
            //提交注册
            submits:function(){
                //需要新建的tag们
                var value =[];//定义一个数组
                //发布人
                //var value='';
                $('input[name="tags"]:checked').each(function(){//遍历每一个名字为tags的复选框，其中选中的执行函数
                    value.push($(this).val());//将选中的值添加到数组value中
                    //value=value+$(this).val()+',';
                });
                //获取时间们
                var date1=$("#date1").val();
                var date2=$("#date2").val();
                var filename=$("#filename").val();
                $.getJSON(baseURL + "sys/user/info", function (r) {
                    //获取发布人
                    var fabup=r.user.realName
                    var j=0;
                    for(var i=0;i<value.length;i++){
                        var testEntity={
                        };
                        testEntity.tags=value[i];
                        testEntity.fabup=fabup;
                        testEntity.starttime=date1;
                        testEntity.endtime=date2;
                        testEntity.taskname=vm.taskname;
                        testEntity.taskreq=vm.taskreq;
                        testEntity.pertoplimit=vm.pertoplimit;
                        testEntity.taskbelimit=vm.taskbelimit;
                        testEntity.reward=vm.reward;
                        testEntity.titlex=vm.titlex;
                        testEntity.titleb=vm.titleb;
                        testEntity.contentx=vm.contentx;
                        testEntity.contentb=vm.contentb;
                        testEntity.filex=vm.filex;
                        testEntity.fileb=vm.fileb;
                        testEntity.hrefx=vm.hrefx;
                        testEntity.hrefb=vm.hrefb;
                        testEntity.majiax=vm.majiax;
                        testEntity.majiab=vm.majiab;
                        testEntity.filename=filename.substring(0,filename.length-1);
                        testEntity.bid=i;
                        testEntity.orgtoplimit=vm.orgtoplimit;
                        testEntity.orgbelimit=vm.orgbelimit;
                        $.ajax({
                            type: "POST",
                            url: baseURL + "/business/comtask/add",
                            contentType: "application/json",
                            data: JSON.stringify(testEntity),
                            success: function (res) {
                                j++;
                                if(j==value.length){
                                    layer.msg('添加成功');
                                }
                            }
                        });
                    }
                });
            },
        }
    });
}
//layui 组件
var lay=layui.use(['form', 'layedit', 'element', 'laydate','upload'], function () {
    var form=layui.form;
    var layedit = layui.layedit
    var $ = layui.jquery;
    var element = layui.element;
    var date = layui.laydate;
    var upload = layui.upload;
    form.render();
    //多文件列表示例
    var demoListView = $('#demoList')
        ,uploadListIns = upload.render({
        elem: '#testList'
        ,url: baseURL+'/file/uploadFile' //改成您自己的上传接口
        ,accept: 'file'
        ,multiple: true
        ,auto: false
        ,bindAction: '#testListAction'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1024).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                demoListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code==0){ //上传成功
                var s='';
                s=$("#filename").val();
                //console.log(s)
                s=s+res.msg+',';
                $("#filename").val(s);
                //console.log( $("#filename").val())
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
    //laydate组件
    date.render({elem: '#date1',type: 'datetime'});
    date.render({elem: '#date2',type: 'datetime'});
});













