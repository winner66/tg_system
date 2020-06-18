var vm = new Vue({
    el: '#l-login',
    data: {
        username: '',
        password: '',
        error: false
    },
    beforeCreate: function () {
        if (self != top) {
            top.location.href = self.location.href;
        }
    },
    methods: {
        login: function () {
            var data = "username=" + vm.username + "&password=" + vm.password;
            if (vm.username === '' || vm.password === '') {
                layer.msg("信息不能为空!");
                return false;
            }
            $.ajax({
                type: "POST",
                url: baseURL + "sys/login",
                data: data,
                dataType: "json",
                success: function (r) {
                    if (r.code === 0) {
                         var dr = r.userToken;
                         localStorage.setItem("token", dr.token);
                         localStorage.setItem("userInfo", r.userInfo);
                         parent.location.href = 'index.html';
                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        }
    }
});

$('#option li').click(function() {
    $(this).addClass('active').siblings().removeClass('active');
    var a = $(this).index();
    $('#card li:eq(' + a + ')').addClass('active').siblings().removeClass('active');
})

$(".icon_e").click(function() {
    $(".login_count_b").show();
    $(".login_count_a").hide();
})
$(".icon_z").click(function() {
    $(".login_count_a").show();
    $(".login_count_b").hide();
})

$("#btn").click(function() {
    var count = 60;
    //jquery要用setInterval()定时器，因为这里不会调用倒计时函数，要循环执行
    var interval = setInterval(() => {
        if(count == 0) {
            //因为button是<input>元素，所以这里要用.val()方法
            $("#btn").val("重新获取").removeAttr("disabled");
            count = 60;
            //当倒计时结束，这里要清除定时器
            clearTimeout(interval);
        } else {
            $("#btn").val(count + "s后重新获取").attr("disabled", "disabled");
            count--;
        }
    }, 1000);
})
