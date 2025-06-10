var textName, textAddress, textProvince, textPhoneNumber, textEmail, textareaNote, listProductTotal, productTotalTemp, tempSumTotal, promoPrice, sumTotal, listCoupon , couponTemp, textCoupon, btnOrder, btnViewBill, viewInforBill, tableChuyenKhoan, btnCheckCoupon;
var arrCart = [];
var formBill = null;
$(function() {
    textName = $("#name");
    textAddress = $("#address");
    textProvince = $("#province");
    textPhoneNumber = $("#phone-number");
    textEmail = $("#email");
    textareaNote = $("#note");
    listProductTotal = $("#list-product-total");
    productTotalTemp = $("#product-total-temp");
    tempSumTotal = $("#temp-total-price");
    promoPrice = $("#promo-price");
    sumTotal = $("#sum-cost");
    listCoupon = $("#list-coupon");
    couponTemp = $("#coupon-temp");
    textCoupon = $("#text-coupon");
    btnOrder = $("#btn-order");
    btnViewBill = $("#btn-view-bill");
    viewInforBill = $("#view-infor-bill");
    tableChuyenKhoan = $("#table-chuyen-khoan");
    btnCheckCoupon = $("#btn-check-coupon");
    viewListProductTotal();
    viewListCouponsGobal();
    clickOrder();
    clickCheckCoupon();
})

async function viewListProductTotal() {
    let cart = getCartLocalStorage();
    if(cart && cart.length > 0) {
        let listId = cart.map(data => data.id);
        await productFindByIdsAndProperties(listId).then(rs => {
            if(rs) {
                arrCart = rs.map((data, index) => {
                    for(let j = 0; j < cart.length; j++) {
                        if(data.id == cart[j].id) {
                            data.number = cart[j].number;
                            return data;
                        }
                    }
                })
            }
        }).catch(err => {
            console.log(err);
            alertDanger(DANGER_LIST_PRODUCT);
            removeItemLocalStorage("cart");
        })
    }
    renderlistProductTotal();
    runCodeCoupons();
}

function runCodeCoupons() {
    let codeCoupons = getItemSessionStorage("cart-discount");
    if(codeCoupons && codeCoupons.code) {
        couponFindByCodeAndCompany(codeCoupons.code, COMPANY_ID).then(rs => {
            if(rs) {
                runMinusDiscount(rs);
            } else {
                removeItemSessionStorage("cart-discount");
                countCost(arrCart);
            }
        }).catch(err => {
            console.log(err);
            removeItemSessionStorage("cart-discount");
            countCost(arrCart);
        })
    } else {
        countCost(arrCart);
        promoPrice.html("");
    }
}

function runMinusDiscount(coupon) {
    let check = false;
    let minusDiscount = 0;
    let {type, decrease, minimum, maxDiscount} = coupon;
    let totalCost = countCost(arrCart);
    switch (type) {
        case 2:
            if((minimum == 0 || totalCost >= minimum)) {
                minusDiscount = decrease;
            } else {
                minusDiscount = 0;
            }
            break;
        case 3:
            if((minimum == 0 || totalCost >= minimum)) {
                minusDiscount = totalCost * decrease;
                if(minusDiscount > maxDiscount) minusDiscount = maxDiscount;
                // if(maxDiscount != 0 && minusDiscount > maxDiscount) minusDiscount = maxDiscount;
            } else {
                minusDiscount = 0;
            }
            break;
    }
    if(minusDiscount == 0) {
        alertInfo(INFOR_NOT_USE_COUPONS);
    } else {
        check = true;
        sumTotal.html(totalCost == 0 ? "" : viewPriceVND(totalCost - minusDiscount));
        promoPrice.html(`- ${viewPriceVND(minusDiscount)}`);
        alertSuccess(SUCCESS_USE_COUPONS);
    }
    return check;
}

function renderlistProductTotal() {
    let viewListProduct = "";
    if(arrCart && arrCart.length > 0) {
        viewListProduct = arrCart.map(data => {
            let {name, promotions, cost, number} = data;
            let productTotalClone = productTotalTemp.clone();
            productTotalClone.removeClass("d-none");
            productTotalClone.removeAttr("id");
            productTotalClone.find(".name-number-product").html(`${viewField(name)} x ${number}`);
            let {minusPrice} = viewPromotionCostProduct(promotions, cost);
            cost = cost - minusPrice;
            productTotalClone.find(".product-total-price").html(`${viewPriceVND(cost)}`);
            return productTotalClone;
        })
    }
    listProductTotal.html(viewListProduct);
}

function viewListCouponsGobal() {
    couponGetGlobalCompany(COMPANY_ID).then(rs => {
        let listViewCoupon = "";
        listViewCoupon = rs.map(data => {
            let couponClone = couponTemp.clone();
            couponClone.removeClass("d-none");
            couponClone.removeAttr("id");
            couponClone.find(".coupon-name").html(viewField(data.code));
            couponClone.find(".coupon-des").html(`${viewField(data.content)} - với giá trị đơn hàng tối thiểu ${viewPriceVND(data.minimum)} ${data.maxDiscount ? `và giảm tối đa ${viewPriceVND(data.maxDiscount)}`: ""}`);
            return couponClone;
        })
        listCoupon.html(listViewCoupon);
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_COUPONS_GLOBAL);
    })
}


function clickCheckCoupon() {
    btnCheckCoupon.click(function () {
        let code = textCoupon.val();
        if(code.length > 0) {
            couponUseCoupon(code, COMPANY_ID).then(rs => {
                if(rs) {
                    if(runMinusDiscount(rs.coupon)) {
                        setItemSessionStorage("cart-discount",  {code: rs.coupon.code, token: rs.token});
                    }
                } else {
                    alertDanger(DANGER_COUPON);
                }
            }).catch(err => {
                console.log(err);
                alertDanger(DANGER_COUPON);
            })
        } else {
            alertInfo(INFOR_INPUT_COUPON);
        }
    })
}

function checkThongTinTen() {
    let rs = false;
    let size = 50;
    let val = textName.val();
    if (regexTen(val) && checkSize(size,val)) {
        rs = true;
        hiddenError(textName);
    } else viewError(textName,INVALID_NAME);
    return {check : rs, val: val};
}

function checkThongTinSDT() {
    let rs = false;
    let val = textPhoneNumber.val();
    if (regexDienThoai(val)) {
        rs = true;
        hiddenError(textPhoneNumber);
    } else viewError(textPhoneNumber,INVALID_PHONE_NUMBER);
    return {check : rs, val: val};
}

function checkThongTinEmail() {
    let rs = false;
    let val = textEmail.val();
    if (regexEmail(val)) {
        rs = true;
        hiddenError(textEmail);
    } else viewError(textEmail,INVALID_EMAIL);
    return {check : rs, val: val};
}

function checkThongTinDiaChi() {
    let rs = false;
    let size = 100;
    let val = textAddress.val();
    if (regexTen(val) && checkSize(size,val)) {
        rs = true;
        hiddenError(textAddress);
    } else viewError(textAddress,INVALID_ADDRESS);
    return {check : rs, val: val};
}

function checkThongTinTinhThanhPho() {
    let rs = false;
    let size = 255;
    let val = textProvince.val();
    if (regexTen(val) && checkSize(size,val)) {
        rs = true;
        hiddenError(textProvince);
    } else viewError(textProvince,INVALID_PROVINCE);
    return {check : rs, val: val};
}

function checkNote() {
    let rs = false;
    let size = 255;
    let val = textareaNote.val();
    if (checkSize(size,val) || val.length === 0) {
        rs = true;
        hiddenError(textareaNote);
    } else viewError(textareaNote,INVALID_TEXTAREA_NOTE);
    return {check : rs, val: val};
}

function clickOrder() {
    btnOrder.click(function () {
        let {check: cTen,val: vTen} = checkThongTinTen();
        let {check: cSDT,val: vSDT} = checkThongTinSDT();
        let {check: cEmail,val: vEmail} = checkThongTinEmail();
        let {check: cDiaChi,val: vDiaChi} = checkThongTinDiaChi();
        let {check: cProvince,val: vProvince} = checkThongTinTinhThanhPho();
        let {check: cNote,val: vNote} = checkNote();
        if(cTen && cSDT && cEmail && cDiaChi && cProvince && cNote) {
            loadingBtn();
            formBill = {
                companyId: COMPANY_ID,
                customerName : vTen,
                customerPhone : vSDT,
                customerEmail : vEmail,
                customerAddress : vDiaChi + "-" +vProvince,
                noteCSKHl: vNote
            }
            callBillCreate();
        }
    })
}

function callBillCreate() {
    let courseDiscount = getItemSessionStorage('cart-discount');
    if(courseDiscount && courseDiscount.token) {
        couponFindByToken(courseDiscount.token).then(rs => {
            if(rs) {
                let status = $("input[name='payment_method']:checked").val();
                handelCreateBill(rs.code ,status);
            }
        }).catch(err => {
            console.log(err);
            removeItemSessionStorage("cart-discount");
            alertWarning(WARNING_COUPONS, 8000);
            runCodeCoupons();
            textCoupon.val("");
            hiddenLoadingBtn();
        })
    } else {
        let status = $("input[name='payment_method']:checked").val();
        handelCreateBill(null ,status);
    }
}

async function handelCreateBill(coupon, status) {
    let billDetailForms = [];
    let cart = getItemLocalStorage("cart");
    if(cart && cart.length > 0) billDetailForms = billDetailForms.concat(cart.map((data) => {
        return {
            number: data.number - 0,
            productId: data.id,
            properties: []
        }
    }));
    formBill.billDetailForms = billDetailForms;
    formBill.coupon = coupon;
    formBill.status = status;
    await billCreate(formBill).then(rs => {
        if(rs) {
            localStorage.removeItem("cart");
            sessionStorage.removeItem("cart-discount");
            viewNumberCart();
            btnOrder.remove();
            btnCheckCoupon.remove();
            let billDetails = rs.billDetails;
            let listProductText = billDetails.map(data => data.productName + ", ").join("");
            listProductText = listProductText.slice(0, listProductText.length - 3);
            runToast(`Vừa mua ${listProductText}`, `${rs.bill.customerName} - ${rs.bill.customerPhone.slice(0, rs.bill.customerPhone.length - 4) + "xxx"}`);
            let view  = "";
            if(status === "2") {
                view = `Tạo đơn hàng thành công vui lòng giữ liên lạc để nhận được hàng - Mã đơn hàng của bạn là : "<i id="bill-code">${rs.bill.code}</i>" 
                            <button class="btn" id="btn-copied" style="color:#41B5F0;" data-clipboard-target="#bill-code">
                                <i class="fas fa-copy"></i>
                            </button>- Đơn giá: <strong>${viewPriceVND(rs.bill.totalMoney)}</strong>`;
            } else {
                view = `Hãy tiến hành chuyển <strong>${viewPriceVND(rs.bill.totalMoney)}</strong> vào tài khoản ngân hàng sau với nội dung "<i id="bill-code">${rs.bill.code}</i>"
                            <button class="btn" id="btn-copied" style="color:#41B5F0;" data-clipboard-target="#bill-code">
                                <i class="fas fa-copy"></i>
                            </button>
                            để hoàn tất.`;
                bankFindByCompany(COMPANY_ID).then(rs => {
                    if(rs) {
                        let trTemp = tableChuyenKhoan.removeClass("d-none").find("tbody tr");
                        let listTr = rs.map(data => {
                            let trClone = trTemp.clone();
                            trClone.find(".name-bank").html(viewField(data.bank));
                            trClone.find(".account-bank").html(viewField(data.bankNumber));
                            trClone.find(".owner-account-bank").html(viewField(data.account));
                            return trClone;
                        });
                        tableChuyenKhoan.find("tbody").html(listTr);
                    }
                }).catch(err => {
                    console.log(err);
                })
            }
            viewInforBill.prepend(view);
            new ClipboardJS('#btn-copied');
            btnViewBill.removeClass("d-none");
            $("#exampleModalCenter").modal("show");
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_CREATE_BILL);
        hiddenLoadingBtn();
    })
}
