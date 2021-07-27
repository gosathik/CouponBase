package com.couponbase.coupon.controller;

import com.couponbase.coupon.model.Coupon;
import com.couponbase.coupon.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PathVariable;


@RestController

public class CouponController
{
    CouponService couponService;
    public CouponController(CouponService couponService)
    {
        this.couponService=couponService;

    }
    @GetMapping("/coupons")
    public ResponseEntity <Iterable<Coupon>> findAllCoupons()
    {
        return new ResponseEntity(couponService.findAllCoupons(), HttpStatus.OK);
    }
    @PostMapping("/addCoupon")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon)
    {
        couponService.addCoupon(coupon);
        return new ResponseEntity(coupon,HttpStatus.CREATED);
    }

    @PatchMapping("/coupon/{couponId}")
    public ResponseEntity<Coupon>updateCoupon(@RequestBody Coupon coupon, @PathVariable long couponId)
    {
        Coupon resultCoupon = couponService.updateCoupon(coupon, couponId).orElse(null);
        if(resultCoupon != null)
            return new ResponseEntity(resultCoupon, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
