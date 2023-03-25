package com.coungard.entity;

import com.coungard.model.DeliveryOrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

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
  @Enumerated(EnumType.STRING)
  private DeliveryOrderStatus status;
  @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL)
  private List<Parcel> parcels;
  @Embedded
  private Address destination;

  private String createdBy;
  private LocalDateTime createdDate;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;

  @Data
  @Embeddable
  public static class Address {

    private String city;
    private String district;
    private String receiverName;
    private String receiverSurname;
    private String receiverPhone;
  }
}
