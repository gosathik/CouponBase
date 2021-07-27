package com.couponbase.coupon.unit;

import com.couponbase.coupon.controller.CouponController;
import com.couponbase.coupon.model.Coupon;
import com.couponbase.coupon.repository.CouponRepository;
import com.couponbase.coupon.service.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)

public class CouponAPITests
{
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

//    @MockBean
//    CouponRepository couponRepository;

    @MockBean
    CouponService couponService;


    @Test
    void testAddCoupon() throws Exception
    {
        Coupon testDataCoupon = new Coupon(100.0, LocalDate.of(2021,12,31));
        Map<String, Object> requestBody= new HashMap<>();
        requestBody.put("couponAmount",testDataCoupon.getCouponAmount());
        requestBody.put("couponExpiryDate",testDataCoupon.getCouponExpiryDate());

        doAnswer(invocation ->{
            Coupon testResultCoupon=invocation.getArgument(0);
            testResultCoupon.setCouponId(1);
            return testResultCoupon;
        }).when(couponService).addCoupon(isA(Coupon.class));

        mvc.perform(post("/addCoupon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("couponId").value(1))
            .andExpect(jsonPath("couponAmount").value(100.0));
                }
    @Test
    void testGetCoupons() throws Exception
    {
        List<Coupon> couponList= new ArrayList<>();
        Coupon coupon = new Coupon();
        coupon.setCouponId(1);
        coupon.setCouponAmount(100.0);
        coupon.setCouponExpiryDate(LocalDate.of(2021,12,31));
        couponList.add(coupon);
        System.out.println(couponList.get(0));
        when(couponService.findAllCoupons()).thenReturn(couponList);
        mvc.perform(get("/coupons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("$.[0].couponId").value(couponList.get(0).getCouponId()));
    }
    @Test
    void testUpdateCoupon() throws Exception
    {
        Coupon existing = new Coupon(50, LocalDate.of(2021,12,31));
        existing.setCouponId(2);

        doAnswer(invocation -> {
            Coupon inputCoupon = invocation.getArgument(0);
            Long inputIndex = invocation.getArgument(1);
            Coupon result = null;
            if (inputIndex == 2) {
                result = new Coupon(existing.getCouponAmount(), existing.getCouponExpiryDate());
                result.setCouponId(2);
                if (inputCoupon.getCouponAmount() != 0)
                    result.setCouponAmount(inputCoupon.getCouponAmount());
                if (inputCoupon.getCouponExpiryDate() != null)
                    result.setCouponExpiryDate(inputCoupon.getCouponExpiryDate());
            }
            return Optional.ofNullable(result);
        }).when(couponService).updateCoupon(isA(Coupon.class), isA(Long.class));
        Map<String, Object> body1 = new HashMap<>();
        body1.put("couponAmount", 50.0);

        mvc.perform(patch("/coupon/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body1)))
                .andExpect(status().isOk());
    }
}
