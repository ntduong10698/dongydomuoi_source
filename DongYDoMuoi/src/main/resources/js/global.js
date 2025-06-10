$(document).ready(function () {
    // $('.banner').slick({
    //     dots: true,
    //     infinite: true,
    // });
    isDoc();
    $(".bars-open").click(function () {
        $("nav .nav").addClass("open");
        $("nav .shadow").addClass("open");
    });
    $(".fa-times").click(function () {
        $("nav .nav").removeClass("open");
        $("nav .shadow").removeClass("open");
    });
    $(".search-icon .search-click").on("click", function(){
        $(".search-icon").toggleClass("show")
    })
    $(document).on("click", function(e){
        var container = $(".search-icon");
        if (!container.is(e.target) && container.has(e.target).length === 0)
        {
            if($(".search-icon").hasClass("show")){
                console.log("has");
                $(".search-icon").removeClass("show")
            };
        }
    })

});
function isDoc(){
    let url = new URL(location.href);
    let path = url.pathname;
    if(path.includes('tai-lieu')){
        localStorage.setItem('isDoc', 'true');
    }else{
        localStorage.setItem('isDoc', 'false');
    };
}