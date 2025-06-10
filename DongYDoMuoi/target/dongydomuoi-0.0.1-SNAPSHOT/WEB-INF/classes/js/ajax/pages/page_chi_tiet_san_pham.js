var imgInforProduct, hrefImgInforProduct, promotionInforProduct, nameInforProduct, previewInforProduct , costInforProduct, statusInforProduct, propertiesInforProduct, quantityInforProduct, modelInforProduct, introductionInforProduct, catalogInforProduct, inputQuantity, btnBuyNow, listProductRelated, productTemp, unitInforProduct, inputDateUse, groupInputQuantity, listImgProduct, tempListImgProduct;
let idPage = null;
$(function() {
    idPage = getPageId();
    imgInforProduct = $(".img-product");
    hrefImgInforProduct = $(".href-img-product");
    promotionInforProduct = $("#pro-infor-product");
    nameInforProduct = $("#name-infor-product");
    costInforProduct = $("#cost-infor-product");
    statusInforProduct = $("#status-infor-product");
    propertiesInforProduct = $("#properties-infor-product");
    // quantityInforProduct = $("#");
    modelInforProduct = $("#model-infor-product");
    introductionInforProduct = $("#introduction-infor-product");
    catalogInforProduct = $("#file-catalog");
    inputQuantity =$("#input-quantity");
    btnBuyNow = $(".buynow");
    listProductRelated = $("#list-product-related");
    productTemp = $("#product-temp");
    previewInforProduct = $("#preview-infor-product");
    unitInforProduct = $("#unit-infor-product");
    inputDateUse = $("#input-date-use");
    groupInputQuantity = $("#group-input-quantity");
    listImgProduct = $(".list-img-product");
    tempListImgProduct = $(".temp-list-img-product");

    getInforProduct();
})

function getInforProduct() {
    if(idPage) {
        productFindById(idPage, true, true, true, true, true, false).then(rs => {
            if(rs) {
                statisticProductIncreseView(idPage);
                let {id, image, name, quantity, model, introduction, preview} = rs.product;
                let {cost} = rs.costs[0];
                let {properties, promotion} = rs;
                let {files} = rs;
                let {id: idCategory, attachment} = rs.categories[0];
                imgInforProduct.attr("src", viewSrcFile(image));
                imgInforProduct.attr("data-zoom-image", viewSrcFile(image));
                imgInforProduct.attr("alt", viewField(name));
                hrefImgInforProduct.attr("href", viewSrcFile(image));
                imgInforProduct.removeClass("d-none");
                viewListImgProduct(files, name);
                unitInforProduct.html(viewField(rs.costs[0].unit));
                let {minusPrice, viewTitleGift, viewDiscount} = viewPromotionCostProduct(promotion, cost);
                //promo
                if(viewDiscount.length > 0) {
                    promotionInforProduct.find(".promo-sale").removeClass("d-none").html(viewDiscount);
                } else {
                    promotionInforProduct.find(".promo-sale").remove();
                }
                if(viewTitleGift.length > 0) {
                    promotionInforProduct.find(".promo-gift").removeClass("d-none").attr("title", viewTitleGift);
                } else {
                    promotionInforProduct.find(".promo-gift").remove();
                }
                runToolTip();
                //end_promo
                nameInforProduct.html(viewField(name));
                previewInforProduct.html(viewField(preview));
                //
                if(cost != 0) {
                    if(minusPrice == 0) {
                        costInforProduct.find("del").remove();
                        costInforProduct.find("span").text(viewPriceVND(cost));
                    } else {
                        costInforProduct.find("del").text(viewPriceVND(cost));
                        costInforProduct.find("span").text(viewPriceVND(cost - minusPrice));
                    }
                    if(quantity > 0) {
                        groupInputQuantity.removeClass("d-none");
                        inputQuantity.attr("max", viewField(quantity));
                        btnBuyNow.attr("onclick", `addToCartHasNumber(${id}, ${quantity})`);
                    } else {
                        groupInputQuantity.before("<strong>Sản phẩm tạm thời hết hàng vui lòng liên hệ để biết thêm thông tin! </strong>")
                        groupInputQuantity.remove();
                        btnBuyNow.remove();
                    }
                } else {
                    costInforProduct.find("del").remove();
                    costInforProduct.find("span").remove();
                    groupInputQuantity.before("<strong>Liên hệ để mua sản phẩm</strong>")
                    groupInputQuantity.remove();
                    btnBuyNow.remove();
                }
                //end cost
                statusInforProduct.html(quantity > 0 ? "Còn hàng" : "Hết hàng");
                viewPropertiesProduct(properties, quantity);
                modelInforProduct.html(viewField(model));
                catalogInforProduct.attr("href", viewSrcFile(attachment));
                introductionInforProduct.html(viewField(introduction));
                //view list product related
                viewProductRelated(idCategory, id);
            }
        }).catch(err => {
            console.log(err);
            alertDanger(DANGER_PRODUCT);
        })
    } else {
        location.href = 404;
    }
}

function viewPropertiesProduct(properties, quantity) {
    let trTmp = propertiesInforProduct.find("tr");
    let listTr = "";
    if(properties) {
        listTr = properties.map(data => {
            if(data.productProperty && data.productProperty.code === "use_day") {
                handelInputQuantityAndDateView(data.value, quantity);
            }
            let trClone = trTmp.clone();
            trClone.find("th").html(viewField(data.productProperty.name));
            trClone.find("td").html(viewField(data.value));
            return trClone;
        })
    }
    propertiesInforProduct.html(listTr).removeClass("d-none");
}

function viewProductRelated(idCategory, idProduct) {
    productFilter(COMPANY_ID, 0, idCategory, 0, "", 0, "date", true, 1, 9).then(rs => {
        if(rs) {
            rs = rs.content.filter(data => data.id != idProduct);
            let listProduct = getListProduct(rs);
            listProductRelated.html(listProduct);
            clickBtnAddCart();
            runProductRelatedCarousel();
            runToolTip();
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_PRODUCT_RELATED);
    })
}

function addToCartHasNumber(id, quantity) {
    let val = inputQuantity.val();
    if(regexNumber(val) && (val >= 1 || val <= quantity)) {
        addToCart(id, val - 0);
    }
}

function handelInputQuantityAndDateView(value, quantity) {
    inputDateUse.attr("min", value);
    inputDateUse.attr("max", value * quantity);
    runInputSpinner();
    inputQuantity.change(function () {
        let val = inputQuantity.val();
        inputDateUse.val(val * value);
    })
    inputDateUse.change(function (){
        let val = inputDateUse.val();
        let residual = val % value;
        let raw = (val - residual) / value;
        inputQuantity.val(residual > 0 ? raw + 1 : raw);
    })
}

function viewListImgProduct(files) {
    let view = "";
    view = files.map(data => {
        let cloneListImgProduct = tempListImgProduct.clone().removeClass("d-none");
        cloneListImgProduct.attr("data-image", viewSrcFile(data.url));
        cloneListImgProduct.attr("data-zoom-image", viewSrcFile(data.url));
        let img = cloneListImgProduct.find("img");
        img.attr("src", viewSrcFile(data.url));
        img.attr("alt", viewField(data.name));
        return cloneListImgProduct;
    })
    listImgProduct.html(view);
    runListProductSlick();
    // runZoomImgProduct();
}