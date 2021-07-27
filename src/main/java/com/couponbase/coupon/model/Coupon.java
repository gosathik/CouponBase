package com.couponbase.coupon.model;

import com.couponbase.item.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long couponId;
    private double couponAmount;
    private LocalDate couponExpiryDate;

    public Coupon(double couponAmount, LocalDate couponExpiryDate) {
        this.couponAmount = couponAmount;
        this.couponExpiryDate = couponExpiryDate;
    }


    //@OneToOne
    //Item item;
}
