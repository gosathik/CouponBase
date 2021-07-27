package com.couponbase.coupon.repository;

import com.couponbase.coupon.model.Coupon;
import com.couponbase.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface CouponRepository extends JpaRepository<Coupon,Long>
{
    @Transactional
    @Modifying
    @Query("Update Coupon c set c.couponAmount = :couponAmount where c.couponId = :couponId")
    void updateCouponAmount(@Param("couponAmount")double couponAmount, @Param("couponId") Long couponId);

    @Transactional
    @Modifying
    @Query("Update Coupon c set c.couponExpiryDate = :couponExpiryDate where c.couponId = :couponId")
    void updateCouponExpiryDate(@Param("couponExpiryDate") LocalDate couponExpiryDate, Long couponId);

    @Transactional
    @Modifying
    @Query("Update Coupon c set c.couponAmount = :couponAmount, c.couponExpiryDate = :couponExpiryDate where c.couponId = :couponId")
    void updateCouponAmountDate(@Param("couponAmount")double couponAmount,@Param("couponExpiryDate") LocalDate couponExpiryDate, Long couponId);
}
