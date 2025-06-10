var listProductTemp, productTemp;
$(function (){
    listProductTemp = $("#list-product-temp");
    productTemp = $("#product-temp")
    viewListSanPham();
})

async function viewListSanPham() {
    //SAN PHAM BAN CHAY
    await productFilter(COMPANY_ID, 0, null, 0, "", 0, "sold", false, 1, 4).then(rs => {
        if(rs) {
            rs = rs.content;
            let listProductClone = listProductTemp.clone().removeClass("d-none").removeAttr("id");
            listProductClone.find(".lp-product-type-name").html("Sản phẩm bán chạy");
            listProductClone.find(".lp-product-type-link").attr("href", "danh-muc?id=0&search=&sort=sold&asc=false");
            let imgListProduct = listProductClone.find(".lp-product-type-img");
            imgListProduct.attr("src", "file/icon/icon-bai-thuoc.png");
            imgListProduct.attr("alt", "sản phẩm bán chạy");
            listProductClone.find("#product-temp").remove();
            listProductClone.find(".row").append(getListProduct(rs));
            listProductTemp.before(listProductClone);
            runToolTip();
            clickBtnAddCart();
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_LIST_PRODUCT_SOLD);
    })
    //END SAN PHAM BAN CHAY

    //SAN PHAM PRODUCT TYPE
    productTypeFindByCompany(COMPANY_ID, true).then(rs => {
        if(rs) {
            rs.map(async data => {
                let listProductClone = listProductTemp.clone().removeClass("d-none").removeAttr("id");
                listProductClone.find(".lp-product-type-name").html(viewField(data.name));
                listProductClone.find(".lp-product-type-link").attr("href", viewAliasProductType(data.alias, data.id));
                listProductClone.attr("data-product-type", data.id);
                let imgListProduct = listProductClone.find(".lp-product-type-img");
                imgListProduct.attr("src", viewSrcFile(data.icon));
                imgListProduct.attr("alt", viewField(data.name));
                listProductClone.find("#product-temp").remove();
                listProductTemp.before(listProductClone);
                //call product list
                await productFilter(COMPANY_ID, data.id, categories = null, brand = 0, text = "", status = 0, sortType = "date", asc = false, page = 1, size = 8).then(rs => {
                    if(rs) {
                        rs = rs.content;
                        let listProduct = getListProduct(rs);
                        $(`.product-wrapper[data-product-type='${data.id}'] .row`).append(listProduct);
                        runToolTip();
                        clickBtnAddCart();
                    }
                }).catch(err => {
                    console.log(err);
                    alertDanger(DANGER_PRODUCT_BY_PRODUCT_TYPE + data.name);
                })
                listProductTemp.remove();
            })
        }
    }).catch(err => {
        console.log(err);
    })
    //END SAN PHAM PRODUCT TYPE
}

