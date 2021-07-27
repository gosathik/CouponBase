package com.couponbase.store.model;

import com.couponbase.coupon.model.Coupon;
import com.couponbase.item.model.Item;
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
public class Store
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long storeId;
    private String storeName;
    //@OneToOne
    //Item item;

    //@OneToOne
    //Coupon coupon;
}
