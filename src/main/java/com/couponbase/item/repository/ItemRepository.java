package com.couponbase.item.repository;

import com.couponbase.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Item,Long>
{
}
