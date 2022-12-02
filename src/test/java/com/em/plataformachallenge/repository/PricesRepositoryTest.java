package com.em.plataformachallenge.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.em.plataformachallenge.entidad.BrandEntity;
import com.em.plataformachallenge.entidad.PriceEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Clase que contiene los test de {@link PricesRepository}
 */
@SpringBootTest
public class PricesRepositoryTest {

  @Autowired
  PricesRepository pricesRepository;

  @Test
  public void priceEntityNotFound() {
    Optional<PriceEntity> priceEntity = pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        new BrandEntity(1L, "ZARA"), 1L, LocalDateTime.now(), LocalDateTime.now());
    assertTrue(priceEntity.isEmpty());

  }

  @Test
  public void priceEntityFindFirst() {
    BrandEntity brandEntity = new BrandEntity(1L, "ZARA");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-00:00:00", formatter);

    Optional<PriceEntity> priceEntity = pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandEntity, 35455L, dateTime, dateTime);

    assertTrue(priceEntity.isPresent());
  }

}
