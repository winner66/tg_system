function deleteFile(this_file){
    var a=$("#filename").val();
    if(a!=null || a!=""){
        a=a.split(",");
        var sub=$("#preview span").index(this_file);
        a.splice(sub,1)
        console.log(a);
        this_file.remove();
        $("#filename").val(a.toString())
    }
}
window.onload = function() {
    //接受html的传值
    var result;
    var id=window.location.search; //获取url中"?"符后的字串
    if(id.indexOf("?")!=-1){
        result = id.substr(id.indexOf("=")+1);
    }
    //从页面上删除图片
    var vm = new Vue({
        el: '#app',
        data: {
            id:'',
            files:'',
            imgs:'',
            tags:'',
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
            bid:'',
            //初始化input
            filename:'',
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
            $.post(baseURL + '/business/comtask/look',{
                'id':result
            },function (data) {
                vm.id=data.id;
                vm.tags=data.tag;
                vm.taskname=data.taskname;
                vm.taskreq=data.taskreq;
                vm.pertoplimit=data.pertoplimit;
                vm.taskbelimit=data.taskbelimit;
                vm.orgbelimit=data.orgbelimit;
                vm.reward=data.reward;
                vm.titlex=data.titlex;
                vm.titleb=data.titleb;
                vm.hrefx=data.hrefx;
                vm.hrefb=data.hrefb;
                vm.contentx=data.contentx;
                vm.contentb=data.contentb;
                vm.filex=data.filex;
                vm.fileb=data.fileb;
                vm.majiax=data.majiax;
                vm.majiab=data.majiab;
                vm.bid=data.bid;
                if(data.filename!=null || data.filename!=""){
                    vm.filename=data.filename+",";
                }
                $("#date1").val(data.starttime);
                $("#date2").val(data.endtime);
                //图片文件分开显示
                var file=(data.filename).split(',');
                // var img=[];
                // for(var i=0;i<file.length;i++){
                //     var point=file[i].lastIndexOf(".");
                //     var type = file[i].substr(point);
                //     if(type==".jpg"||type==".gif"||type==".JPG"||type==".GIF"){
                //         img.push(file[i]);
                //         file.splice(i,1);
                //     }
                // }
                vm.files=file;
                // vm.imgs=img;
                // console.log(file);
                // console.log(img);
                $.post(baseURL + '/business/comtag/findname',{
                    'id':data['tag']
                },function (res) {
                    vm.name=res;
                });
            });
        },
        mounted:function(){
            //初始化下layui
            lay;
        },
        methods:{
            delete:function(rul){
              console.log(rul);
            },
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
                //获取时间们
                var date1=$("#date1").val();
                var date2=$("#date2").val();
                var filename=$("#filename").val();
                $.getJSON(baseURL + "sys/user/info", function (r) {
                    //获取发布人
                    var fabup=r.user.realName
                    var testEntity={
                    };
                    testEntity.id=vm.id;
                    testEntity.tags=vm.tags;
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
                    testEntity.bid=vm.bid;
                    testEntity.orgtoplimit=vm.orgtoplimit;
                    testEntity.orgbelimit=vm.orgbelimit;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "business/comtask/update",
                        contentType: "application/json",
                        data: JSON.stringify(testEntity),
                        success: function (res) {
                            if(res=="success"){
                                layer.msg('修改成功');
                            }else{
                                layer.msg('修改失败');
                            }
                        }
                    });
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
                var s=',';
                s=$("#filename").val();
                console.log(s)
                s=s+res.msg+',';
                $("#filename").val(s);
                console.log( $("#filename").val())
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













