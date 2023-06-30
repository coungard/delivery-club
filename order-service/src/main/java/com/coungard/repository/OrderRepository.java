package com.coungard.repository;

import com.coungard.entity.DeliveryOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {

  @Query("select o from DeliveryOrder o where o.createdBy = :email")
  List<DeliveryOrder> defineAllOrdersByEmail(String email);
}
