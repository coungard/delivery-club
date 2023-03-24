package com.coungard.entity;

import com.coungard.model.ParcelType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@Entity
@Table(name = "parcel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Parcel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double weight;
  @Enumerated(EnumType.STRING)
  private ParcelType type;
  @ManyToOne()
  @JoinColumn(name = "delivery_order_id")
  @ToString.Exclude
  private DeliveryOrder deliveryOrder;

  private String createdBy;
  private LocalDateTime createdDate;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;
}
