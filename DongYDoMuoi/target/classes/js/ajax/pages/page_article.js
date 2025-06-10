let currentPage = 1;
let sizePage = 6;
let tag;
let size = 6;
let idCate = 0;
$(function () {
    localStorage.setItem('isDetail', 'false');
    let url = new URL(location.href);
    tag = url.searchParams.get('tag');
    idCate = url.searchParams.get('id')?url.searchParams.get('id'): 0;
    if (tag !== null) {
        tag = StringHandle.formatBlank(tag);
    } else {
        tag = '';
    }
    LocalStorage.setArticleToLocalStorage(1, '', '');

    setTimeout(function(){
        let articleObject =  JSON.parse(localStorage.getItem('articleObject'));
        let isDoc = localStorage.getItem('isDoc') === 'true';
        if(isDoc){
            $('.tinTuc__title h2').text(`Tài liệu y khoa`);
        }else{
            $('.tinTuc__title h2').text(formatTitle(articleObject.type));
        }
        ArticleController.initElement(currentPage, sizePage, articleObject.type, articleObject.endpointDetail);
        ArticleController.clickShowMoreNews(articleObject.endpointDetail, articleObject.type);
    }, 150);

});
function formatTitle(type){
    switch (type) {
        case 1: return 'Nghiên Cứu';
        case 2: return 'Tuyển dụng';
        case 3: return 'Giới thiệu';
        case 4: return 'Bài thuốc';
        default: return ''
    }
}
let ArticleController = {
    getListTinTuc: async function (page, size, type) {
        let rs;
        rs = await Promise.resolve(newsFilter(idCate, COMPANY_ID, type, "", tag, page, size))
            .then((rs1) => rs1)
            .catch((err) => console.log(err));
        return rs;
    },

    generateTinTucElement: function (object, endpointDetail) {
        let tinTucElement = $("#tinTuc__element").clone();
        tinTucElement.removeClass("d-none").removeAttr("id");
        // tinTucElement.attr('href', `chi-tiet-tin-tuc?id=${object.id}`);
        tinTucElement.attr('href', `${viewAliasArticle(object.alias, endpointDetail, object.id)}`);
        tinTucElement
            .find("div.tinTuc__element--img img")
            .attr({
                "src": viewSrcFile(object.image),
                "alt": object.name
            });
        tinTucElement
            .find("h4.tinTuc__element--name")
            .text(object.name ? object.name : "");
        tinTucElement
            .find("p.tinTuc__element--description")
            .text(object.preview ? object.preview : "");
        tinTucElement.find("p.tinTuc__element--commentCount").text("4 comments");
        tinTucElement.find('.tinTuc__element--date').text(TimeUtils.formatTime(object.creatTime).d);
        tinTucElement.find('.tinTuc__element--month').text(TimeUtils.formatTime(object.creatTime).m);
        return tinTucElement;
    },
    mappingElement: function (listData, endpointDetail) {
        listData.forEach((element) => {
            let templateTinTucElement = ArticleController.generateTinTucElement(element, endpointDetail);
            $("#list-tin-tuc").append(templateTinTucElement);
        });
    },
    initElement: function (page, size, type, endpointDetail) {
        this.getListTinTuc(page, size, type).then(rs => {
            if (rs.totalPages < 2) {
                $("#btn-showMoreNews").hide();
            } else {
                $("#btn-showMoreNews").show().removeClass('d-none');
            }
            rs = rs.content;
            ArticleController.mappingElement(rs, endpointDetail);
        });
    },
    clickShowMoreNews: function (endpointDetail, type) {
        $("#btn-showMoreNews").click(function () {
            currentPage++;
            ArticleController.getListTinTuc(currentPage, sizePage, type).then(rs => {
                if (currentPage < rs.totalPages) {
                    $("#btn-showMoreNews").show();
                } else {
                    $("#btn-showMoreNews").hide();
                }
                rs = rs.content;
                ArticleController.mappingElement(rs, endpointDetail);
            })
        });

    }
}





