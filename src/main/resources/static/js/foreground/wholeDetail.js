$(function () {
    // 轮播
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });

    //分页器内容填充
    var items = swiper.pagination.bullets;
    for (i = 0; i < items.length; i++) {
        $(items[i]).html($(".space-name")[i].value);
    }

});

