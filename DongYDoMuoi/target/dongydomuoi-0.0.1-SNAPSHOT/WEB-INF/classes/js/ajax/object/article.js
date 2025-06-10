let Article = {
    data: {},
    endpoint: 'string',
    endpointDetail: 'string'
};

// function Recruitment() {
//     Article.call(this);
//     this.returnLink = 'chi-tiet-tuyen-dung';
// }


let ArticleController1 = {
    currentPage: 1,
    size: 6,
    tag: '',
    type: 1,

    getListArticle: async function (page, size, type) {
        let rs;
        rs = await Promise.resolve(newsFilter(0, COMPANY_ID, type, "", tag, page, size))
            .then((rs1) => rs1)
            .catch((err) => console.log(err));
        return rs;
    },
    //callback trả về template tương ứng từng web
    generateArticleElement: function (data = {...Article}, selector, callback) {
        if (callback) {
            // data and parent of template
            let template = callback(data, selector);
            return template;
        }
        return null;
    },

    appendListElements: function (data = {...Article}, parentHiddenElementSelector, hiddenElementSelector, callback) {
            let templateTinTucElement = this.generateArticleElement(data, hiddenElementSelector, callback);
            $(parentHiddenElementSelector).append(templateTinTucElement);
    },

    // initElement: function (hiddenElementSelector, clickBtnSelector, page, size, callback) {
    //         let data = callback(page, size);
    //         if (data.totalPages < 2) {
    //             $(clickBtnSelector).hide();
    //         } else {
    //             $(clickBtnSelector).show();
    //         }
    //         data = data.content;
    //         ArticleController.appendListElements(rs, hiddenElementSelector, callback);
    //
    // },

    clickShowMoreNews: function (parentHiddenElementSelector,hiddenElementSelector, showMoreBtn, article = {...Article}, callback) {

        $(showMoreBtn).click(function () {
            ArticleController1.currentPage++;
            newsFilter(0, COMPANY_ID, ArticleController1.type, "", '', ArticleController1.currentPage, ArticleController1.size)
                .then(data=>{
                    console.log(data);
                    if (ArticleController1.currentPage < data.totalPages) {
                        $(showMoreBtn).show();
                    } else {
                        $(showMoreBtn).hide();
                    }

                    let listData = data.content;
                    if(listData.length !== 0){
                        listData.forEach(el=>{
                            article.data = el;
                            console.log(article);
                            ArticleController1.appendListElements(article, parentHiddenElementSelector, hiddenElementSelector, callback)
                        })
                    }
                    // console.log(listData);
                })
                .catch(err=>console.log(err));


        });

    }
};


let ArticleDetailController = {
    handleTag: function (data, endpoint) {
        if (!(data === '' || data === null)) {
            let listTags = data.split("|");
            let templateTags = listTags.map(data => {
                return `<a href="${endpoint}?tag=${data}"  style="margin-right: 4px;color: white;font-size: 1.3rem;display: inline-block;background-color: var(--main-color);padding: 2px 7px;border-radius: 4px;text-transform: lowercase;">${data}</a>`
            }).join('');
            return templateTags;
        }
        return '';
    },
    //data = article.data
    mapTitleAndTag: function (article) {
        $('.tinTuc__title--tagsTinTuc').html(ArticleDetailController.handleTag(article.data.tag, article.endpoint));
        $(".tinTuc__title h2").text(article.data.name);
    },
    //tham so data = Article
    generateArticle: function (article, callback) {
        if (callback) {
            callback(article);
        }

        let templateArticle = $('#hidden-chiTietTinTuc').clone().removeClass("d-none");
        templateArticle.find('.tinTuc__element--img img').attr({
            'src': viewSrcFile(article.data.image),
            'alt': article.data.name
        });
        templateArticle.find('.tinTuc__title--autho').html(`${TimeUtils.formatTime(article.data.creatTime).d} tháng ${TimeUtils.formatTime(article.data.creatTime).mInNumber} ${article.data.author.length !== 0 ? ('- bởi ' + article.data.author) : ''}`);
        templateArticle.find('.tinTuc__contentTinTuc').html(article.data.content);
        templateArticle.find('.tinTuc__previewTinTuc').text(article.data.preview);
        return templateArticle;
    },

    mappingDetailArticle: function (article, parentSelector) {
        $(parentSelector).prepend(ArticleDetailController.generateArticle(article, ArticleDetailController.mapTitleAndTag(article)))
    },

    increaseView: function (id) {
        let uri = `news-service/api/v1/public/statistic/news/${id}/view`;
        return ajaxPut(uri);
    },
};

let TemplateArticle = {
    //trong phan bai viet (load dsach bai viet)
    generateTemplateArticle: function (article = {...Article}, selector) {
        let template = $(selector).clone().removeAttr('id').removeClass('d-none');
        template.removeClass("d-none").removeAttr("id");
        // tinTucElement.attr('href', `chi-tiet-tin-tuc?id=${object.id}`);
        template.attr('href', viewAliasArticle(article.data.alias, article.endpointDetail, article.data.id));
        template
            .find(".article__element--img img")
            .attr("src", viewSrcFile(article.data.image));
        template
            .find(".article__element--name")
            .text(article.data.name ? article.data.name : "");
        template
            .find(".article__element--description")
            .text(article.data.preview ? article.data.preview : "");
        template.find('.article__element--date').text(TimeUtils.formatTime(article.data.creatTime).d);
        template.find('.article__element--month').text(TimeUtils.formatTime(article.data.creatTime).m);
        return template;
    }
}

