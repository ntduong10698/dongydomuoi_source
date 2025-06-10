$(function () {
    // let Recruitment = {...Article};
    localStorage.setItem('isDetail', 'true');
    let articleBox = $('#article-box');
    let articleID = getPageId();
    let articleObject = LocalStorage.getObject('articleObject');

    newsFindById(articleID)
        .then(rs=>{
            let article = {...Article};
            article.data = rs;
            article.endpoint = articleObject.endpoint;
            article.endpointDetail = articleObject.endpointDetail;
            let articleDetailController = {...ArticleDetailController};
            articleDetailController.mappingDetailArticle(article, articleBox);
            articleDetailController.increaseView(articleID).then();
        })
        .catch(err=>console.log(err));
});



