package com.coungard.repository;

import com.coungard.entity.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {

}
