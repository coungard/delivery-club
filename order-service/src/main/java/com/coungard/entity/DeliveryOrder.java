package com.coungard.entity;

import com.coungard.model.DeliveryOrderStatus;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private DeliveryOrderStatus deliveryOrderStatus;
  private String city;
  private String district;
  private String receiverName;
  private String receiveSurname;
  private String receiverPhone;
  @OneToMany(fetch = FetchType.EAGER)
  @Builder.Default
  private Set<Parcel> parcels = new HashSet<>();

  private String createdBy;
  private LocalDateTime createdDate;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;
}
