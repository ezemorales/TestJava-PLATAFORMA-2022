package com.em.plataformachallenge.controller;

import com.em.plataformachallenge.entidad.PriceEntity;
import com.em.plataformachallenge.servicio.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controladora que contiene los endpoints del validador de precios
 */
@RestController
public class ValidadorPrecioController {
  @Autowired
  PricesService pricesService;

  @GetMapping("/v1/prices")
  @Operation(summary = "Obtiene el precio correspondiente a la marca, producto y fecha solicitada.")
  public ResponseEntity<PriceEntity> getPriceByProductBrandAndApplicationDate(
      @Parameter(description = "Identificador del producto", example = "3455") @RequestParam Long productId,
      @Parameter(description = "Identificador de la cadena", example = "1") @RequestParam Long brandId,
      @Parameter(description = "Fecha de aplicacion", example = "2021-05-20-17:00:01") @RequestParam String applicationDate
  ) {
    return ResponseEntity.ok(pricesService.getPrice(brandId, productId, applicationDate));

  }

}
