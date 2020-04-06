$(function () {

    // ------------------------------------------------------ //
    // For demo purposes, can be deleted
    // ------------------------------------------------------ //

    var stylesheet = $('link#theme-stylesheet');
    $( "<link id='new-stylesheet' rel='stylesheet'>" ).insertAfter(stylesheet);
    var alternateColour = $('link#new-stylesheet');

    if ($.cookie("theme_csspath")) {
        alternateColour.attr("href", $.cookie("theme_csspath"));
    }

    $("#colour").change(function () {

        if ($(this).val() !== '') {

            var theme_csspath = 'css/style.' + $(this).val() + '.css';

            alternateColour.attr("href", theme_csspath);

            $.cookie("theme_csspath", theme_csspath, { expires: 365, path: document.URL.substr(0, document.URL.lastIndexOf('/')) });

        }

        return false;
    });


    // =====================================================
    //      NAVBAR
    // =====================================================
    var c, currentScrollTop = 0;
    $(window).on('scroll load', function () {

        if ($(window).scrollTop() >= 100) {
            $('.navbar').addClass('active');
        } else {
            $('.navbar').removeClass('active');
        }

        // Navbar functionality
        var a = $(window).scrollTop(), b = $('.navbar').height();

        currentScrollTop = a;
        if (c < currentScrollTop && a > b + b) {
            $('.navbar').addClass("scrollUp");
        } else if (c > currentScrollTop && !(a <= b)) {
            $('.navbar').removeClass("scrollUp");
        }
        c = currentScrollTop;

    });


    // =====================================================
    //      PREVENTING URL UPDATE ON NAVIGATION LINK
    // =====================================================
    $('.link-scroll').on('click', function (e) {
        var anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $(anchor.attr('href')).offset().top - 100
        }, 1000);

        e.preventDefault();
    });


    // =====================================================
    //      SCROLL SPY
    // =====================================================
    $('body').scrollspy({
        target: '#navbarSupportedContent',
        offset: 80
    });

    // 复合搜索栏
    $('.search-key').on('mouseover', function() {
        $('.keybox').show();
        $(this).addClass('arrow_b');
    }).on('mouseout', function() {
        $(this).removeClass('arrow_b');
        $('.keybox').hide();
    });

    $keyvalue = $(".search-key span").text();
    $('.keybox li').click(function() {
        $('.search-key span').text($(this).text());
        if ($(this).text() != $keyvalue) {
            $('.search-input').val("");
            $keyvalue = $(this).text();
        }
        $('.keybox').hide();
    });

    $(document).keydown(function(event){
        if(event.keyCode==13){
            $(".search-btn").click();
        }
    });
    $('.search-btn').click(function(){

        var keyValue = $('.search-key span').text().replace(/(^\s*)|(\s*$)/g, "");

        if(keyValue == '搜学校'){
            var  name = $('.search-input').val()
            window.open('/web/school/school_search?keyword='+name,'_blank');
        }else if(keyValue == '查专业'){
            window.open('/web/special/pcfindzy?keyword='+$('.search-input').val(),'_blank');
        }else if(keyValue == '招生简章'){
            window.open('/web/news/zhaosheng?keyword='+$('.search-input').val(),'_blank');
        }else if(keyValue == '参考书目'){
            window.open('/web/news/cankao?keyword='+$('.search-input').val(),'_blank');
        }else if(keyValue == '分数线'){
            window.open('/web/special/fenshu?keyword='+$('.search-input').val(),'_blank');
        }else if(keyValue == '实验室'){
            window.open('shiyan.html','_blank');
        }

    });
    //热度排序
    //升降序
    $('.sort').click(function  () {
        //默认降序
        if ($(this).find('img').attr('src') == '/img/px_b.png') {
            //升序
            $(this).find('img').attr('src','/img/px.png');
            $(this).find('span').attr('value','sum(clicks) asc');
            getsublev();
            $('#hide').html(1);
        }else{
            //降序
            $(this).find('img').attr('src','/px_b.png');
            $(this).find('span').attr('value','sum(clicks) desc');
            getsublev();
            $('#hide').html(1);
        };
    });

    $('.result').on('click','.ksyx',function  () {
        if ($(this).parent().parent('.sline').find('.yxshow').css('display') == 'none') {
            $(this).find('img').attr('src','/img/jtx.jpg');
            $(this).parent().parent('.sline').find('.yxshow').show();
            $(this).parent().parent().find('.sline-ul').addClass('active');
        }else{
            $(this).find('img').attr('src','/img/jts.jpg');
            $(this).parent().parent('.sline').find('.yxshow').hide();
            $(this).parent().parent().find('.sline-ul').removeClass('active');
        }
    })
    //ajax
    var getsublev = function(txt) {
        //console.log(111);
        $.ajax({
            url:'/web/special/pcfindzy?format=json',
            type:"POST",
            data:{act:'do',keyword:$('.keyword').html(),type:$.cookie('cds_type1'),root_id:$.cookie('cds_type2'),pid:$.cookie('cds_type3'),page:txt,orderby:$('.sort').find('span').attr('value')},
            dataType:"JSON",
            success: function(data){
                var count = data.data.count;
                var html = '';
                if (count != 0) {
                    $.each(data.data.data, function(commentIndex, comment){
                        html += '<div class="sline clearfix"><div class="sline-ul clearfix active">';
                        html += '<div class="sline-li wid1">['+comment['lev1']+comment['lev2']+comment['lev3']+']'+comment['name']+'</div>';
                        html += '<div class="sline-li wid2">'+comment['lev1']+comment['lev1name']+'</div>';
                        html += '<div class="sline-li wid3">'+comment['lev1']+comment['lev2']+comment['lev2name']+'</div>';
                        html += '<div class="sline-li bm wid4"><a href="https://souky.eol.cn/collect/index">我要报名</a></div><div class="sline-li ksyx wid5"><div><span>开设院校</span><img src="/app/static/scripts/web/pc/img/jtx.jpg"/></div></div></div><div class="yxshow clearfix"><div class="word">推荐院校</div><div class="school clearfix">';

                        if (comment.school != undefined) {
                            $.each(comment.school, function(key, value){
                                html += '<a href="/HomePage/index_'+value['school_id']+'.html">'+value['name']+'</a>';
                            });
                        }

                        html += '</div><div class="all"><a href="/web/special/schoolrecom?specialid='+comment['special_id']+'">查看全部</a></div></div></div>';
                    });
                    $('.content').html('');
                    $('.content').html(html);
                }else{
                    $('.content').html('<h1 class="nodata">暂无搜索结果，<a href="/web/special/pcfindzy">点击返回</a></h1>');
                }
                if( !txt ){
                    yeshubianhua(1,count);
                    var $bottom_page = $('#li_sxx_pageNav .li_bottom_page');
                    $bottom_page.eq(0).css({
                        "background":"#3A9BE0"
                    });
                    $bottom_page.eq(0).find('a').css("color","#fff");
                    pagepanduan();
                }
            }
        });
    }
    var yeshubianhua = function(originPage,pageSum){
        pageSum1 = pageSum
        //底部页数初始化
        $('#li_sxx_pageNav h3 i').eq(0).html(pageSum1);
        $('#li_sxx_pageNav h3 i').eq(1).html(originPage);
        $('#li_sxx_pageNav h3 em').html(Math.ceil(pageSum1/10));
        $('.sum span').html(pageSum1);;
        //首页及总页数
        $('#li_top_page').html('');
        $('#li_top_page').html(1);
        if( Math.ceil(pageSum1/10) == 0 ){
            $('#li_top_num').html(1);
        }else{
            $('#li_top_num').html( Math.ceil(pageSum1/10) );
        }

        if( (Math.ceil(pageSum1/10)) < 7 ){
            if( $('#li_sxx_pageNav h3 i').eq(0).html() == 0 ){
                $('#li_sxx_pageNav h3 em').html(1);
                $('#li_yema').html('');
                $('#li_yema').append('<li class="li_bottom_page"><a href="javascript:;">'+1+'</a></li>');
                return
            }
            $('#li_yema').html('');
            for( let i=1;i<Math.ceil(pageSum1/10)+1;i++ ){
                $('#li_yema').append('<li class="li_bottom_page"><a href="javascript:;">'+i+'</a></li>');
            }
        }else if((Math.ceil(pageSum1/10)) >= 7){
            $('#li_yema').html('');
            for( let j=1;j<9;j++ ){
                $('#li_yema').append('<li class="li_bottom_page"><a href="javascript:;">'+j+'</a></li>');
            }
        }

    }
    // 搜索栏页面切换
    $('.search-btn').click(function(){
        var keyValue = $('.search-key span').text().replace(/(^\s*)|(\s*$)/g, "");
        if(keyValue == '搜学校'){
            var  name = $('.search-input').val()
            window.open('/web/school/school_search?keyword='+name,'_blank');
        }else if(keyValue == '查专业'){
            window.open('/web/special/pcfindzy?keyword='+$('.search-input').val(),'_blank');
        }
    });

    // 搜索栏页面切换
    $('.search-btn').click(function(){
        var keyValue = $('.search-key span').text().replace(/(^\s*)|(\s*$)/g, "");
        if(keyValue == '搜学校'){
            var  name = $('.search-input').val()
            window.open('/web/school/school_search?keyword='+name,'_blank');
        }else if(keyValue == '查专业'){
            window.open('/web/special/pcfindzy?keyword='+$('.search-input').val(),'_blank');
        }
    });
    // 解析地址栏中的参数
    function getUrlParms(para){
        var search=location.search; //页面URL的查询部分字符串
        var arrPara=new Array(); //参数数组。数组单项为包含参数名和参数值的字符串，如“para=value”
        var arrVal=new Array(); //参数值数组。用于存储查找到的参数值

        if(search!=""){
            var index=0;
            search=search.substr(1); //去除开头的“?”
            arrPara=search.split("&");

            for(i in arrPara){
                var paraPre=para+"="; //参数前缀。即参数名+“=”，如“para=”
                if(arrPara[i].indexOf(paraPre)==0&& paraPre.length<arrPara[i].length){
                    arrVal[index]=decodeURI(arrPara[i].substr(paraPre.length)); //顺带URI解码避免出现乱码
                    index++;
                }
            }
        }

        if(arrVal.length==1){
            return arrVal[0];
        }else if(arrVal.length==0){
            return null;
        }else{
            return arrVal;
        }
    }
    $('.sline-li').on('click','.ksyx',function  () {
        if ($(this).parent().parent('.sline').find('.yxshow').css('display') == 'none') {
            $(this).find('img').attr('src','/img/jtx.jpg');
            $(this).parent().parent('.sline').find('.yxshow').show();
            $(this).parent().parent().find('.sline-ul').addClass('active');
        }else{
            $(this).find('img').attr('src','/img/jts.jpg');
            $(this).parent().parent('.sline').find('.yxshow').hide();
            $(this).parent().parent().find('.sline-ul').removeClass('active');
        }
    })

    $("nav.navbar.bootsnav .attr-nav").each(function(){
        $("li.side-menu > a", this).on("click", function(e){
            e.preventDefault();
            $("nav.navbar.bootsnav > .side").toggleClass("on");
            $("body").toggleClass("on-side");
        });
    });
    $(".side .close-side").on("click", function(e){
        e.preventDefault();
        $("nav.navbar.bootsnav > .side").removeClass("on");
        $("body").removeClass("on-side");
    });
});
