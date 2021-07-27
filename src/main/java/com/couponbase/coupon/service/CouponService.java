package com.couponbase.coupon.service;

import com.couponbase.coupon.model.Coupon;
import com.couponbase.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService
{
    @Autowired
    CouponRepository couponRepo;
    public List<Coupon> findAllCoupons()
    {
        return couponRepo.findAll();
    }

    public void addCoupon(Coupon coupon)
    {
        if(coupon != null)
        {
            couponRepo.save(coupon);
        }
    }

    public Optional<Coupon> updateCoupon(Coupon coupon, Long couponId)
    {
        if(coupon.getCouponAmount() == 0 && coupon.getCouponExpiryDate() == null)
        {}
        else if(coupon.getCouponAmount() != 0 && coupon.getCouponExpiryDate() ==null)
        {
            couponRepo.updateCouponAmount(coupon.getCouponAmount(),couponId);
        }
        else if(coupon.getCouponAmount() == 0 && coupon.getCouponExpiryDate() !=null)
        {
            couponRepo.updateCouponExpiryDate(coupon.getCouponExpiryDate(),couponId);
        }
        else
        {
            couponRepo.updateCouponAmountDate(coupon.getCouponAmount(),coupon.getCouponExpiryDate(),couponId);
        }
        return couponRepo.findById(couponId);
    }
}
