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
            bid:'',
            taskreq:'',
            //用来接受后台的数据
            sites:'',
            files:'',
            imgs:'',
            maxid:'',
        },
        created:function(){
            //加载初始化的标签数据
            $.post(baseURL + '/business/comtalk/look',{
                'id':result
            },function (data) {
                vm.sites=data;
                vm.status=data.status;
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
                $.post(baseURL + '/business/comtask/look',{
                    'id':data['taskid']
                },function (res) {
                    $.post(baseURL + '/business/comtag/findname',{
                        'id':res.tag
                    },function (res) {
                        vm.name=res.buComtname;
                    });
                    vm.taskreq=res.taskreq;
                    vm.bid=res.bid;
                });
            });
        },
        mounted:function(){
        },
        methods: {
            stop:function(id){
                layer.prompt({title:'驳回理由'},function(val, index){
                    $.post(baseURL + '/business/comtalk/stop',{
                        'id':id,
                        'reason':val
                    },function (data) {
                        if(data==true){
                            layer.msg('已驳回');
                            setTimeout(location.reload(),2000)
                        }else{
                            layer.msg('驳回失败');
                        }
                    });
                    title:'请输入值',
                        layer.close(index);
                });
            },
            next:function(id){
                $.post(baseURL + '/business/comtalk/maxid',function (res) {
                    if(res>id){
                        id=id+1
                        //接口查询是否是最大的id
                        window.location.href="lookTalk.html?id="+id;
                    }else{
                        layer.msg('已经到最后一个');
                    }
                });
            },
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
                window.location.href="commentSubmitScript.html"
            },
        }
    });
}
//layui 组件
var lay=layui.use(['form', 'layedit', 'element', 'laydate'], function () {
    var form=layui.form;
    var layedit = layui.layedit
    var $ = layui.jquery;
    var element = layui.element;
    var date = layui.laydate;
});













