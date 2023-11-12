package com.coungard.entity;

import com.coungard.model.CourierStatus;
import com.coungard.model.CourierType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "courier")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Courier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private CourierType type;
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private CourierStatus status;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;
  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private LocalDateTime createdDate;
  @LastModifiedBy
  @Column(name = "last_modified_by")
  private String lastModifiedBy;
  @LastModifiedDate
  @Column(name = "last_modified_date")
  private LocalDateTime lastModifiedDate;
}
