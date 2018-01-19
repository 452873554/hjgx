/**
 * Created by alvin on 2018/1/17.
 */
$(function () {

    var remainingTime = 1800;// 最大不活动秒数
    countDown();
    var countDownInterval = setInterval(countDown, 1000);//1000毫秒

    function countDown() {

        // var expireTime = new Date().getTime() + 3600000;
        // var currentTime = new Date().getTime();
        // var remainingTime = parseInt((expireTime - currentTime) / 1000);

        var minutes = parseInt(remainingTime / 60);
        var second = parseInt(remainingTime % 60);

        //会话有效时间不足5分钟，添加高亮提示
        if(minutes < 5){
            if($("#remainingMinutes").hasClass('label-success')){
                $("#remainingMinutes").removeClass('label-success').addClass('label-danger');
                $("#remainingSecond").removeClass('label-success').addClass('label-danger');
            }
        }

        //分和秒剩余个位数添加0补全
        $("#remainingMinutes").html(minutes / 10 >= 1 ? minutes : '0' + minutes);
        $("#remainingSecond").html(second / 10 >= 1 ? second : '0' + second);

        //刷新时间显示
        if (remainingTime < 0) {
            $("#remainingTimeTip").html("会话已过期，后续操作要求重新登录");
            clearInterval(countDownInterval);
            //倒计时结束
        }
        remainingTime--;
    }

});

