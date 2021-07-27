package com.couponbase.coupon.unit;

import com.couponbase.coupon.model.Coupon;
import com.couponbase.coupon.repository.CouponRepository;
import com.couponbase.coupon.service.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTests
{
    @Mock
    CouponRepository couponRepo;

    @InjectMocks
    CouponService couponService;

    @Test
    void testGetCoupons()
    {
        List<Coupon> couponList= new ArrayList<>();
        Coupon coupon = new Coupon();
        coupon.setCouponId(1);
        coupon.setCouponAmount(100.0);
        coupon.setCouponExpiryDate(LocalDate.of(2021,12,31));
        couponList.add(coupon);
        when(couponRepo.findAll()).thenReturn(couponList);
        List<Coupon> actual = couponService.findAllCoupons();

        assertEquals(couponList, actual);
    }

}
