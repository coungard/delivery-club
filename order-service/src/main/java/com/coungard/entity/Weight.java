package com.coungard.entity;

import com.coungard.model.ParcelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "weights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parcel_type")
    @Enumerated(EnumType.STRING)

    private ParcelType parcelType;
    private BigDecimal minWeight;
    private BigDecimal maxWeight;
}
