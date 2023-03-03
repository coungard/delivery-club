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
import lombok.With;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "delivery_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class DeliveryOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private DeliveryOrderStatus status;
  private String city;
  private String district;
  private String receiverName;
  private String receiverSurname;
  private String receiverPhone;
  @OneToMany()
  private Set<Parcel> parcels;

  @CreatedBy
  private String createdBy;
  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedBy
  private String lastModifiedBy;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
