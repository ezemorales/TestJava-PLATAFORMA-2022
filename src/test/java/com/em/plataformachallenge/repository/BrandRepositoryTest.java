package com.em.plataformachallenge.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.em.plataformachallenge.entidad.BrandEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Clase que contiene los test de {@link BrandRepository}
 */
@SpringBootTest
public class BrandRepositoryTest {

  @Autowired
  BrandRepository brandRepository;

  @Test
  public void brandEntityNotFound() {
    Optional<BrandEntity> priceEntity = brandRepository.findById(Mockito.anyLong());
    assertTrue(priceEntity.isEmpty());
  }

  @Test
  public void getBrandEntity() {
    Optional<BrandEntity> priceEntity = brandRepository.findById(1L);
    assertTrue(priceEntity.isPresent());
  }
}
