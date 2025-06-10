let cateElementTemplate1;
let medicineElementTemplate1 ;
let categoryBox1 ;
$(function () {
     cateElementTemplate1 = $('#hidden-category-element');
     medicineElementTemplate1 = $('#medicine-element');
     categoryBox1 = $('#category-medicine-box');
    CategoryController.mappingCategory(categoryBox1, cateElementTemplate1, medicineElementTemplate1);
    LocalStorage.changeArticleLocalStorage(BAI_THUOC_TYPE, 'bai-thuoc', 'chi-tiet-bai-thuoc');
    // getListMedicineCate().then(rs=>console.log(rs));
});

let Category = {
    data: {},
    endpoint: '',
    endpointDetail: ''
};
let Medicine = {
    data: {},
    endpointDetail: ''
};
let CategoryController = {
    mappingCategory:  function(categoryBox, cateElementTemplate, medicineElementTemplate){
        getListMedicineCate()
            .then(data=>{
                data.forEach(async function(value){
                    let idCate = value.id;
                    let listTemplateMedicine;
                    let category = {...Category};
                    category.data = value;
                    category.endpoint = 'bai-thuoc-danh-muc';
                    category.endpointDetail = 'chi-tiet-bai-thuoc';

                    listTemplateMedicine = await newsFilter(idCate, COMPANY_ID, BAI_THUOC_TYPE, '', '', 1, 6)
                        .then(listMedicineData=>{
                            let templateMedicine;
                            if(listMedicineData.content.length !== 0){
                                templateMedicine = listMedicineData.content.map(medicineData=> {
                                    let medicine = {...Medicine};
                                    medicine.data = medicineData;
                                    medicine.endpointDetail = 'chi-tiet-bai-thuoc';
                                    return MedicineTemplate.generateMedicineElementTemplate(medicine, medicineElementTemplate);
                                });
                            }
                            return templateMedicine;

                        })
                        .catch(err=>console.log(err));
                    let templateCategory = CategoryMedicineTemplate.generateCategoryTemplate(category, cateElementTemplate, function(){
                        return listTemplateMedicine;
                    });
                    categoryBox.append(templateCategory)
                })
            })
            .catch(err=>console.log(err));
    }

};
let MedicineTemplate = {
    generateMedicineElementTemplate: function (medicine = {...Medicine}, selectorTemplate) {
        let template = $(selectorTemplate).clone().removeAttr('id').removeClass('d-none');
        template.find('a').attr('href', viewAliasArticle(medicine.data.alias, medicine.endpointDetail, medicine.data));
        template.find('img').attr('src', viewSrcFile(medicine.data.image));
        template.find('h4').text(medicine.data.name);
        template.find('p').text(medicine.data.preview);
        return template;
    }
};
let CategoryMedicineTemplate = {
    generateCategoryTemplate: function (category = {...Category}, selectorTemplate, callback) {
        let template = $(selectorTemplate).clone().removeAttr('id').removeClass('d-none');
        template.find('h3').html(`<a href="${category.endpoint}?id=${category.data.id}"> <span>${category.data.name}</span></a>`);
        template.find('.category__link').html(`<a href="${category.endpoint}?id=${category.data.id}">Xem thêm</a>`);
        if(callback){
            let listTemplateMedicine = callback();
            if(typeof listTemplateMedicine !== 'undefined'){
                listTemplateMedicine.forEach(el=>{
                    template.append(el);
                })
            }else{
                template = ``;
            }

        }

        return template;
    }
};

async function getListMedicineCate(){
    // let URL_NEWS_SERIVCE = 'news-service/api/';
    let listMedicineCategory;
    listMedicineCategory = await Promise.resolve(ajaxGet(`${URL_NEWS_SERIVCE}v1/public/categories/news/company/${COMPANY_ID}`))
        .then(data=> data)
        .catch();

    return listMedicineCategory;
}