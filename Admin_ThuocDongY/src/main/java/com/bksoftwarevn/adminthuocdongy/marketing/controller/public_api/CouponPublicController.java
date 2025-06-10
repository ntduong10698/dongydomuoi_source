package com.bksoftwarevn.adminthuocdongy.marketing.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Coupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.TempoCoupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.CouponView;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.TempoCouponRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.service.CouponService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("api/v1/public/coupons")
public class CouponPublicController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private TempoCouponRepo tempoCouponRepo;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> getGlobal(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(couponService.findGlobal(id)
                    .stream().map(CouponView::new));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{code}/company/{com-id}")
    public ResponseEntity<JsonResult> findByCode(@PathVariable(name = "code") String code,
                                                 @PathVariable("com-id") int comId) {
        try {
            return JsonResult.success(couponService.findByCode(code, comId));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/{code}/company/{com-id}")
    public ResponseEntity<JsonResult> useCoupon(@PathVariable(name = "code") String code,
                                                @PathVariable("com-id") int comId) {
        try {
            Coupon coupon = couponService.findByCode(code, comId).orElse(null);
            if (coupon != null) {
                if (coupon.getQuantity() > coupon.getUsed()) {
                    coupon.setUsed(coupon.getUsed() + 1);
                    couponService.save(coupon);
                    TempoCoupon tempoCoupon = new TempoCoupon();
                    tempoCoupon.setCoupon(coupon);
                    tempoCoupon.setToken(RandomStringUtils.randomAlphanumeric(50));
                    while (tempoCouponRepo.checkToken(tempoCoupon.getToken()) > 0)
                        tempoCoupon.setToken(RandomStringUtils.randomAlphanumeric(50));
                    TempoCoupon savedTempo = tempoCouponRepo.save(tempoCoupon);
                    TimerTask task = new TimerTask() {
                        @SneakyThrows
                        @Override
                        public void run() {
                            tempoCouponRepo.deleteById(savedTempo.getId());
                            coupon.setUsed(coupon.getUsed() - 1);
                            couponService.save(coupon);
                        }
                    };
                    new Timer().schedule(task, 30 * 60_000L);
                    return JsonResult.success(tempoCoupon);
                }
                return JsonResult.badRequest("all coupons had been used ");
            }
            return JsonResult.badRequest("code is invalid");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<JsonResult> findByToken(@PathVariable("token") String token) {
        try {
            TempoCoupon tempoCoupon = tempoCouponRepo.findByToken(token).orElse(null);
            if (tempoCoupon != null) {
                tempoCouponRepo.deleteById(tempoCoupon.getId());
                return JsonResult.success(tempoCoupon.getCoupon());
            }
            return JsonResult.badRequest("invalid token");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
