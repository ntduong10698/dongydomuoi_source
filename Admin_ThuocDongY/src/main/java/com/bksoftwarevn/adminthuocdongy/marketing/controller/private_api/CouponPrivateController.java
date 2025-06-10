package com.bksoftwarevn.adminthuocdongy.marketing.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Coupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.CouponView;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.UserCouponKey;
import com.bksoftwarevn.adminthuocdongy.marketing.service.CouponService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/")
public class CouponPrivateController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/coupons/company/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id,
                                               @RequestParam(name = "text", required = false, defaultValue = "") String text,
                                               @RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "size", defaultValue = "6") int size){
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(couponService.findByCompanyId(id, text, pageable));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/coupons/user/{id}")
    public ResponseEntity<JsonResult> findByUserId(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(couponService.findByUser(id).stream().map(CouponView::new));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(couponService.findById(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/coupon/check")
    public ResponseEntity<JsonResult> checkCode(@RequestParam("company") int comId, @RequestParam("code") String code) {
        try {
            if (!couponService.findByCode(code, comId).isPresent())
                return JsonResult.success("ok");
            return JsonResult.badRequest("used");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/coupon")
    public ResponseEntity<JsonResult> upload(@RequestBody Coupon data){
        try {
            data.setDeleted(false);
            if (data.getCode() == null || data.getCode().isEmpty()){
                do {
                    data.setCode(RandomStringUtils.randomAlphanumeric(6));
                }while (couponService.findByCode(data.getCode(), data.getCompanyId()).isPresent());
            }
            return couponService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/coupon")
    public ResponseEntity<JsonResult> update(@RequestBody Coupon data){
        try {
            return couponService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/coupon/{couponId}/user/{id}")
    public ResponseEntity<JsonResult> useCoupon(@PathVariable(name = "id") int id,
                                                @PathVariable(name = "couponId") int couponId){
        try {
            if(couponService.userUseCoupon(new UserCouponKey(id, couponId)))
                return JsonResult.success("used");
            return JsonResult.badRequest("use fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/coupon/{id}/users")
    public ResponseEntity<JsonResult> addUsers(@PathVariable(name = "id") int id,
                                                  @RequestParam(name = "ids") List<Integer> ids){
        try {
            couponService.addUser(id, ids);
            return JsonResult.success("add success");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (couponService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
