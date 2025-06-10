var idPage, rootPage, textSearchPage, listProductTemp, productTemp, orderByCategory;
var idProductType = 0;
var idCategory = null;
var sortTypeCategory = "date";
var ascCategory = true;
var numberPageCategory = 1;
var sizePageCategory = 16;
$(function() {
    idPage = getPageId();
    rootPage = $("#root-category").attr("data-root-category");
    let href = location.href;
    let url = new URLSearchParams(href);
    textSearchPage = url.get("search");
    textSearchPage = textSearchPage ? textSearchPage : "";
    let sort = url.get("sort");
    sortTypeCategory = sort ? sort : sortTypeCategory;
    let asc = url.get("asc");
    ascCategory = asc ? asc : ascCategory;
    listProductTemp = $("#list-product-temp");
    productTemp = $("#product-temp");
    orderByCategory= $("#order-by-category");

    setSelectSort(href);
    if(rootPage !== 'null') {
        if(rootPage == 1) {
            idProductType = idPage;
            productTypeFindById(idProductType).then(rs => {
                if(rs) {
                    viewInforCategoryPage(rs.image, rs.name, rs.description);
                }
            }).catch(err => {
                console.log(err);
                alertDanger(DANGER_PRODUCT_TYPE);
            })
        } else {
            console.log(rootPage);
            idCategory = [idPage];
        }
    }
    viewListProduct();
})

function setSelectSort(href) {
    let indexSort = href.indexOf("sort=");
    if(indexSort > -1) {
        orderByCategory.val(href.slice(indexSort, indexSort + 20));
    }
}

function viewInforCategoryPage(img, name, desc) {
    $("#catalog").removeClass("d-none");
    let imgCatalog = $("#img-catalog");
    imgCatalog.attr("src", viewSrcFile(img));
    imgCatalog.attr("alt", viewField(name));
    $("#name-catalog").html(viewField(name));
    $("#des-catalog").html(desc);
}

function viewListProduct() {
    productFilter(COMPANY_ID, idProductType, idCategory, 0, textSearchPage, 0, sortTypeCategory, ascCategory, numberPageCategory, sizePageCategory).then(rs => {
        if(rs) {
            let viewlistProduct = "<div class='text-center w-100'><strong>Không có thông tin sản phẩm!</strong></div>";
            if(rs.totalElements > 0) {
                rs = rs.content;
                if(rs.length < sizePageCategory) removeBtnPage();
                viewBtnPage();
                viewlistProduct = getListProduct(rs);
            } else {
                removeBtnPage();
            }
            listProductTemp.find(" > .list-product").append(viewlistProduct);
            clickBtnAddCart();
            runToolTip();
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_LIST_PRODUCT);
        removeBtnPage();
    }).finally(function () {
        hiddenLoadingBtn();
    })
}

async function loadMoreProduct() {
    //view loading
    loadingBtn();
    numberPageCategory++;
    viewListProduct();
}

function sortListProduct() {
    let orderBy = orderByCategory.val();
    let href = location.href;
    let index = href.indexOf("sort=");
    href = index > -1 ? href.slice(0, index - 1) : href;
    if(href.indexOf("?") > 0) {
        href = `${href}&${orderBy}`;
    } else {
        href = `${href}?${orderBy}`;
    }
    location.href = href;
}

