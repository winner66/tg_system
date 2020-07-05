window.onload = function() {
    //接受html的传值
    var result;
    var id=window.location.search; //获取url中"?"符后的字串
    if(id.indexOf("?")!=-1){
        result = id.substr(id.indexOf("=")+1);
    }
    var vm = new Vue({
        el: '#app',
        data: {
            name:'',
            //用来接受后台的数据
            sites:'',
            files:'',
            imgs:'',
        },
        created:function(){
            //加载初始化的标签数据
            $.post(baseURL + '/business/comtask/look',{
                'id':result
            },function (data) {
                vm.sites=data;
                //图片文件分开显示
                var file=(data.filename).split(',');
                var img=[];
                for(var i=0;i<file.length;i++){
                    var point=file[i].lastIndexOf(".");
                    var type = file[i].substr(point);
                    if(type==".jpg"||type==".gif"||type==".JPG"||type==".GIF"){
                        img.push(file[i]);
                        file.splice(i,1);
                    }
                }
                vm.files=file;
                vm.imgs=img;
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
        },
        methods: {
            fd:function(src){
                layer.open({
                    type: 2,
                    content: 'http://localhost:8080/tg_system/'+src,
                    area: ['320px', '195px'],
                    maxmin: true
                });
            },
            //返回上一页
            back:function(){
                window.history.go(-1);
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
                s=s+res.msg+',';
                $("#filename").val(s);
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













