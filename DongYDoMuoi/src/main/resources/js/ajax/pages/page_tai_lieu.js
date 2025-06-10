$(function(){
    let listDoc = $('#list-doc');
    // localStorage.setItem('isDoc', `true`);
    let docElement = $('#doc-element');
    init(listDoc, docElement).then();

    let isDoc = localStorage.getItem('isDoc') === 'true';
    if(isDoc){
        $('.tinTuc__title h2').text(`Tài liệu y khoa`);
    }
});

let DocumentController = {
    getListDocument: function() {
        return ajaxGet(`product-service/api/v1/public/documents/company/${COMPANY_ID}`);
    },

    mappingDoc: function(listData, parentSelector,childSelector, callback){
        let template;
        if(listData.length !== 0){
            template = listData.map(function(el, index){
                if(callback){
                    return callback(el, index, childSelector);
                }
            })
        }
        $(parentSelector).append(template)
    }
};

let DocumentTemplate = {
    genTemplate: function(data, index, docSelector){
        let template = $(docSelector).clone().removeClass('d-none').removeAttr('id');
        template.find('.index').text(index + 1);
        template.find('.name').html(`<a href="${viewSrcFile(data.file)}" target="_blank"><img src="icon/folder_page.png">${data.name}</a>`);
        template.find('.link').html(`<a download class="text-center" href="${viewSrcFile(data.file)}">${textToIconFile(data.file)} Tải
                    về</a>`);
        return template;
    }
};

async function init(parentSelector, childSelector){
    let listData;
    listData =  await DocumentController.getListDocument().then(data=>{
        return data;
    });
    DocumentController.mappingDoc(listData, parentSelector, childSelector, DocumentTemplate.genTemplate);

}
