var textNameCompany, textSlogan, linkEmailCompany, textEmailCompany, linkFacebookCompany, linkYoutubeCompany, linkZaloCompany, descriptionCompnay, iframeAddressCompany,
    textAddressCompany, linkPhoneCompany, linkPhoneCompany2, textPhoneCompany, textPhoneCompany2, selectCategorySearch, textSearch, textWorkingTime, navCategory, liNavTemp, navNTemp;
$(function () {
    textNameCompany = $(".text-name-company");
    textSlogan = $(".text-solgan");
    linkEmailCompany = $(".link-email-company");
    textEmailCompany = $(".text-email-company");
    linkFacebookCompany = $(".link-facebook-company");
    linkYoutubeCompany = $(".link-youtube-company");
    linkZaloCompany = $(".link-zalo-company");
    descriptionCompnay = $(".description-compnay");
    iframeAddressCompany = $(".iframe-address-company");
    textAddressCompany = $(".text-address-company");
    linkPhoneCompany = $(".link-phone-comany");
    linkPhoneCompany2 = $(".link-phone-comany2");
    textPhoneCompany = $(".text-phone-company");
    textPhoneCompany2 = $(".text-phone-company2");
    textWorkingTime = $(".text-working-time");
    navCategory = $("#nav-category");
    liNavTemp = $("#li-nav-temp");
    navNTemp = liNavTemp.find(".class-n");

    selectCategorySearch = $("#cars");
    textSearch = $("#text-search");

    viewSocialCompany();
    viewBackGroundHeader();
    activeMenuMain();
    viewNumberCart();
    keypressEnterInputSearchProduct();
    viewNavAndSelectCategorySearch();
    viewToastBuyProduct();
})

//HEADER
function viewBackGroundHeader() {
    contentFindByCompany(COMPANY_ID, "background-header").then(rs => {
        if(rs[0] && rs[0].partDetails) {
            rs = rs[0].partDetails[0];
            $("header").css("background-image", `url("${viewSrcFile(rs.url)}"`);
        }
    }).catch(err => {
        console.log(err);
    })
}

function viewSocialCompany() {
    companyFindById(COMPANY_ID).then(rs => {
        if(rs) {
            let {address, description, email, logo, map, nameCompany, website, slogan, workingTime} = rs;
            textNameCompany.html(viewField(nameCompany));
            textSlogan.html(viewField(slogan));
            linkEmailCompany.attr("href", email ? `mailto:${email}` : "");
            textEmailCompany.html(viewField(email));
            descriptionCompnay.html(viewField(description));
            iframeAddressCompany.attr("src", viewField(map));
            textAddressCompany.html(viewField(address));
            textWorkingTime.html(viewField(workingTime));
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_INFOR_COMPANY);
    })
    infoFindByCompany(COMPANY_ID).then(rs => {
        if(rs) {
            let arrDienThoai = rs.filter(data => data.type === 1 );
            let arrFacebook = rs.filter(data => data.type === 2);
            let arrYoutube = rs.filter(data => data.type === 4);
            let arrZalo = rs.filter(data => data.type === 9);
            linkFacebookCompany.attr("href", viewField(arrFacebook[0].value));
            linkYoutubeCompany.attr("href", viewField(arrYoutube[0].value));
            linkZaloCompany.attr("href", viewField(arrZalo[0].value));
            //PHONE NUMBER
            let valuePhoneNumber = arrDienThoai[0] ? arrDienThoai[0].value : null;
            let valuePhoneNumber2 = arrDienThoai[1] ? arrDienThoai[1].value : null;
            linkPhoneCompany.attr("href", valuePhoneNumber ? `tel:${valuePhoneNumber}` : "");
            linkPhoneCompany2.attr("href", valuePhoneNumber ? `tel:${valuePhoneNumber2}` : "");
            textPhoneCompany.text(viewField(valuePhoneNumber));
            textPhoneCompany2.text(viewField(valuePhoneNumber2));
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_INFOR_COMPANY);
    })
}

function searchProduct() {
    let linkProductType = selectCategorySearch.val();
    let search = textSearch.val();
    location.href  = `${linkProductType}&search=${search}`;
}

function keypressEnterInputSearchProduct() {
    textSearch.on('keyup', function (e) {
        if (e.key === 'Enter' || e.keyCode === 13) {
            searchProduct();
        }
    });
}

//END_HEADER

//FOOTER
//END_FOOTER

//ActiveMenuMain
function activeMenuMain() {
    let {pathname} = location;
    if(pathname == "/") pathname = "/trang-chu";
    $("#nav-main li").removeClass("active");
    $(`#nav-main li[data-active='${pathname.slice(1)}']`).addClass("active");
}

async function viewNavAndSelectCategorySearch() {
    await productTypeFindByCompany(COMPANY_ID).then(rs => {
        if(rs) {
            let optionTemp = selectCategorySearch.find("option");
            rs.map(data => {
                let liNavTempClone = liNavTemp.clone();
                liNavTempClone.removeClass("d-none");
                liNavTempClone.find(".nav-href").attr("href", viewAliasProductType(data.alias, data.id));
                let imgLiNav = liNavTempClone.find("img");
                imgLiNav.attr("src", viewSrcFile(data.icon));
                imgLiNav.attr("alt", viewField(data.name));
                liNavTempClone.find(".nav-href").attr("data-id", data.id);
                liNavTempClone.find(".nav-text").html(viewField(data.name));
                liNavTempClone.find(".nav1").attr("data-id", data.id);
                liNavTempClone.removeAttr("id");
                //add html
                liNavTemp.before(liNavTempClone);
                //add Nav1
                viewNav1(data.id);
                //viewSelectProductTypeSearch
                let optionClone = optionTemp.clone();
                optionClone.removeAttr("selected");
                optionClone.attr("value", viewAliasProductType(null, data.id));
                optionClone.html(viewField(data.name));
                selectCategorySearch.append(optionClone);
            })
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_PRODUCT_TYPE);
    })
}

function viewNav1(id) {
    categoryProductFindByProductType(id).then(rs => {
        let ulNav1 = $(`.nav1[data-id=${id}]`);
        let liNav1 = ulNav1.find(" > li");
        if(rs && rs.length > 0) {
            ulNav1.removeClass("d-none");
            rs.map(data => {
                let liNav1Clone = liNav1.clone();
                let nav1Href = liNav1Clone.find(".nav1-href");
                nav1Href.attr("href", viewAliasCategory(data.alias, data.id));
                nav1Href.html(viewField(data.name));
                liNav1Clone.attr("data-id", data.id);
                liNav1Clone.find(".class-n").remove();
                liNav1.before(liNav1Clone);
                viewChildsCategory(data.childs, data.id);
            })
        } else {
            ulNav1.remove();
        }
        liNav1.remove();
    }).catch(err => {
        console.log(err);
    })
}

function viewChildsCategory(listChild, idCategory) {
    if(listChild && listChild.length > 0) {
        let navNClone = navNTemp.clone();
        let liNTemp = navNTemp.find("li");
        let listLi = listChild.map(data => {
            let liClone = liNTemp.clone();
            liClone.attr("data-id", data.id);
            liClone.find("a").attr("href", viewAliasCategory(data.alias, data.id));
            liClone.find("a").html(viewField(data.name));
            return liClone;
        })
        navCategory.find(`li[data-id='${idCategory}']`).append(navNClone.html(listLi));
        listChild.map(data => {
            viewChildsCategory(data.childs, data.id);
        })
    }
}

function viewToastBuyProduct() {
    productFilter(COMPANY_ID, 0, null, 0, "", 0, "sold", false, 1, 4).then(rs => {
        if(rs) {
            rs = rs.content;
            let listNameProduct = rs.map(data => "Vừa Mua " + data.name);
            activeToast(listNameProduct);
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_LIST_PRODUCT_SOLD);
    })
}