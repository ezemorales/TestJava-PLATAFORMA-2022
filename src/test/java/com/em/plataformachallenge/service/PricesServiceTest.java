package com.em.plataformachallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.em.plataformachallenge.entidad.BrandEntity;
import com.em.plataformachallenge.entidad.PriceEntity;
import com.em.plataformachallenge.exceptions.EntityNotFoundException;
import com.em.plataformachallenge.repository.BrandRepository;
import com.em.plataformachallenge.repository.PricesRepository;
import com.em.plataformachallenge.servicio.PricesService;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Clase que contiene los test del servicio {@link PricesService}
 */
@SpringBootTest
public class PricesServiceTest {

  @Autowired
  PricesService pricesService;

  @Mock
  PricesRepository pricesRepository;

  @Mock
  BrandRepository brandRepository;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Caso en el que el repositorio de prices devuelve un optional vacio
   */
  @Test
  public void getPriceEmpty() {
    BrandEntity brandEntity = new BrandEntity(1L, "ZARA");

    Mockito.when(
            pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandEntity, 1L, LocalDateTime.now(), LocalDateTime.now()))
        .thenReturn(Optional.of(new PriceEntity()));

    String error = assertThrows(EntityNotFoundException.class,
        () -> pricesService.getPrice(1L, 1L, "2022-12-12-12:00:00")).getMessage();

    assertEquals(
        "Precio no encontrado para la cadena 1, producto 1 y fecha de aplicacion 2022-12-12-12:00:00.",
        error);
  }

  /**
   * Caso en el que el repositorio de brand devuelve un optional vacio
   */
  @Test
  public void getBrandEmpty() {

    Mockito.when(
            brandRepository.findById(Mockito.any()))
        .thenReturn(Optional.empty());

    String error = assertThrows(EntityNotFoundException.class,
        () -> pricesService.getPrice(11L, 1L, "2022-12-12-12:00:00")).getMessage();

    assertEquals(
        "Cadena con id 11 no encontrada.",
        error);
  }


  /**
   * Caso en el que el repositorio devuelve un producto encontrado
   */
  @Test
  public void getPriceOk() {
    assertNotNull(pricesService.getPrice(1L, 35455L, "2020-06-15-10:00:00"));
  }

  /**
   * Caso en el que el servicio recibe una fecha incorrecta
   */
  @Test
  public void getPriceDateIncorrectFormat() {
    assertThrows(DateTimeParseException.class,
        () -> pricesService.getPrice(1L, 35455L, "2020-06-15:10:00:00"));
  }
}
