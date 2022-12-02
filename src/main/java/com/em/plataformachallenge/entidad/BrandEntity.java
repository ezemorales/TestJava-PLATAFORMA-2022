package com.em.plataformachallenge.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Brand que contiene los datos de la cadena
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "brands")
@AllArgsConstructor
public class BrandEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  private String name;
}
