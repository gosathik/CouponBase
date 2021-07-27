package com.couponbase.store.respository;

import com.couponbase.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long>
{
}
