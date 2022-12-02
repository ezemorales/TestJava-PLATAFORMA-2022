package com.em.plataformachallenge.entidad;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * Entidad Price que contiene los datos de PVP y tarifa que aplica a un producto de una cadena entre
 * fechas determinadas
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "prices")
@AllArgsConstructor
public class PriceEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Type(type = "org.hibernate.type.UUIDCharType")
  @Column(nullable = false, columnDefinition = "varchar(36)")
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "brand_id")
  private BrandEntity brandId;
  @Column(name = "start_date")
  private LocalDateTime startDate;
  @Column(name = "end_date")
  private LocalDateTime endDate;
  @Column(name = "price_list")
  private Integer priceList;
  @Column(name = "product_id")
  private Long productId;
  private Short priority;
  private Double price;
  private String curr;
}
