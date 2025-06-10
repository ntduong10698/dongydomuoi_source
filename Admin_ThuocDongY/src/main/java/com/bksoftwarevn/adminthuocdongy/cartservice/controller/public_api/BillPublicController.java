package com.bksoftwarevn.adminthuocdongy.cartservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.BillDetailForm;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.BillForm;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.Cost;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.PromoForm;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.*;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.BillDetail;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.*;
import com.bksoftwarevn.adminthuocdongy.entities.RestBuilder;
import com.bksoftwarevn.adminthuocdongy.service.impl.RestService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public/bill")
public class BillPublicController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private SourceOrderService sourceOrderService;

    @Autowired
    private RestService restService;

    @Autowired
    private SendMailService sendMailService;

    @PostMapping
    @ApiOperation(value = "create bill", notes = "status: 1-Đã thanh toán, 2-Chờ giao hàng,3-chờ chuyển khoản, 4-Hủy")
    public ResponseEntity<JsonResult> post(@RequestBody BillForm form) {
        try {
            //create bill
            Bill bill = new Bill();
            bill.setCode(RandomStringUtils.randomAlphanumeric(6));
            while (billService.findByCode(bill.getCode()).isPresent())
                bill.setCode(RandomStringUtils.randomAlphanumeric(6));
            bill.setCustomerId(form.getCustomerId());
            bill.setCustomerName(form.getCustomerName());
            bill.setCustomerEmail(form.getCustomerEmail());
            bill.setCustomerPhone(form.getCustomerPhone());
            bill.setCustomerAddress(form.getCustomerAddress());
            bill.setCustomerNote(form.getNoteCSKHl());
            bill.setChecked(false);
            bill.setDeleted(false);
            bill.setStatus(statusService.findById(form.getStatus()).orElse(statusService.findById(3).get()));
            bill.setCreatedTime(new Date());
            bill.setSourceOrder(sourceOrderService.findDefault(form.getCompanyId()).orElse(null));
            bill.setCoName(form.getCompanyName());

            double totalMoney = 0;
            //calculate detail
            List<BillDetail> billDetails = new ArrayList<>();
            for (BillDetailForm billDetailForm : form.getBillDetailForms()) {
                BillDetail billDetail = new BillDetail();
                InputProductRest inputProductRest = new ModelMapper().map(restService.callGet(RestBuilder.build().service("")
                        .uri("api/v1/public/products/" + billDetailForm.getProductId())
                        .param("cost", "true")
                        .param("promotion", "true").param("statistic", "false")), InputProductRest.class);

                Double cost = Objects.requireNonNull(inputProductRest.getCosts()
                        .stream()
                        .filter(c -> c.getId() == billDetailForm.getPriceId()).findFirst()
                        .orElse(inputProductRest.getCosts().stream().filter(Cost::isDefaultCost).findFirst().orElse(null)))
                        .getCost();
                billDetail.setOriginPrice(cost);
                //khuyen mai cho tung san pham
                for (int j = 0; j < inputProductRest.getPromotion().size(); j++) {
                    PromoForm promoForm = inputProductRest.getPromotion().get(j);
                    //1 tang qua , 2 giam tien , 3 phan tram
                    switch (promoForm.getType()) {
                        case 1:
                            break;
                        case 2:
                            cost -= promoForm.getDecrease();
                            break;
                        case 3:
                            cost *= (1 - promoForm.getDecrease());
                            break;
                    }
                }
                billDetail.setPromotion(inputProductRest.getPromotion().stream().map(promoForm -> "Mã : " + promoForm.getId() + " - " + (promoForm.getName() != null ? promoForm.getName() : promoForm.getContent())).collect(Collectors.joining("|")));
                billDetail.setProperties(billDetailForm.getProperties().stream().map(prop -> prop.getProperty() + ":" + prop.getValue()).collect(Collectors.joining("|")));
                billDetail.setProductPrice(cost);
                billDetail.setAmount(billDetailForm.getNumber());
                billDetail.setProductId(billDetailForm.getProductId());
                billDetail.setProductImage(inputProductRest.getProduct().getImage());
                billDetail.setProductName(inputProductRest.getProduct().getName());
                billDetail.setDeleted(false);
                billDetails.add(billDetail);
                totalMoney += billDetail.getAmount() * billDetail.getProductPrice();
            }
            //promo for bill
            double decreaseBill = 0;
            //khuyen mai cho bill - cua he thong;
            List<PromoForm> promos = new ArrayList<>(Arrays.asList(new ModelMapper().map(restService.callGetList(RestBuilder.build().service("")
                    .uri("api/v1/public/promotions/company/" + form.getCompanyId())), PromoForm[].class)));
            // coupon hoa don
            try {
                if (form.getCoupon() != null && form.getCoupon().length() > 0)
                    promos.add(new ModelMapper().map(restService.callGet(RestBuilder.build().service("")
                            .uri("api/v1/public/coupons/" + form.getCoupon() + "/company/" + form.getCompanyId())), PromoForm.class));
            } catch (Exception ignored) {
            }
            //decrease bill
            for (PromoForm promoForm : promos) {
                switch (promoForm.getType()) {
                    case 1:
                        break;
                    case 2:
                        if (totalMoney >= promoForm.getMinimum())
                            decreaseBill += promoForm.getDecrease();
                        break;
                    case 3:
                        double temp = totalMoney * promoForm.getDecrease();
                        if (temp > promoForm.getMaxDiscount())
                            temp = promoForm.getMaxDiscount();
                        decreaseBill += temp;
                        break;
                }
            }
            //set promo bill
            bill.setPromotion(promos.stream().map(promoForm -> "Mã : " + promoForm.getId() + " - " + (promoForm.getName() != null ? promoForm.getName() : promoForm.getContent())).collect(Collectors.joining("|")));
            System.out.println(bill.getPromotion());
            //
            bill.setTotalMoney(Math.ceil((totalMoney - decreaseBill) / 1000) * 1000);
            return billService.save(bill)
                    .map(savedBill -> {
                        billDetails.forEach(billDetail -> billDetail.setBill(bill));
                        billDetailService.saveAll(billDetails);
                        //tru so luong san pham
                        restService.callPut(billDetails.stream().map(detail -> {
                            Decrease decrease = new Decrease();
                            decrease.setNumber(detail.getAmount());
                            decrease.setProductId(detail.getProductId());
                            return decrease;
                        }).collect(Collectors.toList()), RestBuilder.build()
                                .service("")
                                .uri("api/v1/public/products/decrease")
                                .param("password", "Bksoftwarevn"));
                        //Send email

                        Company company = new ModelMapper().map(restService.callGet(RestBuilder.build().service("")
                                .uri("api/v1/public/companies/" + form.getCompanyId())), Company.class);

                        if (bill.getCustomerEmail() != null) {
                            StringBuilder content = new StringBuilder("<h2>Xin chào: " + bill.getCustomerName() + "</h2>");
                            content.append("<h4>Đơn hàng của bạn có mã: ").append(bill.getCode()).append("</h4>");
                            content.append("<h3>Tổng giá trị đơn hàng: ").append(bill.getTotalMoney()).append(" VNĐ").append("</h3>");
                            content.append("<br>");
                            content.append("<table><tr><td>Sản phẩm</td><td>Đơn giá</td><td>Số lượng</td><td>Thành tiền</td></th>");
                            billDetails.forEach(bd -> content.append("<tr>")
                                    .append("<td>" + bd.getProductName()).append("</td>")
                                    .append("<td>" + bd.getProductPrice() + "</td>")
                                    .append("<td>" + bd.getAmount() + "</td>")
                                    .append("<td>" + bd.getAmount() * bd.getProductPrice() + "VNĐ</td></tr>"));
                            content.append("</table>");

                            sendMailService.sendHtmlMail(bill.getCustomerEmail(), company.getNameCompany()+": Đơn hàng đã được xác nhận", content.toString());
                        }
                        //send mail to admin
                        {
                            MailJson mail = MailJson.builder()
                                    .header(company.getNameCompany()+": Bạn có đơn hàng mới")
                                    .content("<h3>Mã đơn hàng " + savedBill.getCode() + "</h3><br>")
                                    .build();
                            restService.callPost(mail, RestBuilder.build()
                                    .service("")
                                    .uri("api/v1/public/email/company/" + form.getCompanyId())
                                    .param("password", "Bksoftwarevn"));
                        }
                        return JsonResult.uploaded(BillResponse.builder().bill(savedBill).billDetails(billDetails.stream().peek(detail -> detail.setBill(null)).collect(Collectors.toList())).build());
                    }).orElse(JsonResult.badRequest("invalid data"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }


}
