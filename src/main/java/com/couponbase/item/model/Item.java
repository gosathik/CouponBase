package com.couponbase.item.model;

import com.couponbase.coupon.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;
    private String itemName;
    private double itemPrice;
    //@OneToOne
    //Coupon coupon;
}
