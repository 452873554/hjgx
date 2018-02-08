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
    // 点击按妞
    var n = document.getElementsByClassName('swiper-pagination-bullet')
    $(n).html('客厅')

});

