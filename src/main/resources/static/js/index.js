$('#weather').leoweather({format: '<span id="colock">现在是：<strong>{年}年{月}月{日}日 星期{周} {时}:{分}:{秒}</strong>'});
//生成菜单
var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}, index: 0},
    template: [
        '<li :class="{active: (item.type===0 && index === 0)}">',
        '<a v-if="item.type === 0" href="javascript:;">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul v-if="item.type === 0" class="treeview-menu">',
        '<menu-item :item="item" :index="index" v-for="(item, index) in item.list"></menu-item>',
        '</ul>',
        '<a v-if="item.type === 1" :href="\'#\'+item.url">' ,
        '<i v-if="item.icon != null" :class="item.icon"></i>' ,
        '<i v-else class="fa fa-circle-o"></i> {{item.name}}' ,
        '</a>',
        '</li>'
    ].join('')
});

//iframe自适应
$(window).on('resize', function () {
    var $content = $('.content');
    $content.height($(this).height() - 120);
    $content.find('iframe').each(function () {
        $(this).height($content.height());
    });
}).resize();

//注册菜单组件
Vue.component('menuItem', menuItem);

var vm = new Vue({
    el: '#rrapp',
    data: {
        user: {},
        menuList: {},
        main: "main.html",
        password: '',
        newPassword: '',
        rePassword: '',
        navTitle: "首页"
    },
    methods: {
        getMenuList: function () {
            $.getJSON(baseURL + "sys/menu/nav", function (r) {
                vm.menuList = r.menuList;
                window.permissions = r.permissions;
                //路由
                var router = new Router();
                routerList(router, vm.menuList);
                router.start();
            });
        },
        getUser: function () {
            $.getJSON(baseURL + "sys/user/info", function (r) {
                vm.user = r.user;
            });
        },
        updatePassword: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "修改密码",
                area: ['600px', '320px'],
                shadeClose: false,
                content: jQuery("#passwordLayer"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    var data = "password=" + vm.password + "&newPassword=" + vm.newPassword;
                    if (vm.rePassword == vm.newPassword) {
                        $.ajax({
                            type: "POST",
                            url: baseURL + "sys/user/password",
                            data: data,
                            dataType: "json",
                            success: function (r) {
                                if (r.code == 0) {
                                    layer.close(index);
                                    layer.alert('修改成功，请记住密码哦！', function () {
                                        location.reload();
                                    });
                                } else {
                                    layer.alert(r.msg);
                                }
                            }
                        });
                    } else {
                        layer.msg("两次输入的密码不相同！");
                    }
                }
            });
        },
        logout: function () {
            $.get(baseURL + "lineOut", function (r) {
                if (r.code === 0) {
                    //删除本地token
                    localStorage.removeItem("token");
                    //跳转到登录页面
                    location.href = baseURL + 'login.html';
                }
            });
        }
    },
    created: function () {
        this.getMenuList();
        this.getUser();
    }
});

function routerList(router, menuList) {
    for (var key in menuList) {
        var menu = menuList[key];
        if (menu.type == 0) {
            routerList(router, menu.list);
        } else if (menu.type == 1) {
            router.add('#' + menu.url, function () {
                var url = window.location.hash;

                //替换iframe的url
                vm.main = url.replace('#', '');

                //导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $(".sidebar-menu li").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");

                vm.navTitle = $("a[href='" + url + "']").text();
            });
        }
    }
}
