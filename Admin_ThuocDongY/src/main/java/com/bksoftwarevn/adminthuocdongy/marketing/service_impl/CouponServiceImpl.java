package com.bksoftwarevn.adminthuocdongy.marketing.service_impl;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Coupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.UserHasCoupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.UserCouponKey;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.CouponRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.UserCouponRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Integer, CouponRepo> implements CouponService {

    @Autowired
    private UserCouponRepo userCouponRepo;

    public CouponServiceImpl(CouponRepo repo) {
        super(repo, "Coupon");
    }

    @Override
    public Page<Coupon> findByCompanyId(int id, String text, Pageable pageable) {
        try {
            return repo.findByCompanyId(id, text, pageable);
        }catch (Exception ex){
            log.error("find Coupon by company err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Coupon> findGlobal(int id) {
        try {
            return repo.findGlobal(id, new Date());
        }catch (Exception ex){
            log.error("find Coupons global err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Coupon> findByUser(int id) {
        try {
            return repo.findByUser(id, new Date());
        }catch (Exception ex){
            log.error("find Coupons by user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<Coupon> findByCode(String code, int comId) {
        try {
            return repo.findDataByCode(code, comId);
        }catch (Exception ex){
            log.error("find Coupon by code err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void addUser(int couponId, List<Integer> userIds) {
        try {
            Coupon coupon = repo.findDataById(couponId).get();
            userIds.forEach(idU -> {
                UserHasCoupon uhc = new UserHasCoupon();
                uhc.setId(new UserCouponKey(idU, couponId));
                uhc.setCoupon(coupon);
                uhc.setUsed(0);
                userCouponRepo.save(uhc);
            });
        }catch (Exception ex){
            log.error("add users to Coupon err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean userUseCoupon(UserCouponKey id) {
        try {
            return userCouponRepo.userCoupon(id) > 0;
        }catch (Exception ex){
            log.error("use Coupon err {0}", ex);
            throw ex;
        }
    }
}
