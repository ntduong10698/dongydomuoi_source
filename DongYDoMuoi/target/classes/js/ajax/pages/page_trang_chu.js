var slideBanner, bannerItemTemp, textDoiNguBacSiTrangChu, doctorsCarousel, doctorItemTemp, listProductTemp, productTemp, customerCarousel, customerItemTemp;

$(function() {
    slideBanner = $("#banner-main");
    bannerItemTemp = $("#banner-item-temp");
    textDoiNguBacSiTrangChu = $("#text-doi-ngu-bac-si-trang-chu");
    doctorsCarousel = $("#doctors-carousel");
    doctorItemTemp = $("#doctor-item-temp");
    customerCarousel = $("#customer-carousel");
    customerItemTemp = $("#customer-item-temp");
    listProductTemp = $("#list-product-temp");
    productTemp = $("#product-temp");


    viewPopup();
    viewSlideBanner();
    viewQuangCao();
    viewGioiThieu();
    viewDoiNguBacSi();
    viewListSanPham();
    viewViSaoChon();
    viewKhachHang();
})

function viewPopup() {
    popupFindShowedCompany(COMPANY_ID).then(rs => {
        if (rs) {
            $("#set-popup").html(`<a href="${rs.link}" id="img-pop-up">
                                    <img src="${viewSrcFile(rs.url)}" alt="${rs.name}">
                                    <div class="close-popup">
                                             [x]
                                    </div>
                                </a>
                                `)
            $(".popup-ad").removeClass("d-none");
            $(".close-popup").click(function () {
                $(".popup-ad").css("display", "none");
                return false;
            });
        }
    }).catch(err => {
        console.log(err);
    })
}

function viewSlideBanner() {
    contentFindByCompany(COMPANY_ID, "slide").then(rs => {
        if(rs[0] && rs[0].partDetails) {
            rs = rs[0].partDetails;
            rs.map(data => {
                let bannerItemClone = bannerItemTemp.clone();
                bannerItemClone.attr("href", viewField(data.link));
                let imgItemClone = bannerItemClone.find("img");
                imgItemClone.attr("src", viewSrcFile(data.url));
                imgItemClone.attr("alt", viewField(data.text));
                bannerItemTemp.before(bannerItemClone);
            })
            bannerItemTemp.remove();
            slideBanner.slick({
                dots: true,
                infinite: true,
            });
        }
    }).catch(err => {
        console.log(err);
    })
}

function viewQuangCao() {
    contentFindByCompany(COMPANY_ID, "quang-cao").then(rs => {
        if(rs[0] && rs[0].partDetails) {
            rs = rs[0].partDetails;
            for (let i = 0; i < 4; i++) {
                let data = rs[i];
                let elementGioiThieu = $(`#img-gioi-thieu-${i + 1}`);
                let imgElementGioiThieu = elementGioiThieu.find("img");
                elementGioiThieu.attr("href", viewField(data.link));
                imgElementGioiThieu.attr("src", viewSrcFile(data.url));
                imgElementGioiThieu.attr("alt", viewSrcFile(data.text));
            }
        }
    }).catch(err => {
        console.log(err);
    })
}

function viewGioiThieu() {
    contentFindByCompany(COMPANY_ID, "gioi-thieu").then(rs => {
        if(rs[0] && rs[0].partDetails) {
            rs = rs[0].partDetails;
            for (let i = 0; i < 7; i++) {
                let data = rs[i];
                let elementGioiThieu = $(`#gioi-thieu-${i + 1}`);
                elementGioiThieu.find(".text-gioi-thieu").html(viewField(data.text));
                elementGioiThieu.find(".href-gioi-thieu").attr("href", data.link);
                let imgElementGioiThieu = elementGioiThieu.find("img").removeClass("d-none");
                imgElementGioiThieu.attr("src", viewSrcFile(data.url));
                imgElementGioiThieu.attr("alt", viewField(data.text));
            }
        }
    }).catch(err => {
        console.log(err);
    })
}

function viewDoiNguBacSi() {
    contentFindByCompany(COMPANY_ID, "doi-ngu-bac-si-trang-chu").then(rs => {
        if(rs[0] && rs[0].partDetails) {
            rs = rs[0].partDetails[0];
            textDoiNguBacSiTrangChu.html(viewField(rs.text));
        }
    }).catch(err => {
        console.log(err);
    })
    lecturerFindByCompany(COMPANY_ID).then(rs => {
        if(rs) {
            let listDoctorItem = rs.map(data => {
                let doctorItemClone = doctorItemTemp.clone().removeClass("d-none").removeAttr("id");
                let imgDoctor = doctorItemClone.find(".doctor-item-img");
                imgDoctor.attr("src", viewSrcFile(data.image));
                imgDoctor.attr("attr", viewField(data.name));
                doctorItemClone.find(".doctor-item-name").html(viewField(data.name));
                doctorItemClone.find(".doctor-item-position").html(viewField(data.position));
                doctorItemClone.find(".doctor-item-des").html(viewField(data.introduction));
                return doctorItemClone;
            })
            doctorsCarousel.html(listDoctorItem);
            runSlickDoctor(doctorsCarousel);
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_LECTURER);
    })
}

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

function viewViSaoChon() {
    let ajaxCall = [
        contentFindByCompany(COMPANY_ID, "why-choose-part-1"),
        contentFindByCompany(COMPANY_ID, "why-choose-part-2"),
        contentFindByCompany(COMPANY_ID, "why-choose-part-3")
    ]
    Promise.all(ajaxCall).then(rs => {
        if(rs) {
            rs.map((data, index) => {
                data = data[0].partDetails;
                if(data) {
                    data.map((data1, index1) => {
                        let elementViSao = $(`#vi-sao-${index + 1}-${index1 + 1}`);
                        elementViSao.find(".text-vi-sao").html(viewField(data1.text));
                        let imgElement = elementViSao.find("img").removeClass("d-none");
                        imgElement.attr("src", viewSrcFile(data1.url));
                        imgElement.attr("alt", viewField(data1.text));
                    })
                }
            })
        }
    }).catch(err => {
        console.log(err);
        console.log(DANGER_INFOR_COMPANY_WHY_CHOOSE);
    })
}

function viewKhachHang() {
    customerFindByCompany(COMPANY_ID).then(rs => {
        if(rs) {
            let listCustomerItem = rs.map(data => {
                let customerItemClone = customerItemTemp.clone().removeClass("d-none").removeAttr("id");
                let imgCustomer = customerItemClone.find(".customer-item-img").removeClass("d-none");
                imgCustomer.attr("src", viewSrcFile(data.image));
                imgCustomer.attr("attr", viewField(data.name));
                customerItemClone.find(".customer-item-name").html(viewField(data.name));
                customerItemClone.find(".customer-item-des").html(viewField(data.feedback));
                return customerItemClone;
            })
            customerCarousel.html(listCustomerItem);
            runSlickCustomer(customerCarousel);
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_FEED_BACK_CUSTOMER);
    })
}
